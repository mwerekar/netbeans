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
package org.netbeans.modules.csl.editor.hyperlink;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import org.netbeans.modules.csl.api.DeclarationFinder.AlternativeLocation;
import org.netbeans.modules.csl.api.DeclarationFinder.DeclarationLocation;
import org.netbeans.modules.csl.api.ElementHandle;
import org.netbeans.modules.csl.api.UiUtils;
import org.netbeans.modules.csl.core.GsfHtmlFormatter;
import org.netbeans.modules.csl.navigation.Icons;
import org.openide.awt.StatusDisplayer;
import org.openide.filesystems.FileObject;
import org.openide.util.NbBundle;

/**
 * This file is originally from Retouche, the Java Support 
 * infrastructure in NetBeans. I have modified the file as little
 * as possible to make merging Retouche fixes back as simple as
 * possible. 
 * 
 * (This used to be IsOverriddenPopup in org.netbeans.modules.java.editor.overridden)
 *
 *
 * @author Jan Lahoda
 * @author Tor Norbye
 */
public class DeclarationPopup extends JPanel implements FocusListener {
    
    private String caption;
    private List<AlternativeLocation> declarations;
    
    /** Creates new form DeclarationPopup */
    public DeclarationPopup(String caption, List<AlternativeLocation> declarations) {
        this.caption = caption;
        this.declarations = declarations;
        
        initComponents();
        
        jList1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        addFocusListener(this);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setFocusCycleRoot(true);
        setLayout(new java.awt.GridBagLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(caption
        );
        jLabel1.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jLabel1, gridBagConstraints);

        jList1.setModel(createListModel());
        jList1.setCellRenderer(new RendererImpl());
        jList1.setSelectedIndex(0);
        jList1.setVisibleRowCount(declarations.size()
        );
        jList1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jList1KeyPressed(evt);
            }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    
    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 1) {
            openSelected();
        }
    }//GEN-LAST:event_jList1MouseClicked
    
    private void jList1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && evt.getModifiers() == 0) {
            openSelected();
        }
    }//GEN-LAST:event_jList1KeyPressed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
    private void openSelected() {
        AlternativeLocation desc = (AlternativeLocation) jList1.getSelectedValue();
        
        if (desc != null) {
            DeclarationLocation location = desc.getLocation();
            if (location == DeclarationLocation.NONE) {
                StatusDisplayer.getDefault().setStatusText(NbBundle.getMessage(DeclarationPopup.class, "InvalidLoc"));
                Toolkit.getDefaultToolkit().beep();
            } else {
                String invalid = location.getInvalidMessage();
                if (invalid != null) {
                    // TODO - show in the editor as an error instead?
                    StatusDisplayer.getDefault().setStatusText(invalid);
                    Toolkit.getDefaultToolkit().beep();
                } else {
                    FileObject fileObject = location.getFileObject();
                    if (fileObject != null) {
                        UiUtils.open(fileObject,location.getOffset());
                    } else {
                        Toolkit.getDefaultToolkit().beep();
                    }
                }
            }
        }
        
        PopupUtil.hidePopup();
    }
    
    private ListModel createListModel() {
        DefaultListModel dlm = new DefaultListModel();
        
        for (AlternativeLocation el: declarations) {
            dlm.addElement(el);
        }
        
        return dlm;
    }
    
    private static class RendererImpl extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            
            if (value instanceof AlternativeLocation) {
                AlternativeLocation desc = (AlternativeLocation) value;
                ElementHandle e = desc.getElement();
                ImageIcon icon = Icons.getElementIcon(e.getKind(), e.getModifiers());
                setIcon(icon);
                
                GsfHtmlFormatter formatter = new GsfHtmlFormatter();
                String s = desc.getDisplayHtml(formatter);
                setText("<html>"+s);
            }
            
            return c;
        }
    }
    
    public void focusGained(FocusEvent arg0) {
        jList1.requestFocus();
        jList1.requestFocusInWindow();
    }
    
    public void focusLost(FocusEvent arg0) {
    }
    
}
