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
 * JFXDownloadModePanel.java
 *
 * Created on 25.8.2011, 16:31:29
 */
package org.netbeans.modules.javafx2.project.ui;

import java.awt.Component;
import java.io.File;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;

/**
 *
 * @author Tomas Zezula
 * @author Petr Somol
 */
public class JFXDownloadModePanel extends javax.swing.JPanel {

    private static final Icon JAR_ICON = ImageUtilities.loadImageIcon(
            "org/netbeans/modules/javawebstart/resources/jar.gif",   // NOI18N
            false);

    /** Creates new form JFXDownloadModePanel */
    public JFXDownloadModePanel(
            final Collection<? extends File> runtimeClassPath,
            final Collection<? extends File> lazyJars) {
        initComponents();
        final TableCellRenderer renderer = new LoadRenderer();
        this.tableDownloadMode.setModel(createModel(createResources(runtimeClassPath, lazyJars)));
        this.tableDownloadMode.setDefaultRenderer(Boolean.class, renderer);        
        this.tableDownloadMode.setDefaultRenderer(File.class, renderer);
        this.tableDownloadMode.setDefaultEditor(Boolean.class, createEditor());
    }
    
    List<File> getResources() {
        final TableModel model = this.tableDownloadMode.getModel();
        final List<File> result = new LinkedList<File>();
        for (int i=0; i< model.getRowCount(); i++) {
            if ((Boolean)model.getValueAt(i, 1)) {
                result.add((File)model.getValueAt(i, 0));
            }
        }
        return result;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        labelDownloadMode = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDownloadMode = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(400, 300));
        setLayout(new java.awt.GridBagLayout());

        labelDownloadMode.setLabelFor(tableDownloadMode);
        org.openide.awt.Mnemonics.setLocalizedText(labelDownloadMode, org.openide.util.NbBundle.getMessage(JFXDownloadModePanel.class, "JFXDownloadModePanel.labelDownloadMode.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 0);
        add(labelDownloadMode, gridBagConstraints);

        tableDownloadMode.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Jar", "Load"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableDownloadMode);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        add(jScrollPane1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDownloadMode;
    private javax.swing.JTable tableDownloadMode;
    // End of variables declaration//GEN-END:variables

    private static class LoadRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            String strValue = "";   //NOI18N
            String toolTip = null;
            Icon icon = null;
            if (value instanceof Boolean) {
                strValue = NbBundle.getMessage(JFXDownloadModePanel.class, value == Boolean.TRUE ? "TXT_LAZY":"TXT_EAGER"); //NOI18N
            } else if (value instanceof File) {
                final File f = (File)value;
                strValue = f.getName();
                toolTip = f.getAbsolutePath();
                icon = JAR_ICON;
            }
            final Component c = super.getTableCellRendererComponent(
                    table,
                    strValue, //NOI18N
                    isSelected,
                    hasFocus,
                    row,
                    column);
            if (c instanceof JLabel) {
                JLabel label = (JLabel) c;
                label.setToolTipText (toolTip);
                label.setIcon(icon);
            }
            return c;
        }        
    }
    
    private static TableCellEditor createEditor() {
        final JComboBox combo = new JComboBox(new Object[] {Boolean.TRUE, Boolean.FALSE});
        combo.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                return super.getListCellRendererComponent(
                        list,
                        NbBundle.getMessage(JFXDownloadModePanel.class, value == Boolean.TRUE ? "TXT_LAZY":"TXT_EAGER"), //NOI18N
                        index,
                        isSelected,
                        cellHasFocus);
            }            
        });
        return new DefaultCellEditor(combo);
    }
    
    private static TableModel createModel(final Map<File,Boolean> jars) {
        final Object[][] data = new Object[jars.size()][];
        final Iterator<Map.Entry<File,Boolean>> it = jars.entrySet().iterator();
        for (int i=0; it.hasNext(); i++) {
            final Map.Entry<File,Boolean> entry = it.next();
            data[i] = new Object[] {entry.getKey(),entry.getValue()};
        }
        return new DefaultTableModel(
            data,
            new String[] {
                NbBundle.getMessage(JFXDownloadModePanel.class, "TXT_ArchiveFile"), //NOI18N
                NbBundle.getMessage(JFXDownloadModePanel.class, "TXT_Load") //NOI18N
            }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return File.class;
                    case 1:
                        return Boolean.class;
                    default:
                        throw new IllegalStateException();
                }
            }
            
        };
    }
    
    private static Map<File,Boolean> createResources(
            final Collection<? extends File> runtimeClassPath,
            final Collection<? extends File> lazyJars) {        
        final Map<File,Boolean> result = new LinkedHashMap<File, Boolean>();
        final Set<File> lazy = new HashSet<File>(lazyJars);                       
        for (final File p : runtimeClassPath) {
            result.put(p, lazy.contains(p));
        }
        return result;
    }    
}
