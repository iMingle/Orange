/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

import java.util.Date;

/**
 * 保护性拷贝
 * 
 * @since 1.8
 * @author Mingle
 */
public class DefensiveCopy {

	public static void main(String[] args) {
		Date start = new Date();
		Date end = new Date();
		Period period = new Period(start, end);
		period.end().setTime(123);
		System.out.println(period.start());
		System.out.println(period.end());
	}

}

/**
 * 不可变的时间周期
 */
final class Period {
	private final Date start;
	private final Date end;
	
	public Period(Date start, Date end) {
		this.start = new Date(start.getTime());
		this.end = new Date(end.getTime());
		
		if (start.compareTo(end) > 0)
			throw new IllegalArgumentException(start + " after " + end);
	}
	
	public Date start() {
		return new Date(start.getTime());
	}
	
	public Date end() {
		return new Date(end.getTime());
	}

}
