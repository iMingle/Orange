/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.benchmark;

/**
 * 剔除无效代码
 * 
 * @since 1.0
 * @author Mingle
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
        long startTime = System.nanoTime();

        for (double i = 0; i < iterations; i++)
            calcFibonacci(NUMBER); // no-op(操作或操作序列对程序的输出状态没有任何影响)无效代码

        long elapsedTime = System.nanoTime() - startTime;
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
