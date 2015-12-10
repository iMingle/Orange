/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.cancelclose;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 记录关闭之后取消的任务,可能出现误报问题
 * 
 * @since 1.8
 * @author Mingle
 */
public class TrackingExecutor extends AbstractExecutorService {
	private final ExecutorService exec;
	private final Set<Runnable> tasksCanceledAtShutdown = Collections.synchronizedSet(new HashSet<>());
	
	public TrackingExecutor(ExecutorService exec) {
		this.exec = exec;
	}
	
	public List<Runnable> getCancelledTasks() {
		if (!exec.isTerminated())
			throw new IllegalStateException("not shutdown");
		return new ArrayList<>(tasksCanceledAtShutdown);
	}
	
	@Override
	public void execute(Runnable runnable) {
		exec.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					runnable.run();
				} finally {
					if (isShutdown() && Thread.currentThread().isInterrupted())
						tasksCanceledAtShutdown.add(runnable);
				}
			}
		});
	}

	@Override
	public void shutdown() {
		exec.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		return exec.shutdownNow();
	}

	@Override
	public boolean isShutdown() {
		return exec.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		return exec.isTerminated();
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		return exec.awaitTermination(timeout, unit);
	}

}
