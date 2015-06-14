/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.util;

/**
 * 生成集合容器
 * 
 * @since 1.8
 * @author Mingle
 */
public class Generated {
	// Fill an existing array:
	public static <T> T[] array(T[] a, Generator<T> gen) {
		return new CollectionData<T>(gen, a.length).toArray(a);
	}

	// Create a new array:
	@SuppressWarnings("unchecked")
	public static <T> T[] array(Class<T> type, Generator<T> gen, int size) {
		T[] a = (T[]) java.lang.reflect.Array.newInstance(type, size);
		return new CollectionData<T>(gen, size).toArray(a);
	}
}