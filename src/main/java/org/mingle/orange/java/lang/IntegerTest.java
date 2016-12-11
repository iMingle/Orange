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

package org.mingle.orange.java.lang;

/**
 *
 * @author Mingle
 */
public class IntegerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(0xffffffff));
        System.out.println(Integer.toUnsignedLong(0xffffffff));
        System.out.println(Integer.toUnsignedString(0xffffffff));
        System.out.println(Integer.valueOf(1));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
    }

}
