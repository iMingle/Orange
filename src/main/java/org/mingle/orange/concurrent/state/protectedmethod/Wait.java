/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.state.protectedmethod;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class Wait {
	synchronized void w() throws InterruptedException {
		before();
		wait();
		after();
	}

	synchronized void n() {
		notifyAll();
	}

	void before() {}

	void after() {}
}
