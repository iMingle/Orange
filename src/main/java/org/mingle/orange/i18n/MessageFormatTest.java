/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.i18n;

import java.text.MessageFormat;
import java.util.GregorianCalendar;

/**
 * 格式化带变量的文本
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class MessageFormatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// {2, date, long} 占位符，类型，风格
		// 类型:number, time, date, choice
		// number:integer, currency, percent, $, ##0
		// time&date:short, medium, long, full, yyyy-MM-dd
		String msg = MessageFormat.format(
		"on {2, date, long}, a {0} destroyed {1} houses and caused {3, number, currency} of damage."
		, "hurricane", 99, new GregorianCalendar(2000, 8, 8).getTime(), 10.0E8);
		
		System.out.println(msg);
	}

}
