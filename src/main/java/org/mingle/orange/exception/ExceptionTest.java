/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.exception;

import java.io.FileNotFoundException;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @date 2014年7月15日
 * @version 1.0
 */
public class ExceptionTest {
	
	public static void throwException() throws Exception {
		int a = 12;
		
		if (a > 13) throw new NumberFormatException();
	}

	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		try {
			ExceptionTest.throwException();
		} catch (Exception e) {
			Throwable th = new FileNotFoundException();
			th.initCause(e);
			throw th;
		}
	}

}
