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

/*
 * DocumentationQuery.java
 *
 * Created on June 8, 2006, 9:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.xml.schema.completion;

import java.net.URL;
import javax.swing.Action;
import javax.swing.text.Document;
import javax.swing.text.html.HTML;
import org.netbeans.modules.xml.axi.AXIComponent;
import org.netbeans.modules.xml.axi.AbstractAttribute;
import org.netbeans.modules.xml.axi.AbstractElement;
import org.netbeans.modules.xml.axi.Attribute;
import org.netbeans.spi.editor.completion.CompletionDocumentation;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.openide.util.NbBundle;
import org.netbeans.modules.xml.axi.Element;

/**
 *
 * @author Samaresh
 */
public class DocumentationQuery extends AsyncCompletionQuery {
    
    private CompletionResultItem completionItem;
    
    DocumentationQuery(CompletionResultItem item) {
        this.completionItem = item;
    }
    
    protected void query(CompletionResultSet resultSet,
            Document doc, int caretOffset) {
        resultSet.setDocumentation(DocumentationItem.
                createDocumentationItem(completionItem));
        resultSet.finish();
    }
        
}
