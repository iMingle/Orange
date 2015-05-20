/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.test;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class MethodTest {
	
	/**
	 * test if parameter will change.
	 * @param id
	 * @return
	 */
	public static Long returnLong(Long id) {
		id += 1;
		return id;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Long id = 12L;
		
		System.out.println(MethodTest.returnLong(id));
		System.out.println("id = " + id);
	}

}
