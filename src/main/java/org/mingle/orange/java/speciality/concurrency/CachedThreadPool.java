/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用Executor管理Thread,需要时就创建新的线程
 *
 * @since 1.8
 * @author Mingle
 */
public class CachedThreadPool {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new LiftOff());
		exec.shutdown();	// 防止新任务提交给这个Executor
	}

}
