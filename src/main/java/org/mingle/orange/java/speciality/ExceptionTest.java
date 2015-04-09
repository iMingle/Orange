/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

/**
 * 异常测试类
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class ExceptionTest {
	
	public static void nullPointException(Integer i) {
		if (i == null)
			throw new NullPointerException();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer i = 1;
		i = null;
		try {
			ExceptionTest.nullPointException(i);
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		
	}

}
