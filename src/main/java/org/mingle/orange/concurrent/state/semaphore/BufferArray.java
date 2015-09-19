/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.state.semaphore;

/**
 * 有界缓冲区
 * 
 * @since 1.8
 * @author Mingle
 */
public class BufferArray {
	protected final Object[] array; // the elements
	protected int putPtr = 0; // circular indices
	protected int takePtr = 0;

	BufferArray(int n) {
		array = new Object[n];
	}

	synchronized void insert(Object x) { // put mechanics
		array[putPtr] = x;
		putPtr = (putPtr + 1) % array.length;
	}

	synchronized Object extract() { // take mechanics
		Object x = array[takePtr];
		array[takePtr] = null;
		takePtr = (takePtr + 1) % array.length;
		return x;
	}
}
