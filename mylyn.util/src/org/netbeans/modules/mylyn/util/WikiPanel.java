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
package org.netbeans.modules.mylyn.util;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 *
 * @author jpeska
 */
public abstract class WikiPanel extends JPanel {

    public abstract String getWikiFormatText();

    public abstract void setWikiFormatText(String wikiFormatText);

    public abstract void appendCodeText(String codeToAppend);

    public abstract void clear();

    public abstract JLabel getWarningLabel();

    public abstract void registerHighlights(JTextPane wikiPreviewPane);

    public abstract JTextPane getPreviewPane();

    public abstract JTextPane getCodePane();
}
