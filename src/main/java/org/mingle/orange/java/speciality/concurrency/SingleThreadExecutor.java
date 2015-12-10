/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一个Thread
 *
 * @since 1.8
 * @author Mingle
 */
public class SingleThreadExecutor {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++)
			exec.execute(new LiftOff());
		exec.shutdown();	// 防止新任务提交给这个Executor
	}

}
