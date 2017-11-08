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
package org.netbeans.modules.xml.wsdl.model.extensions.soap;

import org.netbeans.modules.xml.wsdl.model.ExtensibilityElement;

/**
 *
 * @author Nam Nguyen
 */
public interface SOAPComponent extends ExtensibilityElement {

    public interface Visitor {
        void visit(SOAPHeader target);
        void visit(SOAPAddress target);
        void visit(SOAPBinding target);
        void visit(SOAPBody target);
        void visit(SOAPFault target);
        void visit(SOAPHeaderFault target);
        void visit(SOAPOperation target);
    }
    
    void accept(Visitor visitor);
}
