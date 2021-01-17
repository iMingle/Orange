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

import java.util.Random;

/**
 * 随机数工具
 *
 * @author mingle
 */
public final class RandomUtil {
    private static Random random;
    private static long seed = System.currentTimeMillis();

    static {
        random = new Random(seed);
    }

    private RandomUtil() {
    }

    public static void setSeed(long s) {
        seed = s;
        random = new Random(seed);
    }

    public static long getSeed() {
        return seed;
    }

    public static double uniform() {
        return random.nextDouble();
    }

    public static int uniform(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("argument must be positive");
        } else {
            return random.nextInt(n);
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static double random() {
        return uniform();
    }

    public static int uniform(int a, int b) {
        if (b > a && (long) b - (long) a < 2147483647L) {
            return a + uniform(b - a);
        } else {
            throw new IllegalArgumentException("invalid range: [" + a + ", " + b + "]");
        }
    }

    public static double uniform(double a, double b) {
        if (a >= b) {
            throw new IllegalArgumentException("invalid range: [" + a + ", " + b + "]");
        } else {
            return a + uniform() * (b - a);
        }
    }

    public static boolean bernoulli(double p) {
        if (p >= 0.0D && p <= 1.0D) {
            return uniform() < p;
        } else {
            throw new IllegalArgumentException("probability p must be between 0.0 and 1.0");
        }
    }

    public static boolean bernoulli() {
        return bernoulli(0.5D);
    }

    public static double gaussian() {
        double r;
        double x;
        do {
            x = uniform(-1.0D, 1.0D);
            double y = uniform(-1.0D, 1.0D);
            r = x * x + y * y;
        } while (r >= 1.0D || r == 0.0D);

        return x * Math.sqrt(-2.0D * Math.log(r) / r);
    }

    public static double gaussian(double mu, double sigma) {
        return mu + sigma * gaussian();
    }

    public static int geometric(double p) {
        if (p >= 0.0D && p <= 1.0D) {
            return (int) Math.ceil(Math.log(uniform()) / Math.log(1.0D - p));
        } else {
            throw new IllegalArgumentException("probability p must be between 0.0 and 1.0");
        }
    }

    public static int poisson(double lambda) {
        if (lambda <= 0.0D) {
            throw new IllegalArgumentException("lambda must be positive");
        } else if (Double.isInfinite(lambda)) {
            throw new IllegalArgumentException("lambda must not be infinite");
        } else {
            int k = 0;
            double p = 1.0D;
            double expLambda = Math.exp(-lambda);

            do {
                ++k;
                p *= uniform();
            } while (p >= expLambda);

            return k - 1;
        }
    }

    public static double pareto() {
        return pareto(1.0D);
    }

    public static double pareto(double alpha) {
        if (alpha <= 0.0D) {
            throw new IllegalArgumentException("alpha must be positive");
        } else {
            return Math.pow(1.0D - uniform(), -1.0D / alpha) - 1.0D;
        }
    }

    public static double cauchy() {
        return Math.tan(3.141592653589793D * (uniform() - 0.5D));
    }

    public static int discrete(double[] probabilities) {
        if (probabilities == null) {
            throw new IllegalArgumentException("argument array is null");
        } else {
            double EPSILON = 1.0E-14D;
            double sum = 0.0D;

            for (int i = 0; i < probabilities.length; ++i) {
                if (probabilities[i] < 0.0D) {
                    throw new IllegalArgumentException("array entry " + i + " must be nonnegative: " + probabilities[i]);
                }

                sum += probabilities[i];
            }

            if (sum <= 1.0D + EPSILON && sum >= 1.0D - EPSILON) {
                while (true) {
                    double r = uniform();
                    sum = 0.0D;

                    for (int i = 0; i < probabilities.length; ++i) {
                        sum += probabilities[i];
                        if (sum > r) {
                            return i;
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("sum of array entries does not approximately equal 1.0: " + sum);
            }
        }
    }

    public static int discrete(int[] frequencies) {
        if (frequencies == null) {
            throw new IllegalArgumentException("argument array is null");
        } else {
            long sum = 0L;

            for (int i = 0; i < frequencies.length; ++i) {
                if (frequencies[i] < 0) {
                    throw new IllegalArgumentException("array entry " + i + " must be nonnegative: " + frequencies[i]);
                }

                sum += (long) frequencies[i];
            }

            if (sum == 0L) {
                throw new IllegalArgumentException("at least one array entry must be positive");
            } else if (sum >= 2147483647L) {
                throw new IllegalArgumentException("sum of frequencies overflows an int");
            } else {
                double r = (double) uniform((int) sum);
                sum = 0L;

                for (int i = 0; i < frequencies.length; ++i) {
                    sum += (long) frequencies[i];
                    if ((double) sum > r) {
                        return i;
                    }
                }

                assert false;

                return -1;
            }
        }
    }

    public static double exp(double lambda) {
        if (lambda <= 0.0D) {
            throw new IllegalArgumentException("lambda must be positive");
        } else {
            return -Math.log(1.0D - uniform()) / lambda;
        }
    }

    public static void shuffle(Object[] a) {
        if (a == null) {
            throw new IllegalArgumentException("argument array is null");
        } else {
            int n = a.length;

            for (int i = 0; i < n; ++i) {
                int r = i + uniform(n - i);
                Object temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }

        }
    }

    public static void shuffle(double[] a) {
        if (a == null) {
            throw new IllegalArgumentException("argument array is null");
        } else {
            int n = a.length;

            for (int i = 0; i < n; ++i) {
                int r = i + uniform(n - i);
                double temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }

        }
    }

    public static void shuffle(int[] a) {
        if (a == null) {
            throw new IllegalArgumentException("argument array is null");
        } else {
            int n = a.length;

            for (int i = 0; i < n; ++i) {
                int r = i + uniform(n - i);
                int temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }

        }
    }

    public static void shuffle(Object[] a, int lo, int hi) {
        if (a == null) {
            throw new IllegalArgumentException("argument array is null");
        } else if (lo >= 0 && lo <= hi && hi < a.length) {
            for (int i = lo; i <= hi; ++i) {
                int r = i + uniform(hi - i + 1);
                Object temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }

        } else {
            throw new IndexOutOfBoundsException("invalid subarray range: [" + lo + ", " + hi + "]");
        }
    }

    public static void shuffle(double[] a, int lo, int hi) {
        if (a == null) {
            throw new IllegalArgumentException("argument array is null");
        } else if (lo >= 0 && lo <= hi && hi < a.length) {
            for (int i = lo; i <= hi; ++i) {
                int r = i + uniform(hi - i + 1);
                double temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }

        } else {
            throw new IndexOutOfBoundsException("invalid subarray range: [" + lo + ", " + hi + "]");
        }
    }

    public static void shuffle(int[] a, int lo, int hi) {
        if (a == null) {
            throw new IllegalArgumentException("argument array is null");
        } else if (lo >= 0 && lo <= hi && hi < a.length) {
            for (int i = lo; i <= hi; ++i) {
                int r = i + uniform(hi - i + 1);
                int temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }

        } else {
            throw new IndexOutOfBoundsException("invalid subarray range: [" + lo + ", " + hi + "]");
        }
    }
}
