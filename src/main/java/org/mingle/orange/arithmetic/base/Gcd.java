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

package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.algs4.StdIn;

/**
 * 最小公倍数
 * 
 * @author Mingle
 */
public class Gcd {

    public static int gcd(int p, int q) {
        if (q == 0)
            return p;
        int r = p % q;
        return gcd(q, r);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int a = StdIn.readInt();
        int b = StdIn.readInt();
        
        Gcd.gcd(a, b);
    }

}
