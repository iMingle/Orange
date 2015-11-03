/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.util;

/**
 * 静态方法
 * 
 * @since 1.8
 * @author Mingle
 */
public class LaunderThrowable {

	/**
	 * Coerce an unchecked Throwable to a RuntimeException
	 * <p/>
	 * If the Throwable is an Error, throw it; if it is a RuntimeException
	 * return it, otherwise throw IllegalStateException
	 */
	public static RuntimeException launderThrowable(Throwable t) {
		if (t instanceof RuntimeException)
			return (RuntimeException) t;
		else if (t instanceof Error)
			throw (Error) t;
		else
			throw new IllegalStateException("Not unchecked", t);
	}
}