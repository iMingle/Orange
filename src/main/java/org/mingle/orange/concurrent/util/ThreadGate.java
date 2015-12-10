/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.util;

/**
 * 可重新关闭的阀门
 * 
 * @since 1.8
 * @author Mingle
 */
public class ThreadGate {
	// CONDITION-PREDICATE: opened-since(n) (isOpen || generation>n)
	private boolean isOpen;
	private int generation;

	public synchronized void close() {
		isOpen = false;
	}

	public synchronized void open() {
		++generation;
		isOpen = true;
		notifyAll();
	}

	// BLOCKS-UNTIL: opened-since(generation on entry)
	public synchronized void await() throws InterruptedException {
		int arrivalGeneration = generation;
		while (!isOpen && arrivalGeneration == generation)
			wait();
	}
}
