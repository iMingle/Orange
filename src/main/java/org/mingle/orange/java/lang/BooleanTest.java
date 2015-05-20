/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.lang;

/**
 *
 * @since 1.8
 * @author Mingle
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
