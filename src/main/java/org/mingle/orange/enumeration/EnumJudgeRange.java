/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.enumeration;

import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0 2014年7月8日
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 *
 */
public class EnumJudgeRange {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Range.getList().get(2).getNumber());
	}

}

enum Range {
	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);
	private int number;
	private static List<Range> list;
	
	static {
		list = Arrays.asList(Range.values());
	}
	
	private Range(int number) {
		this.number = number;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @return the list
	 */
	public static List<Range> getList() {
		return list;
	}
}
