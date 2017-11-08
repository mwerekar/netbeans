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
 * IzPanel.java
 *
 * Created on Nov 11, 2008, 3:32:39 PM
 */
package org.netbeans.modules.localtasks.task;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.modules.bugtracking.api.Issue;
import org.netbeans.modules.bugtracking.api.IssueQuickSearch;
import org.netbeans.modules.bugtracking.api.Repository;
import org.openide.util.ChangeSupport;

/**
 *
 * @author Tomas Stupka
 * @author Marian Petras
 */
final class AddSubtaskPanel extends JPanel implements ChangeListener {

    private final IssueQuickSearch qs;
    private Repository selectedRepository;
    private final ChangeSupport support;

    AddSubtaskPanel () {
        initComponents();

        support = new ChangeSupport(this);
        qs = IssueQuickSearch.create();
        qs.setChangeListener(this);
        GroupLayout layout = (GroupLayout) getLayout();
        layout.replace(issuePanel, qs.getComponent());
    }

    Issue getIssue () {
        return qs.getIssue();
    }

    Repository getSelectedRepository () {
        return selectedRepository;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        issuePanel = new javax.swing.JPanel();

        setFocusable(false);

        issuePanel.setLayout(new java.awt.BorderLayout());

        errorLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/netbeans/modules/localtasks/resources/error.gif"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(errorLabel, org.openide.util.NbBundle.getMessage(AddSubtaskPanel.class, "AddSubtaskPanel.errorLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(issuePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(errorLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(issuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    final javax.swing.JLabel errorLabel = new javax.swing.JLabel();
    private javax.swing.JPanel issuePanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void addNotify () {
        super.addNotify();
    }

    @Override
    public void removeNotify () {
        super.removeNotify();
    }

    @Override
    public void stateChanged (ChangeEvent e) {
        support.fireChange();
    }
    
    void addChangeListener (ChangeListener listener) {
        support.addChangeListener(listener);
    }
    
    void removeChangeListener (ChangeListener listener) {
        support.removeChangeListener(listener);
    }

}
