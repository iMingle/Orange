/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;

/**
 * tree structure
 * 
 * @since 1.8
 * @author Mingle
 */
public class PriorityQueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PriorityQueue<GregorianCalendar> queue = new PriorityQueue<GregorianCalendar>();
		queue.add(new GregorianCalendar(1988, Calendar.JANUARY, 26));
		queue.add(new GregorianCalendar(1815, Calendar.DECEMBER, 12));
		queue.add(new GregorianCalendar(1903, Calendar.MAY, 3));
		queue.add(new GregorianCalendar(1910, Calendar.JUNE, 22));
		
		System.out.println("Iterating over elements...");
		for (GregorianCalendar date : queue) {
			System.out.println(date.get(Calendar.YEAR));
		}
		System.out.println("Removing elements...");
		while (!queue.isEmpty()) {
			System.out.println(queue.remove().get(Calendar.YEAR));
		}
	}
}
