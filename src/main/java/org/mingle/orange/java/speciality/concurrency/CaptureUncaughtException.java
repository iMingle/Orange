/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 捕获线程异常
 *
 * @since 1.8
 * @author Mingle
 */
class ExceptionThread2 implements Runnable {

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("run() by " + t);
		System.out.println("eh = " + t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}
	
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

	/* (non-Javadoc)
	 * @see java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang.Thread, java.lang.Throwable)
	 */
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("caught " + e);
	}
	
}

class HandlerThreadFactory implements ThreadFactory {

	/* (non-Javadoc)
	 * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
	 */
	@Override
	public Thread newThread(Runnable r) {
		System.out.println(this + " creating new thread");
		Thread t = new Thread(r);
		System.out.println("Created " + t);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("eh = " + t.getUncaughtExceptionHandler());
		return t;
	}
	
}

public class CaptureUncaughtException {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
		exec.execute(new ExceptionThread2());
	}

}
