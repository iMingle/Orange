/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

/**
 * 数字产生器
 *
 * @since 1.8
 * @author Mingle
 */
public abstract class IntGenerator {
	private volatile boolean canceled = false;
	
	public abstract int next();
	
	public void cancel() {
		canceled = true;
	}
	
	public boolean isCanceled() {
		return canceled;
	}
}