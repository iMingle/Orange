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

package org.orange.arithmetic.string;

import java.util.Arrays;

/**
 * The {@code KMP} class finds the first occurrence of a pattern string
 * in a text string.
 * <p>
 * This implementation uses a version of the Knuth-Morris-Pratt substring search
 * algorithm. The version takes time proportional to <em>n</em> + <em>m R</em>
 * in the worst case, where <em>n</em> is the length of the text string,
 * <em>m</em> is the length of the pattern, and <em>R</em> is the alphabet size.
 * It uses extra space proportional to <em>m R</em>.
 * <p>
 *
 * @author mingle
 */
public class KMP {
    private final int radix;             // the radix
    private final int patternLength;     // length of pattern

    /**
     * 确定有限状态自动机(deterministic finite automaton)
     */
    private final int[][] dfa;           // the KMP automaton

    private int[] next;

    /**
     * Preprocesses the pattern string.
     *
     * @param pat the pattern string
     */
    public KMP(String pat) {
        this.radix = 256;
        this.patternLength = pat.length();

        // build DFA from pattern
        dfa = new int[radix][patternLength];
        dfa[pat.charAt(0)][0] = 1;
        for (int x = 0, j = 1; j < patternLength; j++) {
            for (int c = 0; c < radix; c++)
                dfa[c][j] = dfa[c][x];     // Copy mismatch cases.
            dfa[pat.charAt(j)][j] = j + 1; // Set match case.
            x = dfa[pat.charAt(j)][x];     // Update restart state.
        }

        // build next
        next(pat);
    }

    /**
     * Preprocesses the pattern string.
     *
     * @param pattern the pattern string
     * @param radix   the alphabet size
     */
    public KMP(char[] pattern, int radix) {
        this.radix = radix;
        this.patternLength = pattern.length;

        // build DFA from pattern
        int m = pattern.length;
        dfa = new int[radix][m];
        dfa[pattern[0]][0] = 1;
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < radix; c++)
                dfa[c][j] = dfa[c][x];     // Copy mismatch cases.
            dfa[pattern[j]][j] = j + 1;    // Set match case.
            x = dfa[pattern[j]][x];        // Update restart state.
        }

        // build next
        next(new String(pattern));
    }

    /**
     * Returns the index of the first occurrence of the pattern string
     * in the text string.
     *
     * @param txt the text string
     * @return the index of the first occurrence of the pattern string
     * in the text string; N if no such match
     */
    public int search(String txt) {
        // simulate operation of DFA on text
        int n = txt.length();
        int i, j;
        for (i = 0, j = 0; i < n && j < patternLength; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == patternLength) return i - patternLength;
        return n;
    }

    /**
     * Returns the index of the first occurrence of the pattern string
     * in the text string.
     *
     * @param text the text string
     * @return the index of the first occurrence of the pattern string
     * in the text string; N if no such match
     */
    public int search(char[] text) {
        // simulate operation of DFA on text
        int n = text.length;
        int i, j;
        for (i = 0, j = 0; i < n && j < patternLength; i++) {
            j = dfa[text[i]][j];
        }
        if (j == patternLength) return i - patternLength;
        return n;
    }

    // text, pattern分别是主串和模式串
    public int kmp(String text, String pattern) {
        int n = text.length();
        int j = 0;
        for (int i = 0; i < n; ++i) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) { // 一直找到text[i]和pattern[j]
                j = next[j - 1] + 1;
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                ++j;
            }
            if (j == patternLength) { // 找到匹配模式串的了
                return i - patternLength + 1;
            }
        }

        return -1;
    }

    // pattern表示模式串
    private void next(String pattern) {
        next = new int[patternLength];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < patternLength; ++i) {
            while (k != -1 && pattern.charAt(k + 1) != pattern.charAt(i)) {
                k = next[k];
            }
            if (pattern.charAt(k + 1) == pattern.charAt(i)) {
                ++k;
            }
            next[i] = k;
        }
    }

    public static void main(String[] args) {
        String pattern = "ABABAC";
        String text = "BCBAABACAABABACAA";

        int[][] dfa = new int[3][pattern.length()];
        dfa[pattern.charAt(0) - 'A'][0] = 1;

        int m = pattern.length();
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < 3; c++)
                dfa[c][j] = dfa[c][x];     // Copy mismatch cases.
            dfa[pattern.charAt(j) - 'A'][j] = j + 1;    // Set match case.
            System.out.println(Arrays.deepToString(dfa));
            System.out.println("x before: " + x);
            x = dfa[pattern.charAt(j) - 'A'][x];        // Update restart state.
            System.out.println("x after: " + x);
        }


        KMP searcher = new KMP(pattern);
        int offset = searcher.search(text);

        System.out.println("dfa method:");
        System.out.println("text:    " + text);
        System.out.print("pattern: ");
        for (int i = 0; i < offset; i++) {
            System.out.print(" ");
        }
        System.out.println(pattern);

        System.out.println();

        offset = searcher.kmp(text, pattern);
        System.out.println("next method:");
        System.out.println("text:    " + text);
        System.out.print("pattern: ");
        for (int i = 0; i < offset; i++) {
            System.out.print(" ");
        }
        System.out.println(pattern);
    }
}
