/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.util.Comparator;
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
		
		/**
		 * 队列的自然顺序
		 */
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
		for (int i = 0; i < 10; i++) {
			priorityQueue.offer(rand.nextInt(i + 10));
		}
		
		System.out.println(priorityQueue);
		
		PriorityQueue<Integer> priorityQ = new PriorityQueue<Integer>(new Comparator<Integer>() {
			/**
			 * 偶数优先级高于奇数
			 */
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 == o2) return 0;
				
				if (o1 % 2 == 0) {
					if (o2 % 2 == 0) {
						if (o1 > o2) return -1;
						else return 1;
					} else {
						return -1;
					}
				} else {
					if (o2 % 2 != 0) {
						if (o1 > o2) return -1;
						else return 1;
					} else {
						return 1;
					}
				}
			}
		});
		
		for (int i = 1; i < 10; i++) {
			priorityQ.offer(i);
		}

		while (priorityQ.size() != 0) {
			System.out.print(priorityQ.poll() + ", ");	// 8, 6, 4, 2, 9, 7, 5, 3, 1, 
		}
	}

}
