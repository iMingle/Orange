/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.atomic;

/**
 * 非阻塞的计数器
 * 
 * @since 1.8
 * @author Mingle
 */
public class CasCounter {
	private SimulatedCAS value;
	
	public int getValue() {
		return value.get();
	}
	
	public int increment() {
		int v;
		
		do {
			v = value.get();
		} while (v != value.compareAndSwap(v, v + 1));
		
		return v + 1;
	}
}
