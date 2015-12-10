/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.atomic;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class SimulatedCAS {
	private int value;
	
	public synchronized int get() {
		return value;
	}
	
	public synchronized int compareAndSwap(int expectedValue, int newValue) {
		int oldValue = value;
		if (oldValue == expectedValue)
			value = newValue;
		return oldValue;
	}
	
	public synchronized boolean compareAndSet(int expectedValue, int newValue) {
		return expectedValue == compareAndSwap(expectedValue, newValue);
	}
}
