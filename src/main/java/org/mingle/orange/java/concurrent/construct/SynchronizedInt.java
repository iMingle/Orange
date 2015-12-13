/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.construct;

/**
 * 隔离成员变量保证原子性
 * 
 * @since 1.8
 * @author Mingle
 */
public class SynchronizedInt {
	private int value;

	public SynchronizedInt(int value) {
		this.value = value;
	}

	public synchronized int get() {
		return value;
	}

	public synchronized int set(int value) {
		int oldValue = value;
		this.value = value;
		return oldValue;
	}

	public synchronized int increment() {
		return value++;
	}
}

class Person {
	protected final SynchronizedInt age = new SynchronizedInt(0);

	public int getAge() {
		return age.get();
	}

	public void birthday() {
		age.increment();
	}
}