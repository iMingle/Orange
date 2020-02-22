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

package org.orange.arithmetic.util;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 求解最大子序列
 *
 * Benchmark                Mode  Cnt         Score         Error  Units
 * MaxSubsequence.testMax0  avgt   10  95172931.773 ± 1540205.532  ns/op
 * MaxSubsequence.testMax1  avgt   10    340716.960 ±   22869.548  ns/op
 * MaxSubsequence.testMax2  avgt   10     21217.958 ±    1278.862  ns/op
 * MaxSubsequence.testMax3  avgt   10       990.700 ±      51.135  ns/op
 *
 * @author mingle
 */
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@Measurement(iterations = 10)
@Warmup(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
public class MaxSubsequence {
    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>(SIZE);

        ThreadLocalRandom random = ThreadLocalRandom.current();
        random.ints(SIZE, -10, 10).parallel().forEach(value -> data.add(value));
        System.out.println(data);
    }

    private static final int SIZE = 1000;
    private static final List<Integer> data = new ArrayList<>(SIZE);

    @Setup
    public void setup() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        random.ints(SIZE, -10, 10).parallel().forEach(value -> data.add(value));
    }

    @Benchmark
    public static int testMax0() {
        return max0(data);
    }

    @Benchmark
    public static int testMax1() {
        return max1(data);
    }

    @Benchmark
    public static int testMax2() {
        return max2(data);
    }

    @Benchmark
    public static int testMax3() {
        return max3(data);
    }

    /**
     * O(N*N*N)
     *
     * @param data
     * @return
     */
    public static int max0(List<Integer> data) {
        int maxSum = 0;
        int length = data.size();

        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                int thisSum = 0;

                for (int k = i; k <= j; k++)
                    thisSum += data.get(k);

                if (thisSum > maxSum)
                    maxSum = thisSum;
            }
        }

        return maxSum;
    }

    /**
     * O(N*N)
     *
     * @param data
     * @return
     */
    public static int max1(List<Integer> data) {
        int maxSum = 0;
        int length = data.size();

        for (int i = 0; i < length; i++) {
            int thisSum = 0;
            for (int j = i; j < length; j++) {
                thisSum += data.get(j);

                if (thisSum > maxSum)
                    maxSum = thisSum;
            }
        }

        return maxSum;
    }

    /**
     * O(NlogN)
     *
     * @param data
     * @return
     */
    public static int max2(List<Integer> data) {
        return maxRecursive(data, 0, data.size() - 1);
    }

    private static int maxRecursive(List<Integer> data, int left, int right) {
        if (left == right) {
            if (data.get(left) > 0)
                return data.get(left);
            else
                return 0;
        }

        int center = left + (right - left) / 2;
        int maxLeft = maxRecursive(data, left, center);
        int maxRight = maxRecursive(data, center + 1, right);

        int maxLeftBorder = 0;
        int leftBorder = 0;
        for (int i = center; i > left; i--) {
            leftBorder += data.get(i);
            if (leftBorder > maxLeftBorder)
                maxLeftBorder = leftBorder;
        }

        int maxRightBorder = 0;
        int rightBorder = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorder += data.get(i);
            if (rightBorder > maxRightBorder)
                maxRightBorder = rightBorder;
        }

        return Math.max(Math.max(maxLeft, maxRight), maxLeftBorder + maxRightBorder);
    }

    /**
     * O(N)
     *
     * @param data
     * @return
     */
    public static int max3(List<Integer> data) {
        int maxSum = 0;
        int thisSum = 0;
        int length = data.size();

        for (int i = 0; i < length; i++) {
            thisSum += data.get(i);
            if (thisSum > maxSum)
                maxSum = thisSum;
            else if (thisSum < 0)
                thisSum = 0;
        }

        return maxSum;
    }
}
