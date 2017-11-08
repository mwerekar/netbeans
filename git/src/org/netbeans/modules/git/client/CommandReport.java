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

package org.netbeans.modules.git.client;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * Small notification dialog similar to ErrorManager's one. The main purpose is to show (possibly long) list
 * of warning and error messages.
 *
 * @author Maros Sandor
 */
public class CommandReport extends javax.swing.JPanel {

    
    public CommandReport(String prompt, String message) {
        initComponents();
        JComponent msg = createCenterComponent(message);
        jScrollPane1.setViewportView(msg);        
        jLabel1.setText(prompt);        
    }

    private JComponent createCenterComponent(String text) {
        if (text.startsWith("<html") || text.startsWith("<HTML")) {  // NOI18N
            JLabel msg = new JLabel(text);
            return msg;            
        } else {
            JTextArea msg = new JTextArea();
            msg.setBackground(javax.swing.UIManager.getDefaults().getColor("TextField.inactiveBackground")); // NOI18N
            msg.setColumns(40);
            msg.setEditable(false);
            msg.setRows(10);
            msg.setMargin(new java.awt.Insets(4, 4, 0, 0));
            msg.setText(text);
            return msg;
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
}
