/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java7;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class BinaryIntegerLiteral {
	public static void main(String[] args) {
		byte b1 = 0B00100001;
		byte b2 = 0X21;
		byte b3 = 041;
		byte b4 = 33;
		System.out.println((b1 == b2) && (b3 == b4));
		
		long maxInt = 21_474_836_47;
		System.out.println(Integer.MAX_VALUE == maxInt);
	}
}
