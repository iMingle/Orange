/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.sync;

import java.util.Random;

/**
 * 非懒加载单例模式
 * 
 * @since 1.8
 * @author Mingle
 */
public class EagerSingletonCounter {
	private final long initial;
	private long count;
	private static final EagerSingletonCounter s = new EagerSingletonCounter();
	
	public EagerSingletonCounter() {
		initial = Math.abs(new Random().nextLong() / 2);
		count = initial;
	}
	
	public static EagerSingletonCounter instance() {
		return s;
	}

	public synchronized long next() {
		return count++;
	}

	public synchronized void reset() {
		count = initial;
	}
}
