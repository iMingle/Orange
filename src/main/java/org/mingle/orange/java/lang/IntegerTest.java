/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.lang;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class IntegerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(0xffffffff));
		System.out.println(Integer.toUnsignedLong(0xffffffff));
		System.out.println(Integer.toUnsignedString(0xffffffff));
		System.out.println(Integer.valueOf(1));
		System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
		System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
	}

}
