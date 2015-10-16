/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.test;

import java.util.concurrent.Semaphore;

/**
 * 基于信号量的有界缓存
 * 
 * @since 1.8
 * @author Mingle
 */
public class SemaphoreBoundedBuffer<E> {
	private final Semaphore availableItems, availableSpaces;
	private final E[] items;
	private int putPosition = 0, takePosition = 0;
	
	@SuppressWarnings("unchecked")
	public SemaphoreBoundedBuffer(int capacity) {
		availableItems = new Semaphore(0);
		availableSpaces = new Semaphore(capacity);
		items = (E[]) new Object[capacity];
	}
	
	public boolean isEmpty() {
		return availableItems.availablePermits() == 0;
	}
	
	public boolean isFull() {
		return availableSpaces.availablePermits() == 0;
	}
	
	public void put(E x) throws InterruptedException {
		availableSpaces.acquire();
		doInsert(x);
		availableItems.release();
	}
	
	public E take() throws InterruptedException {
		availableItems.acquire();
		E item = doExtract();
		availableSpaces.release();
		return item;
	}

	private synchronized void doInsert(E x) {
		int i = putPosition;
		items[i] = x;
		putPosition = (++i == items.length) ? 0 : i;
	}
	
	private synchronized E doExtract() {
		int i = takePosition;
		E x = items[i];
		items[i] = null;
		takePosition = (++i == items.length) ? 0 : i;
		return x;
	}
}
