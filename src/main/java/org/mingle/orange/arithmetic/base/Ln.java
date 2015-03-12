package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Ln {
	
	public static double ln(int n) {
		if (1 == n) return 0;
		
		return Math.log(n) + ln(n - 1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String s = StdIn.readString();
		double a = StdIn.readInt();
		double b = StdIn.readInt();
		
		StdOut.printf("%s, %f, %f, %.3f", s, a, b, a / b);
		
//		System.out.println(s);
		
	}

}
