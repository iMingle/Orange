/*
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
 * limitations under the License.
 */

package org.mingle.orange.java8;

/**
 * 
 * 
 * @author mingle
 */
public class InterfaceDefaultMethod {
    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        
        System.out.println(formula.calculate(100)); // 100.0
        System.out.println(formula.sqrt(16)); // 4.0
    }
}

interface Formula {
    double calculate(int a);

    /**
     * 默认方法
     * 
     * @param a
     * @return
     */
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
    
    /**
     * 静态方法
     * 
     * @param args
     */
    public static void main(String[] args) {
        
    }
}

