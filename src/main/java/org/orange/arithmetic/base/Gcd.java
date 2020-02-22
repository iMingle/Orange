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

package org.orange.arithmetic.base;

/**
 * 最小公倍数
 *
 * @author mingle
 */
public class Gcd {

    public static int gcd(int p, int q) {
        while (q != 0) {
            int rem = p % q;
            p = q;
            q = rem;
        }

        return p;
    }

    public static int gcdRecursive(int p, int q) {
        if (q == 0)
            return p;
        int r = p % q;
        return gcdRecursive(q, r);
    }

    public static void main(String[] args) {
        int b = 50;
        int a = 15;

        System.out.println(Gcd.gcd(a, b));
        System.out.println(Gcd.gcdRecursive(a, b));
    }

}
