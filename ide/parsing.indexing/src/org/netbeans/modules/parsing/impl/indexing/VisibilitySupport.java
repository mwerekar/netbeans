/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.parsing.impl.indexing;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.queries.VisibilityQuery;
import org.netbeans.spi.queries.VisibilityQueryChangeEvent;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.Pair;
import org.openide.util.Parameters;
import org.openide.util.RequestProcessor;

/**
 *
 * @author Tomas Zezula
 */
class VisibilitySupport implements ChangeListener {

    private static final int VISIBILITY_CHANGE_WINDOW = 1000;
    private static final Logger LOGGER = Logger.getLogger(VisibilitySupport.class.getName());
    
    //@GuardedBy("visibilityCache")
    private final Map<FileObject,Boolean> visibilityCache = Collections.synchronizedMap(new WeakHashMap<FileObject, Boolean>());
    private final SlidingTask visibilityTask;
    private final RequestProcessor.Task visibilityChanged;
    
    private VisibilitySupport(
            @NonNull final RepositoryUpdater ru,
            @NonNull final RequestProcessor worker) {
        this.visibilityTask = new SlidingTask(ru);
        this.visibilityChanged = worker.create(this.visibilityTask);
    }

    void start() {
        VisibilityQuery.getDefault().addChangeListener(this);
    }
    
    void stop() {
        VisibilityQuery.getDefault().removeChangeListener(this);
    }
    
