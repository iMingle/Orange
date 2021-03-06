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

package org.orange.abc;

/**
 * @author mingle
 */
public class Abc {
    public static void main(String[] args) {
        /**
         * 12345的浮点数标识
         * 11000000111001  1.1000000111001*(2**13)
         */
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-1 >> 2));
        System.out.println(-1 >> 20);
        System.out.println(Integer.toBinaryString(-1 >>> 1));
        System.out.println(-1 >>> 1);
        System.out.println(Integer.MAX_VALUE);

        System.out.println(1 ^ 3);

        System.out.println(Float.floatToIntBits(1.2f));
        System.out.println(Integer.toBinaryString((-1) >> 2));
        System.out.println(Integer.toBinaryString(12345)); // 1+8+16+32+2**12+2**13
    }

    private static class TreeNode {
        int val = 0;
        TreeNode left;
        TreeNode right;
    }
}
