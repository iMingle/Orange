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

package org.mingle.orange.util.benchmark;

/**
 * 剔除无效代码
 * 
 * @author mingle
 */
public class DeadCode {
    private static final long NANOS_PER_MS = 1000000;
    private static final int NUMBER = 25;

    private static int calcFibonacci(int n) {
        int result = 1;
        int prev = -1;
        int sum = 0;

        for (int i = 0; i <= n; i++) {
            sum = prev + result;
            prev = result;
            result = sum;
        }

        return result;
    }

    private static void doTest(int iterations) {
        int answer = 0;
        long startTime = System.nanoTime();

        for (double i = 0; i < iterations; i++)
            answer = calcFibonacci(NUMBER);

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("    Answer -> " + answer); // 打印结果JIT则不会标记为无效代码
        System.out.println("    Elapsed nanoseconds -> " + elapsedTime);

        float millis = elapsedTime / NANOS_PER_MS;
        float itrsPerMs = 0;
        if (millis != 0)
            itrsPerMs = iterations / millis;

        System.out.println("    Iterations per ms -> " + itrsPerMs);
    }

    public static void main(String[] args) {
        int warmUpCycles = 1000000;
        int testCycles = 900000000;
        System.out.println("Warming up ...");
        doTest(warmUpCycles);
        System.out.println("1st Warmup done.");
        System.out.println("Starting 2nd Warm up ...");
        doTest(testCycles);
        System.out.println("2st Warmup done.");
        System.out.println("Starting measurement interval ...");
        doTest(testCycles);
        System.out.println("Measurement interval done.");
        System.out.println("Test completed.");
    }

}