    boolean isVisible(
        @NonNull FileObject file,
        @NullAllowed final FileObject root) {
        long st = 0L;
        if (LOGGER.isLoggable(Level.FINER)) {
            st = System.currentTimeMillis();
        }
        try {
            final VisibilityQuery vq = VisibilityQuery.getDefault();
            final Deque<FileObject> fta = new ArrayDeque<FileObject>();
            Boolean vote = null;
            boolean folder = false;
            while (root != null && !root.equals(file)) {
                vote = visibilityCache.get(file);
                if (vote != null) {
                    break;
                }
                if (folder || file.isFolder()) {
                    fta.offer(file);
                }
                if (!vq.isVisible(file)) {
                    vote = Boolean.FALSE;
                    break;
                }
                file = file.getParent();
                folder = true;
            }
            if (vote == null) {
                vote = vq.isVisible(file);
                fta.offer(file);
            }
            if (!fta.isEmpty()) {
                synchronized(visibilityCache) {
                    for (FileObject nf : fta) {
                        visibilityCache.put(nf, vote);
                    }
                }
            }
            return vote;
        } finally {
            if (LOGGER.isLoggable(Level.FINER)) {
                LOGGER.log(
                    Level.FINER,
                    "reportVisibilityOverhead: {0}",    //NOI18N
                    (System.currentTimeMillis() - st));
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        visibilityCache.clear();
        if (Crawler.listenOnVisibility()) {            
            if (e instanceof VisibilityQueryChangeEvent) {
                final FileObject[] affectedFiles = ((VisibilityQueryChangeEvent)e).getFileObjects();
                visibilityTask.localChange(affectedFiles);
            } else {
                visibilityTask.globalChange();
            }
            visibilityChanged.schedule(VISIBILITY_CHANGE_WINDOW);
        }
    }

    private static class SlidingTask implements Runnable {

        private final RepositoryUpdater ru;

        //@GuardedBy("this")
        private boolean globalChange;
        private final Set</*@GuardedBy("this")*/FileObject> localChanges = new HashSet<FileObject>();
        //@GuardedBy("this")
        private  LogContext visibilityLogCtx;

        SlidingTask(@NonNull final RepositoryUpdater ru) {
            this.ru = ru;
        }

        synchronized void globalChange() {
            globalChange = true;

            if (visibilityLogCtx == null) {
                visibilityLogCtx = LogContext.create(LogContext.EventType.FILE, null);
            }
        }

        synchronized void localChange(final FileObject... onFiles) {
            localChanges.addAll(Arrays.asList(onFiles));
            if (visibilityLogCtx == null) {
                visibilityLogCtx = LogContext.create(LogContext.EventType.FILE, null);
            }
        }

        @Override
        public void run() {
            final boolean global;
            final Collection<FileObject> changedFiles;
            final LogContext logCtx;
            synchronized (this) {
                logCtx = visibilityLogCtx;
                visibilityLogCtx = null;
                global = globalChange;
                globalChange = false;
                changedFiles = new ArrayList<FileObject>(localChanges);
                localChanges.clear();
            }
            if (global) {
                LOGGER.fine ("VisibilityQuery global changed, reindexing");    //NOI18N
                ru.refreshAll(false, false, true, logCtx);
            } else {
                if (LOGGER.isLoggable(Level.FINE)) {
                    LOGGER.log (
                      Level.FINE,
                      "VisibilityQuery changed for {0}, reindexing these files.",    //NOI18N
                      Arrays.asList(changedFiles));
                }
                final Map<URI, Collection<URL>> srcShownPerRoot =
                        new HashMap<URI, Collection<URL>>();
                final Map<URI, Set<String>> srcHiddenPerRoot =
                        new HashMap<URI, Set<String>>();
                final Set<URI> binChangedRoot =
                        new HashSet<URI>();
                final Map<URI,TimeStamps> tsPerRoot = new HashMap<URI, TimeStamps>();
                final VisibilityQuery vq = VisibilityQuery.getDefault();
                for (FileObject chf : changedFiles) {
                    Pair<URL,FileObject> owner = ru.getOwningSourceRoot(chf);
                    if (owner != null) {
                        final boolean visible = vq.isVisible(chf);
                        try {
                            final URI ownerURI = owner.first().toURI();
                            if (visible) {
                                Collection<URL> files = srcShownPerRoot.get(ownerURI);
                                if (files == null) {
                                    files = new ArrayList<URL>();
                                    srcShownPerRoot.put(ownerURI, files);
                                }
                                if (chf.equals(owner.second())) {
                                    for (FileObject cld : chf.getChildren()) {
                                        files.add(cld.toURL());
                                    }
                                } else {
                                    files.add(chf.toURL());
                                }
                            } else if (owner.second() != null) {
                                Set<String> files = srcHiddenPerRoot.get(ownerURI);
                                if (files == null) {
                                    files = new HashSet<String>();
                                    srcHiddenPerRoot.put(ownerURI, files);
                                }
                                if (chf.isFolder()) {
                                    TimeStamps ts = tsPerRoot.get(ownerURI);
                                    if (ts == null) {
                                        ts = TimeStamps.forRoot(owner.first(), false);
                                        tsPerRoot.put(ownerURI, ts);
                                    }
                                    files.addAll(ts.getEnclosedFiles(FileUtil.getRelativePath(owner.second(), chf)));
                                } else {
                                    files.add(FileUtil.getRelativePath(owner.second(), chf));
                                }
                            }
                        } catch (URISyntaxException e) {
                            Exceptions.printStackTrace(e);
                        } catch (IOException e) {
                            Exceptions.printStackTrace(e);
                        }
                        continue;
                    }
                    owner = ru.getOwningBinaryRoot(chf);
                    if (owner != null) {
                        try {
                            final URI ownerURI = owner.first().toURI();
                            binChangedRoot.add(ownerURI);
                        } catch (URISyntaxException e) {
                            Exceptions.printStackTrace(e);
                        }
                    }
                }
                for (Map.Entry<URI,Collection<URL>> e : srcShownPerRoot.entrySet()) {
                    try {
                        ru.addIndexingJob(
                            e.getKey().toURL(),
                            e.getValue(),
                            false,
                            false,
                            false,
                            false,
                            true,
                            logCtx);
                    } catch (MalformedURLException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
                for (Map.Entry<URI,Set<String>> e : srcHiddenPerRoot.entrySet()) {
                    try {
                        ru.addDeleteJob(
                            e.getKey().toURL(),
                            e.getValue(),
                            logCtx);
                    } catch (MalformedURLException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
                for (URI e : binChangedRoot) {
                    try {
                        ru.addBinaryJob(
                            e.toURL(),
                            logCtx);
                    } catch (MalformedURLException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
        }
    }

    @NonNull
    static VisibilitySupport create(
        @NonNull final RepositoryUpdater ru,
        @NonNull final RequestProcessor worker) {
        Parameters.notNull("ru", ru);   //NOI18N
        Parameters.notNull("worker", worker);   //NOI18N
        return new VisibilitySupport(ru, worker);
    }


}
