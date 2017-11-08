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
package org.netbeans.test.codegen.labels;

/**
 *
 * @author Pavel Flaska
 */
public class SetLabelTestClass {

    int number = 25;

    /** Creates a new instance of Pepik */
    public <AnyType> void breakWithLabel() {
        Alois: {
            System.err.println("Tady je Lojzovo.");

            Marenka: {
                System.err.println("Marenka bydli na Lojzove.");
                if (number > 10) {
                    break Marenka;
                } else {
                    break Alois;
                }
            }
        }
        Frantisek: for (int i = 0; i < 10; i++) {
            if (number == 0) {
                continue Frantisek;
            }
            number++;
        }
        java.util.List<AnyType> l;
        java.util.List<String> ls;
    }
    
}
