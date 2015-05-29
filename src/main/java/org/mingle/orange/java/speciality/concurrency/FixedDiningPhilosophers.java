/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 解决哲学家就餐死锁问题
 *
 * @since 1.8
 * @author Mingle
 */
public class FixedDiningPhilosophers {

	public static void main(String[] args) throws Exception {
		int ponder = 5;
		if (args.length > 0)
			ponder = Integer.parseInt(args[0]);
		int size = 5;
		if (args.length > 1)
			size = Integer.parseInt(args[1]);
		ExecutorService exec = Executors.newCachedThreadPool();
		Chopstick[] sticks = new Chopstick[size];
		Arrays.fill(sticks, new Chopstick());
		for (int i = 0; i < size; i++)
			if (i < (size - 1))
				exec.execute(new Philosopher(sticks[i], sticks[i + 1], i, ponder));
			else
				exec.execute(new Philosopher(sticks[0], sticks[i], i, ponder));
		if (args.length == 3 && args[2].equals("timeout"))
			TimeUnit.SECONDS.sleep(5);
		else {
			System.out.println("Press 'Enter' to quit");
			System.in.read();
		}
		exec.shutdownNow();
	}

}
