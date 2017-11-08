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
package org.netbeans.modules.j2ee.persistence.spi.jpql;

import org.eclipse.persistence.jpa.jpql.tools.spi.IManagedTypeProvider;
import org.eclipse.persistence.jpa.jpql.tools.spi.IManagedTypeVisitor;
import org.eclipse.persistence.jpa.jpql.tools.spi.IMappedSuperclass;
import org.netbeans.modules.j2ee.metadata.model.api.support.annotation.PersistentObject;
import org.netbeans.modules.j2ee.persistence.api.metadata.orm.Attributes;

/**
 *
 * @author sp153251
 */
public class MappedSuperclass extends ManagedType implements IMappedSuperclass{

    
    public MappedSuperclass(org.netbeans.modules.j2ee.persistence.api.metadata.orm.MappedSuperclass element, IManagedTypeProvider provider){
        super((PersistentObject)element, provider);     
    }

    @Override
    public void accept(IManagedTypeVisitor imtv) {
        imtv.visit(this);
    }

    @Override
    Attributes getAttributes() {
        org.netbeans.modules.j2ee.persistence.api.metadata.orm.MappedSuperclass ms = (org.netbeans.modules.j2ee.persistence.api.metadata.orm.MappedSuperclass) getPersistentObject();
        return ms.getAttributes();
    }
    
}
