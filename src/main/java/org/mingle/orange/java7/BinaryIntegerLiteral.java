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

package org.mingle.orange.java7;

/**
 * 
 * 
 * @author Mingle
 */
public class BinaryIntegerLiteral {
    public static void main(String[] args) {
        byte b1 = 0B00100001;
        byte b2 = 0X21;
        byte b3 = 041;
        byte b4 = 33;
        System.out.println((b1 == b2) && (b3 == b4));
        
        long maxInt = 21_474_836_47;
        System.out.println(Integer.MAX_VALUE == maxInt);
    }
}
