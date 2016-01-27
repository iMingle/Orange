/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.base;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 算法的解法
 * 
 * @since 1.8
 * @author Mingle
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
