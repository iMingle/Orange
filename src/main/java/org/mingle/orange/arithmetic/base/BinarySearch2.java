package org.mingle.orange.arithmetic.base;

/*************************************************************************
 *  Compilation:  javac BinarySearch.java
 *  Execution:    java BinarySearch whitelist.txt < input.txt
 *  Data files:   http://algs4.cs.princeton.edu/11model/tinyW.txt
 *                http://algs4.cs.princeton.edu/11model/tinyT.txt
 *                http://algs4.cs.princeton.edu/11model/largeW.txt
 *                http://algs4.cs.princeton.edu/11model/largeT.txt
 *
 *  % java BinarySearch tinyW.txt < tinyT.txt
 *  50
 *  99
 *  13
 *
 *  % java BinarySearch largeW.txt < largeT.txt | more
 *  499569
 *  984875
 *  295754
 *  207807
 *  140925
 *  161828
 *  [3,675,966 total values]
 *
 *************************************************************************/


public class BinarySearch2 {

    public static int rank(int key, int[] a) {
        int time = 0;

        return rank(key, a, 0, a.length - 1, time);
    }

    public static int rank(int key, int[] a, int lo, int hi, int time) {
        System.out.println("lo = " + lo);
        System.out.println("hi = " + hi);
        time++;
        System.out.println("time = " + time);
        System.out.println("=========");
        if (lo > hi)
            return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid])
            return rank(key, a, lo, mid - 1, time);
        else if (key > a[mid])
            return rank(key, a, mid + 1, hi, time);
        else
            return mid;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        rank(3, array);
    }
}
