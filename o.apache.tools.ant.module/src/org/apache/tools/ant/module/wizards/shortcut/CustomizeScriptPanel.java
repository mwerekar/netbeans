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

package org.apache.tools.ant.module.wizards.shortcut;

import java.awt.Component;
import javax.swing.JEditorPane;
import javax.swing.event.ChangeListener;
import javax.swing.text.EditorKit;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

final class CustomizeScriptPanel extends javax.swing.JPanel {

    /** Create the wizard panel component and set up some basic properties. */
    public CustomizeScriptPanel (CustomizeScriptWizardPanel wiz) {
        initComponents ();
        initAccessibility ();
        // Provide a name in the title bar.
        setName (NbBundle.getMessage (CustomizeScriptPanel.class, "CSP_LBL_cust_gend_ant_script"));
        scriptPane.setContentType ("text/xml"); // NOI18N
        // Hack; EditorKit does not permit "fallback" kits, so we have to
        // mimic what the IDE itself does:
        EditorKit kit = scriptPane.getEditorKit ();
        String clazz = kit.getClass ().getName ();
        if (clazz.equals ("javax.swing.text.DefaultEditorKit") || // NOI18N
               clazz.equals ("javax.swing.JEditorPane$PlainEditorKit")) { // NOI18N
            scriptPane.setEditorKit (JEditorPane.createEditorKitForContentType ("text/plain")); // NOI18N
        }
    }

    // --- VISUAL DESIGN OF PANEL ---
    
    @Override
    public void requestFocus () {
        super.requestFocus ();
        scriptPane.requestFocus ();
    }

    private void initAccessibility () {        
        scriptPane.getAccessibleContext().setAccessibleName(NbBundle.getMessage (CustomizeScriptPanel.class, "ACSN_TEXT_you_may_customize_gend2"));          
        scriptPane.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage (CustomizeScriptPanel.class, "ACSD_TEXT_you_may_customize_gend2"));          
        this.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(CustomizeScriptPanel.class, "CSP_TEXT_you_may_customize_gend2"));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        scrollPane = new javax.swing.JScrollPane();
        scriptPane = new javax.swing.JEditorPane();
        hintsArea = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout(0, 11));

        scrollPane.setPreferredSize(new java.awt.Dimension(100, 100));
        scrollPane.setViewportView(scriptPane);

        add(scrollPane, java.awt.BorderLayout.CENTER);

        hintsArea.setBackground(new java.awt.Color(204, 204, 204));
        hintsArea.setEditable(false);
        hintsArea.setFont(javax.swing.UIManager.getFont ("Label.font"));
        hintsArea.setForeground(new java.awt.Color(102, 102, 153));
        hintsArea.setLineWrap(true);
        hintsArea.setText(NbBundle.getMessage(CustomizeScriptPanel.class, "CSP_TEXT_you_may_customize_gend2"));
        hintsArea.setWrapStyleWord(true);
        hintsArea.setDisabledTextColor(javax.swing.UIManager.getColor ("Label.foreground"));
        hintsArea.setEnabled(false);
        hintsArea.setOpaque(false);
        add(hintsArea, java.awt.BorderLayout.NORTH);

    }//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea hintsArea;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JEditorPane scriptPane;
    // End of variables declaration//GEN-END:variables

    
    public static class CustomizeScriptWizardPanel implements WizardDescriptor.Panel<ShortcutWizard> {

        private CustomizeScriptPanel panel = null;

        public Component getComponent () {
            return getPanel();
        }
        
        private CustomizeScriptPanel getPanel() {
            if (panel == null) {
                panel = new CustomizeScriptPanel(this);
            }
            return panel;
        }

        public HelpCtx getHelp () {
            return HelpCtx.DEFAULT_HELP;
        }

        public boolean isValid () {
            return true;
            // XXX ideally make it valid only if script is parseable without errors;
            // could use AntProjectSupport for this, or just parse the XML and check
            // for the correct root element etc.
        }

        public final void addChangeListener (ChangeListener l) {}
        public final void removeChangeListener (ChangeListener l) {}

        public void readSettings(ShortcutWizard wiz) {
            String contents = wiz.getContents();
            getPanel().scriptPane.setText(contents);
        }
        public void storeSettings(ShortcutWizard wiz) {
            wiz.putContents(getPanel().scriptPane.getText());
        }
    }

}
