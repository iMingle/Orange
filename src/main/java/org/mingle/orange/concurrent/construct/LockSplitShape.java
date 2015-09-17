/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.construct;

/**
 * 分解锁
 * 
 * @since 1.8
 * @author Mingle
 */
public class LockSplitShape {
	protected double x = 0.0;
	protected double y = 0.0;
	protected double width = 0.0;
	protected double height = 0.0;

	protected final Object locationLock = new Object();
	protected final Object dimensionLock = new Object();

	public double x() {
		synchronized (locationLock) {
			return x;
		}
	}

	public double y() {
		synchronized (locationLock) {
			return y;
		}
	}

	public void adjustLocation() {
		synchronized (locationLock) {
			x = 1; // longCalculation1();
			y = 2; // longCalculation2();
		}
	}
}
