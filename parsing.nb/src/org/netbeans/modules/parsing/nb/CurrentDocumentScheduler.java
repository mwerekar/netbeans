/**
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

package org.netbeans.modules.parsing.nb;

import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

import org.netbeans.modules.parsing.api.Source;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;
import org.openide.util.lookup.ServiceProvider;


/**
 *
 * @author Jan Jancura
 */
@ServiceProvider(service=Scheduler.class)
public class CurrentDocumentScheduler extends CurrentEditorTaskScheduler {
    
    private Document        currentDocument;
    
    protected void setEditor (JTextComponent editor) {
        if (editor != null) {
            Document document = editor.getDocument ();
            if (currentDocument == document) return;
            currentDocument = document;
            final Source source = Source.create (currentDocument);
            schedule (source, new SchedulerEvent (this) {});
        }
        else {
            currentDocument = null;            
            schedule(null, null);
        }
    }

    /**
     * for tests only
     * @param source
     */
    public void schedule (Source source) {
        schedule (source, new SchedulerEvent (this) {});
    }
    
    @Override
    public String toString () {
        return "CurrentDocumentScheduler";
    }

    @Override
    protected SchedulerEvent createSchedulerEvent (SourceModificationEvent event) {
        if (event.getModifiedSource () == getSource())
            return new SchedulerEvent (this) {};
        return null;
    }
}



