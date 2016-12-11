/*
 *
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 *
 */

package org.mingle.orange.java.base;

import java.util.Date;

/**
 * 
 * @author mingle
 */
public class InnerClassTest {
    private static int age;
    
    public static class Inner {
        public void test() {
            System.out.println(age);
            final String methodVar = "method variable";
            
            class methodClass {
                public String t() {
                    return methodVar;
                }
            }
            
            methodClass m = new methodClass();
            
            System.out.println(m.t());
        }
        
        
    }

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        InnerClassTest outer = new InnerClassTest();
        outer.age = 13;
        Inner inner = new Inner();
        
        inner.test();
        
        System.out.println(new Person("Mingle", 3, 3) {
            @SuppressWarnings("unused")
            private Date birthday;
            
            public String toString() {
                return this.getClass().getName() + "####" + this.getName() + this.getAge() + this.getSex();
            }
        });
    }
}
