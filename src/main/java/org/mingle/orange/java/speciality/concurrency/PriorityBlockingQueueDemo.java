/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 优先级阻塞队列测试
 *
 * @since 1.8
 * @author Mingle
 */
class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
	private Random rand = new Random(47);
	private static int counter = 0;
	private final int id = counter++;
	private final int priority;
	protected static List<PrioritizedTask> sequence = new ArrayList<>();

	public PrioritizedTask(int priority) {
		this.priority = priority;
		sequence.add(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(PrioritizedTask o) {
		return priority < o.priority ? 1 : (priority > o.priority ? -1 : 0);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
		} catch (InterruptedException e) {
			// 可接受的退出方式
		}
	}
	
	public String toString() {
		return String.format("[%1$-4d]", priority) + " Task " + id;
	}
	
	public String summary() {
		return "(" + id + ":" + priority + ")";
	}
	
	public static class EndSentinel extends PrioritizedTask {
		private ExecutorService exec;

		public EndSentinel(ExecutorService e) {
			super(-1);
			exec = e;
		}
		
		public void run() {
			int count = 0;
			for (PrioritizedTask pt : sequence) {
				System.out.print(pt.summary() + " ");
				if (++count % 5 == 0)
					System.out.println();
			}
			System.out.println();
			System.out.println(this + " Calling shutdownNow()");
			exec.shutdownNow();
		}
	}
}

class PrioritizedTaskProducer implements Runnable {
	private Random rand = new Random(47);
	private Queue<Runnable> queue;
	private ExecutorService exec;

	public PrioritizedTaskProducer(Queue<Runnable> queue, ExecutorService exec) {
		this.queue = queue;
		this.exec = exec;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			queue.add(new PrioritizedTask(rand.nextInt(10)));
//			Thread.yield();
		}
		try {
			for (int i = 0; i < 20; i++) {
//				TimeUnit.MILLISECONDS.sleep(250);
				queue.add(new PrioritizedTask(10));
			}
			
			for (int i = 0; i < 10; i++) {
				queue.add(new PrioritizedTask(i));
			}
			queue.add(new PrioritizedTask.EndSentinel(exec));
		} catch (Exception e) {
			// 可接受的方式退出
		}
		System.out.println("Finished PrioritizedTaskProducer");
	}
	
}

class PrioritizedTaskConsumer implements Runnable {
	private PriorityBlockingQueue<Runnable> q;

	public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> q) {
		this.q = q;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				q.take().run();	// 用当前线程运行任务
			}
		} catch (InterruptedException e) {
			// 可接受的方式退出
		}
		System.out.println("Finished PrioritizedTaskConsumer");
	}
	
}

public class PriorityBlockingQueueDemo {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
		exec.execute(new PrioritizedTaskProducer(queue, exec));
		exec.execute(new PrioritizedTaskConsumer(queue));
	}

}
