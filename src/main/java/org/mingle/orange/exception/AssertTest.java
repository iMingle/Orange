/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.exception;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @date 2014年7月16日
 * @version 1.0
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
