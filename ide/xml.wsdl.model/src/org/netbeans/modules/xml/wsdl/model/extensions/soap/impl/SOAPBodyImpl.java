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

package org.netbeans.modules.xml.wsdl.model.extensions.soap.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.netbeans.modules.xml.wsdl.model.BindingInput;
import org.netbeans.modules.xml.wsdl.model.BindingOutput;
import org.netbeans.modules.xml.wsdl.model.Part;
import org.netbeans.modules.xml.wsdl.model.WSDLModel;
import org.netbeans.modules.xml.wsdl.model.extensions.soap.SOAPBody;
import org.netbeans.modules.xml.wsdl.model.extensions.soap.SOAPComponent;
import org.netbeans.modules.xml.wsdl.model.extensions.soap.SOAPQName;
import org.netbeans.modules.xml.wsdl.model.impl.Util;
import org.netbeans.modules.xml.xam.Component;
import org.netbeans.modules.xml.xam.Reference;
import org.w3c.dom.Element;

/**
 *
 * @author Nam Nguyen
 */
public class SOAPBodyImpl extends SOAPMessageBaseImpl implements SOAPBody {
    
    /** Creates a new instance of SOAPBodyImpl */
    public SOAPBodyImpl(WSDLModel model, Element e) {
        super(model, e);
    }
    
    public SOAPBodyImpl(WSDLModel model){
        this(model, createPrefixedElement(SOAPQName.BODY.getQName(), model));
    }
    
    public void accept(SOAPComponent.Visitor visitor) {
        visitor.visit(this);
    }

    public List<String> getParts() {
        String s = getAttribute(SOAPAttribute.PARTS);
        return s == null ? null : Util.parse(s);
    }
    
    public List<Reference<Part>> getPartRefs() {
        String s = getAttribute(SOAPAttribute.PARTS);
        return s == null ? null : parseParts(s);
    }
    
    public void addPart(String part) {
        String parts = getAttribute(SOAPAttribute.PARTS);
        parts = parts == null ? part : parts.trim() + " " + part; //NOI18N
        setAttribute(PARTS_PROPERTY, SOAPAttribute.PARTS, parts);
    }
    
    public void addPartRef(Reference<Part> partRef) {
        addPart(partRef.getRefString());
    }
    
    public void addPart(int index, String part) {
        List<String> parts = getParts();
        if (parts != null) {
            parts.add(index, part);
        } else {
            parts = Collections.singletonList(part);
        }
        setAttribute(PARTS_PROPERTY, SOAPAttribute.PARTS, Util.toString(parts));;
    }
    
    public void addPartRef(int index, Reference<Part> partRef) {
        addPart(index, partRef.getRefString());
    }
    
    public void removePart(String part) {
        Collection<String> parts = getParts();
        if (parts != null && parts.remove(part)) {
            setAttribute(PARTS_PROPERTY, SOAPAttribute.PARTS, Util.toString(parts));
        }
    }

    public void removePartRef(Reference<Part> partRef) {
        removePart(partRef.getRefString());
    }

    public void setParts(List<String> parts) {
        setAttribute(PARTS_PROPERTY, SOAPAttribute.PARTS, Util.toString(parts));
    }

    public void setPartRefs(List<Reference<Part>> parts) {
        String value = null;
        if (parts != null && ! parts.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Reference<Part> ref : parts) {
                sb.append(ref.getRefString());
            }
            value = sb.toString();
        }
        setAttribute(PARTS_PROPERTY, SOAPAttribute.PARTS, value);
    }
    
    private List<Reference<Part>> parseParts(String s) {
        List<Reference<Part>> ret = new ArrayList<Reference<Part>>();
        for (String part : Util.parse(s)) {
            ret.add(new PartReference(this, part));
        }
        return ret;
    }


    @Override
    public boolean canBeAddedTo(Component target) {
        if (target instanceof BindingInput || target instanceof BindingOutput) {
            return true;
        }
        return false;
    }
}
