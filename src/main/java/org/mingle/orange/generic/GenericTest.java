/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.generic;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class GenericTest<T> {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] words = {"Mary", "had", "a", "little", "lamb"};
		
		System.out.println(GenericTest.min(words));
		
		GregorianCalendar[] birthdays = {
				new GregorianCalendar(1800, Calendar.DECEMBER, 4),
				new GregorianCalendar(1930, Calendar.DECEMBER, 8),
				new GregorianCalendar(1945, Calendar.DECEMBER, 16),
				new GregorianCalendar(1988, Calendar.JANUARY, 26)
		};
		
		System.out.println(GenericTest.min(birthdays));
		
		Pair<String> pair = new Pair<String>("first", "second");
		
		if (pair instanceof Pair) {
			System.out.println("pair is Pair");
		}
		// the same to up
		if (pair instanceof Pair<?>) {
			System.out.println("pair is Pair<?>");
		}
		
		System.out.println(pair.getClass());	// class org.mingle.orange.generic.Pair
	}

	public static <T extends Comparable<? super T>> T min(T[] words) {
		if (null == words || 0 == words.length) return null;
		T min = words[0];
		
		for (int i = 0; i < words.length; i++) {
			if (min.compareTo(words[i]) > 0) min = words[i];
		}
		
		return min;
	}
	
	public T max(T[] numbers) {
		return numbers[0];
	}
	
	public <K extends Comparable<?>> void mm(Class<K> klass) {
		return;
	}
}
