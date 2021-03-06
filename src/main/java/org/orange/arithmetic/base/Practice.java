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

package org.orange.arithmetic.base;

public class Practice {

    public static double[][] array = new double[100][51];

    public static int[] histogram(int[] a, int m) {
        int[] arr = new int[m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i == a[j]) {
                    arr[i]++;
                }
            }
        }

        return arr;
    }

    public static String exR2(int n) {
        String s = exR2(n - 3) + n + exR2(n - 2) + n;
        System.out.println("k");
        if (n <= 0)
            return "";
        return s;
    }

    public static int mystery(int a, int b) {
        if (b == 0)
            return 0;
        if (b % 2 == 0)
            return mystery(a + a, b / 2);
        return mystery(a + a, b / 2) + a;
    }

    public static double binomial(int N, int k, double p) {
        if ((N == 0) && (k == 0))
            return array[0][0] = 1.0;
        if ((N < 0) || (k < 0))
            return 0.0;
        System.out.println("====");
        return array[N][k] = (1.0 - p) * array[N - 1][k] + p
                * array[N - 1][k - 1];
    }

    public static void main(String[] args) {
        double xlo = Double.parseDouble("0.2");
        double xhi = Double.parseDouble("0.5");
        double ylo = Double.parseDouble("0.5");
        double yhi = Double.parseDouble("0.6");
        int T = Integer.parseInt("10000");
        Interval1D xinterval = new Interval1D(xlo, xhi);
        Interval1D yinterval = new Interval1D(ylo, yhi);
        Interval2D box = new Interval2D(xinterval, yinterval);
        box.draw();
        Counter c = new Counter("hits");
        for (int t = 0; t < T; t++) {
            double x = Math.random();
            double y = Math.random();
            Point2D p = new Point2D(x, y);
            if (box.contains(p))
                c.increment();
            else
                p.draw();
        }
        System.out.println(c);
        System.out.println(box.area());
    }
}
