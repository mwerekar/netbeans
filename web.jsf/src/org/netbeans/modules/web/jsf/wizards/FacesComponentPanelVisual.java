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
package org.netbeans.modules.web.jsf.wizards;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.util.ChangeSupport;

/**
 *
 * @author Martin Fousek <marfous@netbeans.org>
 */
public class FacesComponentPanelVisual extends javax.swing.JPanel implements ChangeListener {

    private static final long serialVersionUID = 1L;

    private final ChangeSupport changeSupport = new ChangeSupport(this);

    /**
     * Creates new form FacesComponentPanelVisual
     */
    public FacesComponentPanelVisual() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tagNameLabel = new javax.swing.JLabel();
        tagNamespaceLabel = new javax.swing.JLabel();
        tagNamespaceTextField = new javax.swing.JTextField();
        tagNameTextField = new javax.swing.JTextField();
        sampleCodeCheckBox = new javax.swing.JCheckBox();

        tagNameLabel.setLabelFor(tagNameTextField);
        org.openide.awt.Mnemonics.setLocalizedText(tagNameLabel, org.openide.util.NbBundle.getMessage(FacesComponentPanelVisual.class, "FacesComponentPanelVisual.tagNameLabel.text")); // NOI18N

        tagNamespaceLabel.setLabelFor(tagNamespaceTextField);
        org.openide.awt.Mnemonics.setLocalizedText(tagNamespaceLabel, org.openide.util.NbBundle.getMessage(FacesComponentPanelVisual.class, "FacesComponentPanelVisual.tagNamespaceLabel.text")); // NOI18N

        tagNamespaceTextField.setText("http://xmlns.jcp.org/jsf/component"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(sampleCodeCheckBox, org.openide.util.NbBundle.getMessage(FacesComponentPanelVisual.class, "FacesComponentPanelVisual.sampleCodeCheckBox.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tagNamespaceLabel)
                    .addComponent(tagNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tagNamespaceTextField)
                    .addComponent(tagNameTextField)))
            .addGroup(layout.createSequentialGroup()
                .addComponent(sampleCodeCheckBox)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagNameLabel)
                    .addComponent(tagNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagNamespaceLabel)
                    .addComponent(tagNamespaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(sampleCodeCheckBox))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox sampleCodeCheckBox;
    private javax.swing.JLabel tagNameLabel;
    private javax.swing.JTextField tagNameTextField;
    private javax.swing.JLabel tagNamespaceLabel;
    private javax.swing.JTextField tagNamespaceTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void stateChanged(ChangeEvent e) {
    }

    public void addChangeListener(ChangeListener listener) {
        changeSupport.addChangeListener(listener);
    }

    public void removeChangeListener(ChangeListener listener) {
        changeSupport.removeChangeListener(listener);
    }

    public String getTagName() {
        return tagNameTextField.getText();
    }

    public String getTagNamespace() {
        return tagNamespaceTextField.getText();
    }

    public boolean isSampleCode() {
        return sampleCodeCheckBox.isSelected();
    }
}