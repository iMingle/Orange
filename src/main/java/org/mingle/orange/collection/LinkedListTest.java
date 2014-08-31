/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * This program demonstrates operations on linked lists.
 * 
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @date 2014年8月31日
 * @version 1.0
 */
public class LinkedListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> lists = new LinkedList<String>();
		lists.add("one");
		lists.add("two");
		lists.add("three");
		lists.add("four");
		lists.add("five");
		lists.add("six");

		Iterator<String> it = lists.iterator();
		String first = it.next();
		System.out.println("first = " + first);
		String second = it.next();
		System.out.println("second = " + second);
		it.remove();

		System.out.println("lists = " + lists);//lists = [one, three, four, five, six]

		ListIterator<String> lit = lists.listIterator();
		lit.add("seven");
		lit.add("eight");

		System.out.println("lists = " + lists);//lists = [seven, eight, one, three, four, five, six]

		List<String> a = new LinkedList<String>();
		a.add("Amy");
		a.add("Carl");
		a.add("Erica");

		List<String> b = new LinkedList<String>();
		b.add("Bob");
		b.add("Doug");
		b.add("Frances");
		b.add("Gloria");

		// merge the words from b into a
		ListIterator<String> aIter = a.listIterator();
		Iterator<String> bIter = b.iterator();
		
		while (bIter.hasNext()) {
			if (aIter.hasNext()) aIter.next();
			aIter.add(bIter.next());
		}
		
		System.out.println("a = " + a);
		
		// remove every second word from b
		bIter = b.iterator();
		while (bIter.hasNext()) {
			bIter.next();
			if (bIter.hasNext()) {
				bIter.next();
				bIter.remove();
			}
		}
		
		System.out.println("b = " + b);
		
		// remove all words in b from a
		a.removeAll(b);
		System.out.println("a = " + a);
	}
}
