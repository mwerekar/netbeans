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
package org.netbeans.modules.project.ui.zip;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import org.openide.NotificationLineSupport;
import org.openide.util.NbBundle.Messages;

/**
 *
 * @author mkozeny
 */
public class ChangeImportFolder extends javax.swing.JPanel {

    /**
     * Creates new form ChangeImportFolder
     */
    public ChangeImportFolder() {
        initComponents();
    }

    JTextField getFolderField() {
        return folderField;
    }
    
    @Messages({"# {0} - folder", "ERR_project_folder_already_exists=Project folder \"{0}\" already exists."})
    boolean checkImportFolder(NotificationLineSupport notifications, String entry) {
        notifications.clearMessages();
        String updatedPath = folderField.getText();
        File updatedFile = new File(updatedPath, entry);
        File folderToImport = new File(updatedPath);
        if (updatedFile.exists()) {
            notifications.setWarningMessage(Bundle.ERR_project_folder_already_exists(entry));
            return false;
        }
        if (updatedPath.isEmpty()) {
            notifications.setInformationMessage(Bundle.ERR_no_folder());
            return false;
        }
        if (!folderToImport.isDirectory()) {
            notifications.setErrorMessage(Bundle.ERR_folder_nonexistent(updatedPath));
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        folderLabel = new javax.swing.JLabel();
        folderField = new javax.swing.JTextField();
        folderButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(371, 55));

        org.openide.awt.Mnemonics.setLocalizedText(folderLabel, org.openide.util.NbBundle.getMessage(ChangeImportFolder.class, "ChangeImportFolder.folderLabel.text")); // NOI18N

        folderField.setEditable(false);
        folderField.setText(org.openide.util.NbBundle.getMessage(ChangeImportFolder.class, "ChangeImportFolder.folderField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(folderButton, org.openide.util.NbBundle.getMessage(ChangeImportFolder.class, "ChangeImportFolder.folderButton.text")); // NOI18N
        folderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(folderLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(folderField, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(folderButton)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(folderLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(folderField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(folderButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void folderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_folderButtonActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            folderField.setText(fc.getSelectedFile().getAbsolutePath());
            firePropertyChange("validity", null, null);
        }
    }//GEN-LAST:event_folderButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton folderButton;
    private javax.swing.JTextField folderField;
    private javax.swing.JLabel folderLabel;
    // End of variables declaration//GEN-END:variables
}
