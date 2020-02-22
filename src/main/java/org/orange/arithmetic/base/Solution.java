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

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 算法的解法
 * 
 * @author mingle
 */
public class Solution {
    /**
     * Given an array of integers, every element appears twice except for one.
     * Find that single one.
     * 
     * @param A
     * @return
     */
    public static int singleNumber(int[] A) {
        int n = A.length;
        while (--n > 0)
            A[n - 1] ^= A[n];

        return A[0];
    }

    /**
     * Given an input string, reverse the string word by word.
     * 
     * @param in
     * @return
     */
    public static String reverseWords(String in) {
        if (in == null)
            return null;
        if (in == "")
            return "";

        Deque<String> stack = new ArrayDeque<String>();
        in = in.trim() + ' ';
        int length = in.length();
        int i = 0;
        int j = 0;
        while (i < length) {
            while (i < length && in.charAt(i) == ' ')
                i++;
            if (i < length) {
                j = in.indexOf(' ', i);
                stack.push(in.substring(i, j));
                i = j + 1;
            }
        }

        StringBuilder result = new StringBuilder();

        while (!stack.isEmpty())
            result.append(stack.poll()).append(" ");

        return result.toString().trim();
    }
    
    public static void main(String[] args) {
        System.out.println(Solution.singleNumber(new int[] {1, 1, 3, 3, 5, 6, 6}));
        System.out.println(Solution.reverseWords("I  love you "));
    }
}
