/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.state;

/**
 * 通知
 * 
 * @since 1.8
 * @author Mingle
 */
public class SimpleBoundedCounter {
	static final long MIN = 0; // minimum allowed value

	static final long MAX = 10; // maximum allowed value

	protected long count = MIN;

	public synchronized long count() {
		return count;
	}

	public synchronized void inc() throws InterruptedException {
		awaitUnderMax();
		setCount(count + 1);
	}

	public synchronized void dec() throws InterruptedException {
		awaitOverMin();
		setCount(count - 1);
	}

	protected void setCount(long newValue) { // PRE: lock held
		count = newValue;
		notifyAll(); // wake up any thread depending on new value
	}

	protected void awaitUnderMax() throws InterruptedException {
		while (count == MAX)
			wait();
	}

	protected void awaitOverMin() throws InterruptedException {
		while (count == MIN)
			wait();
	}
}
