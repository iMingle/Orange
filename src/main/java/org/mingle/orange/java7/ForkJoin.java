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

package org.mingle.orange.java7;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

import static java.lang.Integer.parseInt;

/**
 * if (my portion of the work is small enough)
 *   do the work directly
 * else
 *   split my work into two pieces
 *   invoke the two pieces and wait for the results
 * 
 * @author mingle
 */
public class ForkJoin {}

class MergeSort {
    private final ForkJoinPool pool;

    private static class MergeSortTask extends RecursiveAction {
        private static final long serialVersionUID = -6470257404132422772L;
        
        private final int[] array;
        private final int low;
        private final int high;
        private static final int THRESHOLD = 8;

        /**
         * Creates a {@code MergeSortTask} containing the array and the bounds of the array
         *
         * @param array the array to sort
         * @param low the lower element to start sorting at
         * @param high the non-inclusive high element to sort to
         */
        protected MergeSortTask(int[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low <= THRESHOLD) {
                Arrays.sort(array, low, high);
            } else {
                int middle = low + ((high - low) >> 1);
                // Execute the sub tasks and wait for them to finish
                invokeAll(new MergeSortTask(array, low, middle), new MergeSortTask(array, middle, high));
                // Then merge the results
                merge(middle);
            }
        }

        /**
         * Merges the two sorted arrays this.low, middle - 1 and middle, this.high - 1
         * @param middle the index in the array where the second sorted list begins
         */
        private void merge(int middle) {
            if (array[middle - 1] < array[middle]) {
                return; // the arrays are already correctly sorted, so we can skip the merge
            }
            int[] copy = new int[high - low];
            System.arraycopy(array, low, copy, 0, copy.length);
            int copyLow = 0;
            int copyHigh = high - low;
            int copyMiddle = middle - low;

            for (int i = low, p = copyLow, q = copyMiddle; i < high; i++) {
                if (q >= copyHigh || (p < copyMiddle && copy[p] < copy[q]) ) {
                    array[i] = copy[p++];
                } else {
                    array[i] = copy[q++];
                }
            }
        }
    }

    /**
     * Creates a {@code MergeSort} containing a ForkJoinPool with the indicated parallelism level
     * @param parallelism the parallelism level used
     */
    public MergeSort(int parallelism) {
        pool = new ForkJoinPool(parallelism);
    }

    /**
     * Sorts all the elements of the given array using the ForkJoin framework
     * @param array the array to sort
     */
    public void sort(int[] array) {
        ForkJoinTask<Void> job = pool.submit(new MergeSortTask(array, 0, array.length));
        job.join();
    }
}

class MergeDemo {
    // Use a fixed seed to always get the same random values back
    private final Random random = new Random(759123751834L);
    private static final int ITERATIONS = 10;

    /**
     * Represents the formula {@code f(n) = start + (step * n)} for n = 0 & n < iterations
     */
    private static class Range {
        private final int start;
        private final int step;
        private final int iterations;

        private Range(int start, int step, int iterations) {
            this.start = start;
            this.step = step;
            this.iterations = iterations;
        }

        /**
         * Parses start, step and iterations from args
         * @param args the string array containing the arguments
         * @param start which element to start the start argument from
         * @return the constructed range
         */
        public static Range parse(String[] args, int start) {
            if (args.length < start + 3) {
                throw new IllegalArgumentException("Too few elements in array");
            }
            return new Range(parseInt(args[start]), parseInt(args[start + 1]), parseInt(args[start + 2]));
        }

        public int get(int iteration) {
            return start + (step * iteration);
        }

        public int getIterations() {
            return iterations;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(start).append(" ").append(step).append(" ").append(iterations);
            return builder.toString();
        }
    }

    /**
     * Wraps the different parameters that is used when running the MergeExample.
     * {@code sizes} represents the different array sizes
     * {@code parallelism} represents the different parallelism levels
     */
    private static class Configuration {
        private final Range sizes;
        private final Range parallelism;

        private final static Configuration defaultConfig = new Configuration(new Range(20000, 20000, 10),
                new Range(2, 2, 10));

        private Configuration(Range sizes, Range parallelism) {
            this.sizes = sizes;
            this.parallelism = parallelism;
        }

        /**
         * Parses the arguments and attempts to create a configuration containing the
         * parameters for creating the array sizes and parallelism sizes
         * @param args the input arguments
         * @return the configuration
         */
        public static Configuration parse(String[] args) {
            if (args.length == 0) {
                return defaultConfig;
            } else {
                try {
                    if (args.length == 6) {
                        return new Configuration(Range.parse(args, 0), Range.parse(args, 3));
                    }
                } catch (NumberFormatException e) {
                    System.err.println("MergeExample: error: Argument was not a number.");
                }
                System.err.println("MergeExample <size start> <size step> <size steps> <parallel start> <parallel step>" +
                        " <parallel steps>");
                System.err.println("example: MergeExample 20000 10000 3 1 1 4");
                System.err.println("example: will run with arrays of sizes 20000, 30000, 40000" +
                        " and parallelism: 1, 2, 3, 4");
                return null;
            }
        }

