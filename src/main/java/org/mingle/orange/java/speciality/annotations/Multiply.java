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

package org.mingle.orange.java.speciality.annotations;

/**
 *
 * @author mingle
 */
@ExtractInterface("IMultiply")
public class Multiply {
    public int multiply(int x, int y) {
        int total = 0;
        for (int i = 0; i < x; i++) {
            total = add(total, y);
        }
        return total;
    }

    /**
     * @param x
     * @param y
     * @return
     */
    private int add(int x, int y) {
        return x + y;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Multiply m = new Multiply();
        System.out.println("11 * 16 = " + m.multiply(11, 16));
    }

}
