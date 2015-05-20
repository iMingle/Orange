/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.thread;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 原子的boolean type
 * 
 * @since 1.8
 * @author Mingle
 */
public class AtomicBooleanTest {
	private static boolean done = true;
	private static volatile boolean doneVolatile = true;
	private static AtomicBoolean atomicBoolean = new AtomicBoolean(true);
	
	/**
	 * has atomicity
	 */
	public static void flipDone() {
		done = !done;
	}
	
	/**
	 * volatile has atomicity
	 */
	public static void flipDoneVolatile() {
		doneVolatile = !doneVolatile;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AtomicBooleanTest.flipDone();
		System.out.println("done is " + done);
		AtomicBooleanTest.flipDoneVolatile();
		System.out.println("doneVolatile is " + doneVolatile);

		System.out.println(atomicBoolean.get());
	}

}
