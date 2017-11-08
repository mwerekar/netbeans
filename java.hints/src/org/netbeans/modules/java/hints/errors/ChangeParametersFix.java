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
package org.netbeans.modules.java.hints.errors;

import com.sun.source.tree.Tree;
import java.util.Set;
import javax.lang.model.element.Modifier;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import org.netbeans.api.java.source.TreePathHandle;
import org.netbeans.modules.refactoring.api.Problem;
import org.netbeans.modules.refactoring.api.RefactoringSession;
import org.netbeans.modules.refactoring.api.ui.RefactoringActionsFactory;
import org.netbeans.modules.refactoring.java.api.ChangeParametersRefactoring;
import org.netbeans.modules.refactoring.java.api.ChangeParametersRefactoring.ParameterInfo;
import org.netbeans.modules.refactoring.java.api.ui.JavaRefactoringActionsFactory;
import org.netbeans.spi.editor.hints.ChangeInfo;
import org.netbeans.spi.editor.hints.Fix;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.Lookups;
import static org.netbeans.modules.java.hints.errors.Bundle.*;

/**
 *
 * @author Ralph Ruijs
 */
@NbBundle.Messages({"LBL_FIX_ChangeMethodParameters=Change Method Signature from {0} to {1}",
                    "LBL_FIX_ChangeConstructorParameters=Change constructor from {0} to {1}"})
public final class ChangeParametersFix implements Fix {
    private final boolean doFullRefactoring;
    private final TreePathHandle tph;
    private final String declaration;
    private final String newDeclaration;
    private final ChangeParametersRefactoring.ParameterInfo[] newParameterInfo;
    private final Set<Modifier> modifiers;
    private final boolean isConstr;

    public ChangeParametersFix(boolean doFullRefactoring, TreePathHandle tph, Set<Modifier> modifiers, String declaration, String newDeclaration, ParameterInfo[] newParameterInfo, boolean isConstr) {
        this.doFullRefactoring = doFullRefactoring;
        this.tph = tph;
	this.modifiers = modifiers;
        this.declaration = declaration;
        this.newDeclaration = newDeclaration;
        this.newParameterInfo = newParameterInfo;
        this.isConstr = isConstr;
    }

    @Override
    public String getText() {
        return isConstr? LBL_FIX_ChangeConstructorParameters(declaration, newDeclaration) : LBL_FIX_ChangeMethodParameters(declaration, newDeclaration); // NOI18N
    }

    @Override
    public ChangeInfo implement() throws Exception {
        if(doFullRefactoring) {
            doFullChangeMethodParameters(tph, newParameterInfo);
            return null;
        }
        ChangeParametersRefactoring refactoring = new ChangeParametersRefactoring(tph);
        refactoring.setParameterInfo(new ChangeParametersRefactoring.ParameterInfo[]{});
        refactoring.setModifiers(modifiers);
        refactoring.setParameterInfo(newParameterInfo);
        RefactoringSession session = RefactoringSession.create("ChangeMethodParameterHint"); // NOI18N
        Problem problem = null;
        problem = refactoring.preCheck();
        if (problem != null) {
            doFullChangeMethodParameters(tph, newParameterInfo);
            return null;
        }
        problem = refactoring.prepare(session);
        if (problem != null) {
            doFullChangeMethodParameters(tph, newParameterInfo);
            return null;
        }
        problem = session.doRefactoring(false);
        if (problem != null) {
            doFullChangeMethodParameters(tph, newParameterInfo);
            return null;
        }
        return null;
    }
    
    private static void doFullChangeMethodParameters(TreePathHandle tph, ParameterInfo[] newParameterInfo) {
        InstanceContent ic = new InstanceContent();
        ic.add(tph);
        ic.add(new AbstractNode(Children.LEAF, Lookups.singleton(tph)));
        for (ParameterInfo parameterInfo : newParameterInfo) {
            ic.add(parameterInfo);
        }
        Lookup actionContext = new AbstractLookup(ic);
        final Action a = JavaRefactoringActionsFactory.changeParametersAction().createContextAwareInstance(actionContext);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                a.actionPerformed(RefactoringActionsFactory.DEFAULT_EVENT);
            }
        });
    }
}
