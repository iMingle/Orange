/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.exception;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class AssertTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a = 3;
		assert a > 3;
		assert a > 3 : "assert test";
	}

}
