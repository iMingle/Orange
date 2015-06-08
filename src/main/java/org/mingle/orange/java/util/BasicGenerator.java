/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.util;

/**
 * 基本对象生成器
 * 
 * @since 1.8
 * @author Mingle
 */
public class BasicGenerator<T> implements Generator<T> {
	private Class<T> type;

	public BasicGenerator(Class<T> type) {
		this.type = type;
	}

	public T next() {
		try {
			// Assumes type is a public class:
			return type.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// Produce a Default generator given a type token:
	public static <T> Generator<T> create(Class<T> type) {
		return new BasicGenerator<T>(type);
	}
}
