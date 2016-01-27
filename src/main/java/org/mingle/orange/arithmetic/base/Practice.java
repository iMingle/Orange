package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.introcs.StdOut;

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
        StdOut.println(c);
        StdOut.println(box.area());
    }
}
