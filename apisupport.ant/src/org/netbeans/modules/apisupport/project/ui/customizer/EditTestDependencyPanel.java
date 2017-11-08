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


package org.netbeans.modules.apisupport.project.ui.customizer;

import org.netbeans.modules.apisupport.project.universe.TestModuleDependency;
import org.openide.util.NbBundle;

/**
 *
 * @author Tomas Musil
 */
public class EditTestDependencyPanel extends javax.swing.JPanel {
    private TestModuleDependency tmd ;
    
    public EditTestDependencyPanel(final TestModuleDependency originalDep) {
        tmd = originalDep;
        initComponents();
        fillOriginal();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        moduleJar = new javax.swing.JLabel();
        moduleJarValue = new javax.swing.JTextField();
        tests = new javax.swing.JCheckBox();
        compile = new javax.swing.JCheckBox();
        dependencies = new javax.swing.JCheckBox();

        moduleJar.setDisplayedMnemonic('M');
        moduleJar.setLabelFor(moduleJarValue);
        org.openide.awt.Mnemonics.setLocalizedText(moduleJar, org.openide.util.NbBundle.getMessage(EditTestDependencyPanel.class, "EditTestDependencyPanel.moduleJar.text")); // NOI18N

        moduleJarValue.setEditable(false);
        moduleJarValue.setText(org.openide.util.NbBundle.getMessage(EditTestDependencyPanel.class, "EditTestDependencyPanel.moduleJarValue.text")); // NOI18N

        tests.setMnemonic('T');
        org.openide.awt.Mnemonics.setLocalizedText(tests, org.openide.util.NbBundle.getMessage(EditTestDependencyPanel.class, "EditTestDependencyPanel.tests.text")); // NOI18N
        tests.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tests.setMargin(new java.awt.Insets(0, 0, 0, 0));

        compile.setMnemonic('C');
        org.openide.awt.Mnemonics.setLocalizedText(compile, org.openide.util.NbBundle.getMessage(EditTestDependencyPanel.class, "EditTestDependencyPanel.compile.text")); // NOI18N
        compile.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        compile.setMargin(new java.awt.Insets(0, 0, 0, 0));

        dependencies.setMnemonic('R');
        org.openide.awt.Mnemonics.setLocalizedText(dependencies, org.openide.util.NbBundle.getMessage(EditTestDependencyPanel.class, "EditTestDependencyPanel.dependencies.text")); // NOI18N
        dependencies.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dependencies.setMargin(new java.awt.Insets(0, 0, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(moduleJar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moduleJarValue, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
                    .addComponent(tests)
                    .addComponent(dependencies)
                    .addComponent(compile))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moduleJar)
                    .addComponent(moduleJarValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(tests)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dependencies)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(compile)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        moduleJar.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(EditTestDependencyPanel.class, "EditTestDependencyPanel.moduleJar.AccessibleContext.accessibleDescription")); // NOI18N
        moduleJarValue.getAccessibleContext().setAccessibleName(NbBundle.getMessage(EditTestDependencyPanel.class, "EditTestDependencyPanel.moduleJarValue.AccessibleContext.accessibleName")); // NOI18N
        moduleJarValue.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(EditTestDependencyPanel.class, "EditTestDependencyPanel.moduleJarValue.AccessibleContext.accessibleDescription")); // NOI18N
        tests.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(EditTestDependencyPanel.class, "EditTestDependencyPanel.tests.AccessibleContext.accessibleDescription")); // NOI18N
        compile.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(EditTestDependencyPanel.class, "EditTestDependencyPanel.compile.AccessibleContext.accessibleDescription")); // NOI18N
        dependencies.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(EditTestDependencyPanel.class, "EditTestDependencyPanel.dependencies.AccessibleContext.accessibleDescription")); // NOI18N

        getAccessibleContext().setAccessibleName(NbBundle.getMessage(EditTestDependencyPanel.class, "EditTestDependencyPanel.AccessibleContext.accessibleName")); // NOI18N
        getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(EditTestDependencyPanel.class, "EditTestDependencyPanel.AccessibleContext.accessibleDescription")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents
    
    private void fillOriginal(){
        moduleJarValue.setText(tmd.getModule().getCodeNameBase());
        tests.setSelected(tmd.isTest());
        compile.setSelected(tmd.isCompile());
        dependencies.setSelected(tmd.isRecursive());
    }
    
    
    public TestModuleDependency getEditedDependency(){
        
        return new TestModuleDependency(tmd.getModule(), tests.isSelected(), dependencies.isSelected(), 
                compile.isSelected());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox compile;
    private javax.swing.JCheckBox dependencies;
    private javax.swing.JLabel moduleJar;
    private javax.swing.JTextField moduleJarValue;
    private javax.swing.JCheckBox tests;
    // End of variables declaration//GEN-END:variables
    
}
