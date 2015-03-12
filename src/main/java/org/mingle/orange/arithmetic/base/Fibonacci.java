package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.introcs.StdOut;

public class Fibonacci {
	
	public static int[] array = new int[100];

	public static long F(int N) {
		if (N == 0)
			return 0;
		if (N == 1)
			return 1;
		return F(N - 1) + F(N - 2);
	}
	
	public static long newF(int N) {
		if (N == 0)
			return array[0] = 0;
		if (N == 1)
			return array[1] = 1;
		
		return array[N] = array[N - 1] + array[N - 2];
	}

	public static void main(String[] args) {
		for (int N = 0; N < 100; N++)
			StdOut.println(N + " " + newF(N));
	}

}
