/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java7;

import java.util.concurrent.ThreadLocalRandom;

/**
 * In JDK 7, java.util.concurrent includes a convenience class, 
 * ThreadLocalRandom, for applications that expect to use random 
 * numbers from multiple threads or ForkJoinTasks.
 * For concurrent access, using ThreadLocalRandom instead of Math.random() 
 * results in less contention and, ultimately, better performance.
 * 
 * @since 1.8
 * @author Mingle
 */
public class ConcurrentRandomNumbers {
	public static void main(String[] args) {
		int rand = ThreadLocalRandom.current().nextInt(1, 100);
		System.out.println(rand);
	}
}
