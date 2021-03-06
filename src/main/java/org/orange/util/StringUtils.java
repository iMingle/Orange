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

package org.orange.util;

import java.util.LinkedList;

/**
 * String工具类
 * 
 * @author mingle
 */
public class StringUtils {
    /**
     * Given an input string, reverse the string word by word.
     * 
     * @param in
     * @return
     */
    public static String reverse(String in) {
        if (in == null)
            return null;
        if (in == "")
            return "";

        LinkedList<String> stack = new LinkedList<String>();
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

    /**
     * 递归实现reverse
     * 
     * @param s
     * @return
     */
    public static String reverseRecursion(String s) {
        int N = s.length();
        if (N <= 1)
            return s;
        String a = s.substring(0, N / 2);
        String b = s.substring(N / 2, N);
        return reverseRecursion(b) + reverseRecursion(a);
    }

    public static void main(String[] args) {
        String seq = "Hello";
        System.out.println(StringUtils.reverse("I  love the game"));
        System.out.println(seq);
        System.out.println(StringUtils.reverseRecursion(seq));
    }

}
