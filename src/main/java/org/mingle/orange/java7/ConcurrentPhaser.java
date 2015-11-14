/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java7;

import java.util.List;
import java.util.concurrent.Phaser;

/**
 * A reusable synchronization barrier, similar in functionality to 
 * CyclicBarrier and CountDownLatch but supporting more flexible usage.
 * 
 * @since 1.8
 * @author Mingle
 */
public class ConcurrentPhaser {
	/**
	 * A Phaser may be used instead of a CountDownLatch to control 
	 * a one-shot action serving a variable number of parties. 
	 * The typical idiom is for the method setting this up to first register, 
	 * then start the actions, then deregister
	 * 
	 * @param tasks
	 */
	void runTasks(List<Runnable> tasks) {
		final Phaser phaser = new Phaser(1); // "1" to register self
		// create and start threads
		for (final Runnable task : tasks) {
			phaser.register();
			new Thread() {
				public void run() {
					phaser.arriveAndAwaitAdvance(); // await all creation
					task.run();
				}
			}.start();
		}

		// allow threads to start and deregister self
		phaser.arriveAndDeregister();
	}

	/**
	 * One way to cause a set of threads to repeatedly perform actions 
	 * for a given number of iterations is to override onAdvance
	 * 
	 * @param tasks
	 * @param iterations
	 */
	void startTasks(List<Runnable> tasks, final int iterations) {
		final Phaser phaser = new Phaser() {
			protected boolean onAdvance(int phase, int registeredParties) {
				return phase >= iterations || registeredParties == 0;
			}
		};
		phaser.register();
		for (final Runnable task : tasks) {
			phaser.register();
			new Thread() {
				public void run() {
					do {
						task.run();
						phaser.arriveAndAwaitAdvance();
					} while (!phaser.isTerminated());
				}
			}.start();
		}
		phaser.arriveAndDeregister(); // deregister self, don't wait
	}
}
