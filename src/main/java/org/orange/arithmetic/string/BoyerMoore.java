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

/**
 * Boyer-Moore字符串搜索算法
 *
 * <p>
 * The {@code BoyerMoore} class finds the first occurrence of a pattern string
 * in a text string.
 * <p>
 * This implementation uses the Boyer-Moore algorithm (with the bad-character
 * rule, but not the strong good suffix rule).
 * <p>
 *
 * @author mingle
 */
public class BoyerMoore {
    private final int radix;    // the radix
    private final int[] right;  // the bad-character skip array
    private char[] pattern;    // store the pattern as a character array
    private String pat;        // or as a string

    /**
     * Preprocesses the pattern string.
     *
     * @param pat the pattern string
     */
    public BoyerMoore(String pat) {
        this.radix = 256;
        this.pat = pat;

        // position of rightmost occurrence of c in the pattern
        right = new int[radix];
        for (int c = 0; c < radix; c++)
            right[c] = -1;
        for (int j = 0; j < pat.length(); j++)
            right[pat.charAt(j)] = j;
    }

    /**
     * Preprocesses the pattern string.
     *
     * @param pattern the pattern string
     * @param radix   the alphabet size
     */
    public BoyerMoore(char[] pattern, int radix) {
        this.radix = radix;
        this.pattern = new char[pattern.length];
        for (int j = 0; j < pattern.length; j++)
            this.pattern[j] = pattern[j];

        // position of rightmost occurrence of c in the pattern
        right = new int[radix];
        for (int c = 0; c < radix; c++)
            right[c] = -1;
        for (int j = 0; j < pattern.length; j++)
            right[pattern[j]] = j;
    }

    /**
     * Returns the index of the first occurrence of the pattern string
     * in the text string.
     *
     * @param txt the text string
     * @return the index of the first occurrence of the pattern string
     * in the text string; n if no such match
     */
    public int search(String txt) {
        int m = pat.length();
        int n = txt.length();
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = Math.max(1, j - right[txt.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0) return i;
        }
        return n;
    }

    /**
     * Returns the index of the first occurrence of the pattern string
     * in the text string.
     *
     * @param text the text string
     * @return the index of the first occurrence of the pattern string
     * in the text string; n if no such match
     */
    public int search(char[] text) {
        int m = pattern.length;
        int n = text.length;
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (pattern[j] != text[i + j]) {
                    skip = Math.max(1, j - right[text[i + j]]);
                    break;
                }
            }
            if (skip == 0) return i;
        }
        return n;
    }

    public static void main(String[] args) {
//        String pattern = "acbd";
//        String text = "abjoijljacbdsdfacbdlll";

        String pattern = "cbacabc";
        String text = "abcacabcbcbacabc";

        BoyerMoore searcher = new BoyerMoore(pattern);
        int offset = searcher.search(text);

        System.out.println("text:    " + text);
        System.out.print("pattern: ");
        for (int i = 0; i < offset; i++) {
            System.out.print(" ");
        }
        System.out.println(pattern);
    }
}
