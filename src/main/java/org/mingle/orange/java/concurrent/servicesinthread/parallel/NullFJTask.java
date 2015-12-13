/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.servicesinthread.parallel;

/**
 * FJTask的空实现
 * 
 * @since 1.8
 * @author Mingle
 */
public class NullFJTask extends FJTask {

	@Override
	public void run() {
		
	}

	@Override
	boolean isDone() {
		return false;
	}

	@Override
	void cancel() {
		
	}

	@Override
	void fork() {
		
	}

	@Override
	void start() {
		
	}

	@Override
	void yield() {
		
	}

	@Override
	void join() {
		
	}

	@Override
	void invoke(FJTask t) {
		
	}

	@Override
	void coInvoke(FJTask t, FJTask u) {
		
	}

	@Override
	void coInvoke(FJTask[] tasks) {
		
	}

	@Override
	void reset() {
		
	}

}
