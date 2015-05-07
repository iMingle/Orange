/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.annotations;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class Multiply {
	public int multiply(int x, int y) {
		int total = 0;
		for (int i = 0; i < x; i++) {
			total = add(total, y);
		}
		return total;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	private int add(int x, int y) {
		return x + y;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Multiply m = new Multiply();
		System.out.println("11 * 16 = " + m.multiply(11, 16));
	}

}
