package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.algs4.StdRandom;

public class Stopwatch {
    private final long start;
    
    public Stopwatch() {
        start = System.currentTimeMillis();
    }
    
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        
        return (now - start) / 1000.0;
    }
    
    public static void main(String[] args) {
        int[] a = new int[16000];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(-1000000, 1000000);
        }
        
        Stopwatch stopwatch = new Stopwatch();
        int count = ThreeSum.count(a);
        
        double time = stopwatch.elapsedTime();
        
        System.out.println(count + " triples " + time + " seconds");
    }
}
