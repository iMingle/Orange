/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.state.latch;

/**
 * 闭锁变量,类中所有相关的变量都被定义为volatile或者它们的读写都在同步下执行
 * 
 * @since 1.8
 * @author Mingle
 */
public class LatchingThermometer {
	private volatile boolean ready; // latching
	private volatile float temperature;

	public double getReading() {
		while (!ready)
			Thread.yield();
		return temperature;
	}

	void sense(float t) { // called from sensor
		temperature = t;
		ready = true;
	}
}
