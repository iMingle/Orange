/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * try catch无法捕获线程异常
 *
 * @since 1.8
 * @author Mingle
 */
public class ExceptionThread implements Runnable {
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		throw new RuntimeException();
	}

	public static void main(String[] args) {
		try {
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());
		} catch (RuntimeException e) {
			System.out.println("Exception has been handled!");
		}
	}

}
