/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.collection;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @date 2014年9月2日
 * @version 1.0
 */
public class EnumSetMapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EnumSet<Weekday> always = EnumSet.allOf(Weekday.class);
		System.out.println("always = " + always);
		EnumSet<Weekday> never = EnumSet.noneOf(Weekday.class);
		System.out.println("never = " + never);
		EnumSet<Weekday> workday = EnumSet.range(Weekday.MONDAY, Weekday.FRIDAY);
		System.out.println("workday = " + workday);
		EnumSet<Weekday> mwf = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
		System.out.println("mwf = " + mwf);
		
		EnumMap<Weekday, Integer> map = new EnumMap<Weekday, Integer>(Weekday.class);
		map.put(Weekday.MONDAY, 1);
		map.put(Weekday.TUESDAY, 2);
		map.put(Weekday.WEDNESDAY, 3);
		map.put(Weekday.THURSDAY, 4);
		map.put(Weekday.FRIDAY, 5);
		map.put(Weekday.SATURDAY, 6);
		map.put(Weekday.SUNDAY, 7);
		System.out.println("EnumMap = " + map);
	}
}

enum Weekday {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
