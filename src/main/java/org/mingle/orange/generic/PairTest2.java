/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.generic;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @date 2014年7月22日
 * @version 1.0
 */
public class PairTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GregorianCalendar[] birthdays = {
				new GregorianCalendar(1800, Calendar.DECEMBER, 4),
				new GregorianCalendar(1930, Calendar.DECEMBER, 8),
				new GregorianCalendar(1945, Calendar.DECEMBER, 16),
				new GregorianCalendar(1988, Calendar.JANUARY, 26)
		};
		Pair<GregorianCalendar> mm = ArrayAlg2.minmax(birthdays);
		System.out.println("min = " + mm.getFirst().getTime());
		System.out.println("max = " + mm.getSecond().getTime());
		
		DateInterval interval = new DateInterval(new Date(), new Date());
		Pair<Date> pair = interval;
		
		pair.setSecond(new Date());
	}
}

class DateInterval extends Pair<Date> {
	/**
	 * @param first
	 * @param second
	 */
	public DateInterval(Date first, Date second) {
		super(first, second);
	}

	/**
	 * 
	 */
	public void setSecond(Date second) {
		System.out.println("DateInterval setSecond");
		if (second.compareTo(getFirst()) >= 0) {
			super.setSecond(second);
		}
	}
}

class ArrayAlg2 {

	/**
	 * get the minimum and maximum of an array of objects of type T.
	 * @param birthdays
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T extends Comparable> Pair<T> minmax(T[] birthdays) {
		if (null == birthdays || 0 == birthdays.length) return null;
		T min = birthdays[0];
		T max = birthdays[0];
		
		for (int i = 0; i < birthdays.length; i++) {
			if (min.compareTo(birthdays[i]) > 0) min = birthdays[i];
			if (max.compareTo(birthdays[i]) < 0) max = birthdays[i];
		}
		
		return new Pair<T>(min, max);
	}
}