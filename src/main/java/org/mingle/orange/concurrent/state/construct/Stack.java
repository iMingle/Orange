/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.state.construct;

/**
 * 保障分层
 * 
 * @since 1.8
 * @author Mingle
 */
public class Stack {
	public synchronized boolean isEmpty() {
		return false; /* ... */
	}

	public synchronized void push(Object x) { /* ... */
	}

	public synchronized Object pop() throws StackEmptyException {
		if (isEmpty())
			throw new StackEmptyException();
		else
			return null;
	}
}