/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.util;

/**
 * 对值
 * 
 * @since 1.8
 * @author Mingle
 */
public class Pair<K, V> {
	public final K key;
	public final V value;

	public Pair(K k, V v) {
		key = k;
		value = v;
	}
}
