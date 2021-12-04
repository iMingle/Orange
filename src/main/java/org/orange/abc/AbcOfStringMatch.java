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

import java.math.BigInteger;
import java.util.Random;

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

    private static int kmp1(String text, String pattern) {
        int n = text.length();
        int j = 0;
        int[] next = next1(pattern);

        for (int i = 0; i < n; i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j))
                j = next[j - 1] + 1;

            if (text.charAt(i) == pattern.charAt(j))
                j++;

            if (j == pattern.length())
                return i - pattern.length() + 1;
        }

        return -1;
    }

    private static int[] next1(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = -1;
        int k = -1;

        for (int i = 1; i < pattern.length(); i++) {
            while (k != -1 && pattern.charAt(k + 1) != pattern.charAt(i))
                k = next[k];

            if (pattern.charAt(k + 1) == pattern.charAt(i))
                k++;

            next[i] = k;
        }

        return next;
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

    public static int rabinkarp(String text, String pattern) {
        int n = text.length();
        int patternLength = pattern.length();
        if (n < patternLength) return -1;
        int radix = 256;

        final long prime = BigInteger.probablePrime(31, new Random()).longValue();
        long rm = 1;
        long patternHash = hash(pattern, radix, patternLength, prime);
        long textHash = hash(text, radix, patternLength, prime);

        if (patternHash == textHash && check(text, pattern, 0)) return 0;

        for (int i = 1; i < patternLength; i++) {
            rm = (radix * rm) % prime;
        }

        for (int i = 0; i < n - patternLength; i++) {
            textHash = (textHash + prime - rm * text.charAt(i) % prime) % prime;
            textHash = (textHash * radix + text.charAt(i + patternLength)) % prime;

            int offset = i + 1;
            if (patternHash == textHash && check(text, pattern, offset)) return offset;
        }

        return -1;
    }

    private static long hash(String key, int radix, int m, long prime) {
        long h = 0;
        for (int i = 0; i < m; i++) {
            h = (radix * h + key.charAt(i)) % prime;
        }

        return h;
    }

    private static boolean check(String text, String pattern, int i) {
        for (int j = 0; j < pattern.length(); j++) {
            if (pattern.charAt(j) != text.charAt(i + j))
                return false;
        }

        return true;
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

        offset = AbcOfStringMatch.rabinkarp(text, pattern);
        System.out.println("next method:");
        System.out.println("text:    " + text);
        System.out.print("pattern: ");
        for (int i = 0; i < offset; i++) {
            System.out.print(" ");
        }
        System.out.println(pattern);
    }
}
