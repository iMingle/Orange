/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.sync;

/**
 * 死锁
 * 
 * @since 1.8
 * @author Mingle
 */
public class Cell {
	private long value;
	
	public synchronized long getValue() {
		return value;
	}

	public synchronized void setValue(long v) {
		value = v;
	}

	/**
	 * 会造成死锁,a.swapValue(b)和b.swapValue(a)
	 * 
	 * @param other
	 */
	public synchronized void swapValue(Cell other) {
		long t = getValue();
		long v = other.getValue();
		setValue(v);
		other.setValue(t);
	}
}
