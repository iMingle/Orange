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

package org.mingle.orange.effectivejava;

import java.util.Collection;

/**
 * 用接口模拟可伸缩的枚举
 * 
 * @author mingle
 */
public class EnumExtensible {
    interface Operation {
        double apply(double x, double y);
    }
    
    enum BasicOperation implements Operation {
        PLUS("+") {
            @Override public double apply(double x, double y) {
                return x + y;
            }
        },
        MINUS("-") {
            @Override public double apply(double x, double y) {
                return x - y;
            }
        },
        TIMES("*") {
            @Override public double apply(double x, double y) {
                return x * y;
            }
        },
        DIVIDE("/") {
            @Override public double apply(double x, double y) {
                return x / y;
            }
        };
        
        private final String symbol;

        BasicOperation(String symbol) {
            this.symbol = symbol;
        }
        
        @Override public String toString() {
            return symbol;
        }
    }
    
    /**
     * 扩展enum
     */
    enum ExtendedOperation implements Operation {
        EXP("^") {
            @Override public double apply(double x, double y) {
                return Math.pow(x, y);
            }
        },
        REMAINDER("%") {
            @Override public double apply(double x, double y) {
                return x % y;
            }
        };
        
        private final String symbol;
        
        ExtendedOperation(String symbol) {
            this.symbol = symbol;
        }
        
        @Override public String toString() {
            return symbol;
        }
    }
    
    public static <T extends java.lang.Enum<T> & Operation> void test(Class<T> opSet, double x, double y) {
        for (Operation op : opSet.getEnumConstants()) {
            System.out.printf("%f %s %f = %f\n", x, op, y, op.apply(x, y));
        }
    }
    
    public static void test(Collection<? extends Operation> opSet, double x, double y) {
        for (Operation op : opSet) {
            System.out.printf("%f %s %f = %f\n", x, op, y, op.apply(x, y));
        }
    }
    
    public static void main(String[] args) {
        test(ExtendedOperation.class, 4, 2);
    }
}
