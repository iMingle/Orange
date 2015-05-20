/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 从任务中产生返回值
 *
 * @since 1.8
 * @author Mingle
 */
public class CallableDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<String>> results = new ArrayList<>();
		for (int i = 0; i < 10; i++)
			results.add(exec.submit(new TaskWithResult(i)));
		for (Future<String> fs : results)
			try {
				System.out.println(fs.get());
			} catch (ExecutionException | InterruptedException e) {
				System.out.println(e);
			} finally {
				exec.shutdown();
			}
	}
}

class TaskWithResult implements Callable<String> {
	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public String call() throws Exception {
		return "result of TaskWithResult " + id;
	}

}