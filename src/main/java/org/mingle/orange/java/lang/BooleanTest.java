/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.lang;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class BooleanTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(true & true);
		System.out.println(true & false);
		System.out.println(false & false);
		System.out.println(true | true);
		System.out.println(true | false);
		System.out.println(false | false);
		System.out.println(false ^ false);
		System.out.println(!true);
	}

}
