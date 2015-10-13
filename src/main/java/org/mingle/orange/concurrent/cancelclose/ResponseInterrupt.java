/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.cancelclose;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.mingle.orange.util.LaunderThrowable;

/**
 * 响应中断
 * 
 * @since 1.8
 * @author Mingle
 */
public class ResponseInterrupt {
	private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(2);
	private static final ExecutorService taskExec = Executors.newCachedThreadPool();
	
	/**
	 * 在外部线程中安排中断(不要这么做)
	 * 
	 * @param r
	 * @param timeout
	 * @param unit
	 * @throws InterruptedException
	 */
	public static void timedRun1(Runnable r, long timeout, TimeUnit unit)
			throws InterruptedException {
		Thread taskThread = Thread.currentThread();
		cancelExec.schedule(new Runnable() {
			
			@Override
			public void run() {
				taskThread.interrupt();
			}
		}, timeout, unit);
		r.run();
	}

	/**
	 * 在专门的线程中中断任务
	 * 
	 * @param r
	 * @param timeout
	 * @param unit
	 * @throws InterruptedException
	 */
	public static void timedRun2(Runnable r, long timeout, TimeUnit unit)
			throws InterruptedException {
		class RethrowableTask implements Runnable {
			private volatile Throwable t;
			@Override
			public void run() {
				try {
					r.run();
				} catch (Throwable t) {
					this.t = t;
				}
			}
			
			void rethrow() {
				if (t != null)
					throw LaunderThrowable.launderThrowable(t);
			}
		}
		
		RethrowableTask task = new RethrowableTask();
		Thread taskThread = new Thread(task);
		taskThread.start();
		cancelExec.schedule(new Runnable() {
			@Override
			public void run() {
				taskThread.interrupt();
			}
		}, timeout, unit);
		taskThread.join(unit.toMillis(timeout));	// 无法确定是因为线程正常退出还是超时退出
		task.rethrow();
	}

	/**
	 * 通过Future来取消任务
	 * 
	 * @param r
	 * @param timeout
	 * @param unit
	 * @throws InterruptedException
	 */
	public static void timedRun(Runnable r, long timeout, TimeUnit unit)
			throws InterruptedException {
		Future<?> task = taskExec.submit(r);
		try {
			task.get(timeout, unit);
		} catch (ExecutionException e) {
			// 重新抛出异常
			throw LaunderThrowable.launderThrowable(e.getCause());
		} catch (TimeoutException e) {
			// 超时取消任务
		} finally {
			task.cancel(true);	// 如果任务正在运行,那么将被中断
		}
	}
}
