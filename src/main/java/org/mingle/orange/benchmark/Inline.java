/*
 *
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
 * imitations under the License.
 *
 */

package org.mingle.orange.benchmark;

/**
 * 内联优化
 * java -XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining -XX:MaxInlineSize=100 Inline
 * 
 * @author Mingle
 */
public class Inline {
    private static final long ITERATIONS = 5000000000L;
    private static final long WARMUP = 10000000L;
    private static final long NANOS_PER_MS = 1000L * 1000L;

    private static boolean equalsTest(String s) {
        boolean result = s.equals(s);

        return result;
    }

    private static long doTest(long iterations) {
        long startTime = System.nanoTime();

        for (long i = 0; i < iterations; i++)
            equalsTest("ABCDEFG");

        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    private static void printStats(long n, long nanos) {
        float itrsPerMs = 0;
        float millis = nanos / NANOS_PER_MS;
        if (millis != 0)
            itrsPerMs = n / millis;
        System.out.println("    Elapsed time in ms -> " + millis);
        System.out.println("    Iterations / ms ----> " + itrsPerMs);
    }

    public static void main(String[] args) {
        System.out.println("Warming up ...");
        long nanos = doTest(WARMUP);
        System.out.println("1st warm up done.");
        printStats(WARMUP, nanos);

        System.out.println("Starting 2nd Warm up ...");
        nanos = doTest(WARMUP);
        System.out.println("2st Warmup done.");
        printStats(WARMUP, nanos);

        System.out.println("Starting measurement interval ...");
        nanos = doTest(ITERATIONS);
        System.out.println("Measurement interval done.");
        System.out.println("Test completed.");
        printStats(ITERATIONS, nanos);
    }
}