        /**
         * Creates an array for reporting the test result time in
         * @return an array containing {@code sizes.iterations * parallelism.iterations} elements
         */
        private long[][] createTimesArray() {
            return new long[sizes.getIterations()][parallelism.getIterations()];
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder("");
            if (this == defaultConfig) {
                builder.append("Default configuration. ");
            }
            builder.append("Running with parameters: ");
            builder.append(sizes);
            builder.append(" ");
            builder.append(parallelism);
            return builder.toString();
        }
    }

    /**
     * Generates an array of {@code elements} random elements
     * @param elements the number of elements requested in the array
     * @return an array of {@code elements} random elements
     */
    private int[] generateArray(int elements) {
        int[] array = new int[elements];
        for (int i = 0; i < elements; ++i) {
            array[i] = random.nextInt();
        }
        return array;
    }

    /**
     * Runs the test
     * @param config contains the settings for the test
     */
    private void run(Configuration config) {
        Range sizes = config.sizes;
        Range parallelism = config.parallelism;

        // Run a couple of sorts to make the JIT compile / optimize the code
        // which should produce somewhat more fair times
        warmup();

        long[][] times = config.createTimesArray();

        for (int size = 0; size < sizes.getIterations(); size++) {
            runForSize(parallelism, sizes.get(size), times, size);
        }

        printResults(sizes, parallelism, times);
    }

    /**
     * Prints the results as a table
     * @param sizes the different sizes of the arrays
     * @param parallelism the different parallelism levels used
     * @param times the median times for the different sizes / parallelism
     */
    private void printResults(Range sizes, Range parallelism, long[][] times) {
        System.out.println("Time in milliseconds. Y-axis: number of elements. X-axis parallelism used.");
        long[] sums = new long[times[0].length];
        System.out.format("%8s  ", "");
        for (int i = 0; i < times[0].length; i++) {
            System.out.format("%4d ", parallelism.get(i));
        }
        System.out.println("");
        for (int size = 0; size < sizes.getIterations(); size++) {
            System.out.format("%8d: ", sizes.get(size));
            for (int i = 0; i < times[size].length; i++) {
                sums[i] += times[size][i];
                System.out.format("%4d ", times[size][i]);
            }
            System.out.println("");
        }
        System.out.format("%8s: ", "Total");
        for (long sum : sums) {
            System.out.format("%4d ", sum);
        }
        System.out.println("");
    }

    private void runForSize(Range parallelism, int elements, long[][] times, int size) {
        for (int step = 0; step < parallelism.getIterations(); step++) {
            long time = runForParallelism(ITERATIONS, elements, parallelism.get(step));
            times[size][step] = time;
        }
    }

    /**
     * Runs <i>iterations</i> number of test sorts of a random array of <i>element</i> length
     * @param iterations number of iterations
     * @param elements number of elements in the random array
     * @param parallelism parallelism for the ForkJoin framework
     * @return the median time of runs
     */
    private long runForParallelism(int iterations, int elements, int parallelism) {
        MergeSort mergeSort = new MergeSort(parallelism);
        long[] times = new long[iterations];

        for (int i = 0; i < iterations; i++) {
            // Suggest the VM to run a garbage collection to reduce the risk of getting one
            // while running the test run
            System.gc();
            long start = System.currentTimeMillis();
            mergeSort.sort(generateArray(elements));
            times[i] = System.currentTimeMillis() - start;
        }

        return medianValue(times);
    }

    /**
     * Calculates the median value of the array
     * @param times array of times
     * @return the median value
     */
    private long medianValue(long[] times) {
        if (times.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        // Make a copy of times to avoid having side effects on the parameter value
        Arrays.sort(times.clone());
        long median = times[times.length / 2];
        if (times.length > 1 && times.length % 2 != 0) {
            median = (median + times[times.length / 2 + 1]) / 2;
        }
        return median;
    }

    /**
     * Generates 1000 arrays of 1000 elements and sorts them as a warmup
     */
    private void warmup() {
        MergeSort mergeSort = new MergeSort(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 1000; i++) {
            mergeSort.sort(generateArray(1000));
        }
    }

    public static void main(String[] args) {
        Configuration configuration = Configuration.parse(args);
        if (configuration == null) {
            System.exit(1);
        }
        System.out.println(configuration);
        new MergeDemo().run(configuration);
    }
}
