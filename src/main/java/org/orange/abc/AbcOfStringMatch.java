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
public class AbcOfStringMatch {
    // text, pattern分别是主串和模式串
    public static int kmp(String text, String pattern) {
        int n = text.length();
        int j = 0;
        int[] next = next(pattern);

        for (int i = 0; i < n; i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j))
                j = next[j - 1] + 1;

            if (text.charAt(i) == pattern.charAt(j))
                ++j;

            if (j == pattern.length())
                return i - pattern.length() + 1;
        }

        return -1;
    }

    // pattern表示模式串
    private static int[] next(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = -1;
        int k = -1;

        for (int i = 1; i < pattern.length(); i++) {
            while (k != -1 && pattern.charAt(k + 1) != pattern.charAt(i))
                k = next[k];

            if (pattern.charAt(k + 1) == pattern.charAt(i))
                ++k;

            next[i] = k;
        }

        return next;
    }

    public static void main(String[] args) {
        String pattern = "ABABAC";
        String text = "BCBAABACAABABACAA";

        int offset = AbcOfStringMatch.kmp(text, pattern);
        System.out.println("next method:");
        System.out.println("text:    " + text);
        System.out.print("pattern: ");
        for (int i = 0; i < offset; i++) {
            System.out.print(" ");
        }
        System.out.println(pattern);
    }
}
