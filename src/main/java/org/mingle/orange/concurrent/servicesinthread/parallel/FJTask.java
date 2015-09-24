/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.servicesinthread.parallel;

/**
 * fork/join
 * 
 * @since 1.8
 * @author Mingle
 */
public abstract class FJTask implements Runnable {
	abstract boolean isDone();					// True after task is run
	abstract void cancel();						// Prematurely set as done
	abstract void fork();						// Start a dependent task
	abstract void start();						// Start an arbitrary task
	abstract void yield();						// Allow another task to run
	abstract void join();						// Yield caller until done
	abstract void invoke(FJTask t);				// Directly run t
	abstract void coInvoke(FJTask t, FJTask u);	// Fork and join t and u
	abstract void coInvoke(FJTask[] tasks);		// coInvoke all
	abstract void reset();						// Clear to allow reuse
}
