/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.sync;

/**
 * 静态计数器
 * 
 * @since 1.8
 * @author Mingle
 */
public class StaticCounter {
	private static final long initial = Math.abs(new java.util.Random().nextLong() / 2);
	private static long count = initial;

	private StaticCounter() {}

	public static synchronized long next() {
		return count++;
	}

	public static synchronized void reset() {
		count = initial;
	}
}
