/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.algs4.StdIn;

/**
 * 最小公倍数
 * 
 * @since 1.0
 * @author Mingle
 */
public class Gcd {

    public static int gcd(int p, int q) {
        if (q == 0)
            return p;
        int r = p % q;
        return gcd(q, r);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int a = StdIn.readInt();
        int b = StdIn.readInt();
        
        Gcd.gcd(a, b);
    }

}
