package org.mingle.orange.arithmetic.sort;

import edu.princeton.cs.algs4.In;

public class Shell {
    
    @SuppressWarnings("rawtypes")
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        
        while (h < N / 3) h = h * 3 + 1;
        
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = h; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            
            h /= 3;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    @SuppressWarnings({ "rawtypes" })
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    
    @SuppressWarnings("rawtypes")
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        
        System.out.println();
    }
    
    @SuppressWarnings("rawtypes")
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        
        return true;
    }
    
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        String[] s = In.readStrings();
        sort(s);
        
        assert isSorted(s);
        
        show(s);
    }
}
