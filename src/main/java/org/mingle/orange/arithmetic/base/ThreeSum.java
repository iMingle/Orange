package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class ThreeSum {
    public static int count(int[] a) { // Count triples that sum to 0.
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0)
                        cnt++;
        return cnt;
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        String[] s = In.readStrings("1.txt");
        int[] a = new int[s.length];

        for (int i = 0; i < s.length; i++) {
            a[i] = Integer.parseInt(s[i]);
        }

        System.out.println(a.length);
        int count = 0;

        Arrays.sort(a);

        for (int i = 0; i < a.length; i++) {
            if (BinarySearch.rank0(-a[i], a) > i) {
                count++;
            }
        }

        System.out.println(count);
    }
}
