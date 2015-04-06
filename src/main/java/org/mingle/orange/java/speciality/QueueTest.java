/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * Queue测试类
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class QueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random(47);
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			queue.offer(rand.nextInt(i + 10));
		}
		
		System.out.println(queue);
		
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
		for (int i = 0; i < 10; i++) {
			priorityQueue.offer(rand.nextInt(i + 10));
		}
		
		System.out.println(priorityQueue);
	}

}
