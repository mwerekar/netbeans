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
package org.openide.loaders;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import org.openide.ErrorManager;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import org.xml.sax.SAXException;

/** There was a problem for objects queried twice from inside the XML parsing and 
 * lookup preparation.
 *
 * @author Jaroslav Tulach
 */
public class XMLDataObjectLifeLock68934Test extends LoggingTestCaseHid 
implements Node.Cookie {

    private ErrorManager err;
    
    public XMLDataObjectLifeLock68934Test(String s) {
        super(s);
    }
    protected void setUp() throws Exception {
        clearWorkDir();
        
        err = ErrorManager.getDefault().getInstance("TEST-" + getName());
    }
    
    public void testQueryFromInsideTheQuery() throws IOException {
        FileObject res = FileUtil.createData(
            FileUtil.getConfigRoot(),
            getName() + "/R.xml"
        );
        
        err.log("file created: " + res);
        org.openide.filesystems.FileLock l = res.lock();
        OutputStream os = res.getOutputStream(l);
        err.log("stream opened");
        PrintStream ps = new PrintStream(os);
        
        ps.println("<?xml version='1.0' encoding='UTF-8'?>");
        ps.println("<!DOCTYPE MIME-resolver PUBLIC '-//NetBeans//DTD MIME Resolver 1.0//EN' 'http://www.netbeans.org/dtds/mime-resolver-1_0.dtd'>");
        ps.println("<MIME-resolver>");
        ps.println("    <file>");
        ps.println("        <ext name='lenka'/>");
        ps.println("        <resolver mime='hodna/lenka'/>");
        ps.println("    </file>");
        ps.println("</MIME-resolver>");

        err.log("Content written");
        os.close();
        err.log("Stream closed");
        l.releaseLock();
        err.log("releaseLock");
    
        
        final DataObject obj = DataObject.find(res);
        
        class EP implements Environment.Provider, Node.Cookie {
            public EP query = this;
            
            public Lookup getEnvironment(DataObject obj) {
                
                if (query == this) {
                    query = null;
                    query = (EP)obj.getCookie(EP.class);
                
                    assertEquals("Right object: ", XMLDataObject.class, obj.getClass());
                    XMLDataObject xml = (XMLDataObject)obj;
                    String id = null;
                    try {
                        id = xml.getDocument().getDoctype().getPublicId();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        fail("No exception");
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                        fail("No exception");
                    }
                    assertEquals("-//NetBeans//DTD MIME Resolver 1.0//EN", id);
                }
                
                
                return Lookups.singleton(this);
            }
        };
        
        EP environmentProvider = new EP();
        registerIntoLookup(environmentProvider);
        

        EP mine = (EP)obj.getCookie(EP.class);
        
        assertEquals("provider is objects cookie", environmentProvider, mine);
        assertEquals("query inside works as well", environmentProvider, environmentProvider.query);
    }
    
        
}
