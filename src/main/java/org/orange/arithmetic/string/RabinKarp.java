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

import java.math.BigInteger;
import java.util.Random;

/**
 * The {@code RabinKarp} class finds the first occurrence of a pattern string
 * in a text string.
 * <p>
 * This implementation uses the Rabin-Karp algorithm.
 * <p>
 *
 * @author mingle
 */
public class RabinKarp {
    private final String pattern;      // the pattern
    private final long patternHash;    // pattern hash value
    private final int patternLength;   // pattern length
    private final long prime;          // a large prime, small enough to avoid long overflow
    private final int radix;           // radix
    private long rm;                  // R^(M-1) % Q

    /**
     * Preprocesses the pattern string.
     *
     * @param pattern the pattern string
     */
    public RabinKarp(String pattern) {
        this.pattern = pattern;
        radix = 256;
        patternLength = pattern.length();
        prime = longRandomPrime();

        // precompute R^(m-1) % q for use in removing leading digit
        rm = 1;
        for (int i = 1; i <= patternLength - 1; i++)
            rm = (radix * rm) % prime;
        patternHash = hash(pattern, patternLength);
    }

    // Compute hash for key[0..m-1].
    private long hash(String key, int m) {
        long h = 0;
        for (int j = 0; j < m; j++)
            h = (radix * h + key.charAt(j)) % prime;
        return h;
    }

    private boolean check(String txt, int i) {
        for (int j = 0; j < patternLength; j++)
            if (pattern.charAt(j) != txt.charAt(i + j))
                return false;
        return true;
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
        int n = txt.length();
        if (n < patternLength) return n;
        long txtHash = hash(txt, patternLength);

        // check for match at offset 0
        if ((patternHash == txtHash) && check(txt, 0))
            return 0;

        // check for hash match; if hash match, check for exact match
        for (int i = patternLength; i < n; i++) {
            // Remove leading digit, add trailing digit, check for match.
            txtHash = (txtHash + prime - rm * txt.charAt(i - patternLength) % prime) % prime;
            txtHash = (txtHash * radix + txt.charAt(i)) % prime;

            // match
            int offset = i - patternLength + 1;
            if ((patternHash == txtHash) && check(txt, offset))
                return offset;
        }

        // no match
        return n;
    }

    // a random 31-bit prime
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    public static void main(String[] args) {
        String pattern = "acbd";
        String text = "abjoijljacbdsdfacbdlll";

        RabinKarp searcher = new RabinKarp(pattern);
        int offset = searcher.search(text);

        System.out.println("text:    " + text);
        System.out.print("pattern: ");
        for (int i = 0; i < offset; i++) {
            System.out.print(" ");
        }
        System.out.println(pattern);
    }
}
