/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.object;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 
 * @since 1.8
 * @author Mingle
 */
public class CalendarTest {
	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Calendar calendar = new GregorianCalendar(Locale.UK);
		System.out.println(calendar.toString());
		int month = calendar.get(Calendar.MONTH);
		
		Date date = calendar.getTime();
		
		System.out.println(date);
		
		DateFormatSymbols dfs = new DateFormatSymbols();
		
		for (String s : dfs.getMonths()) {
			System.out.println(s);
		}
		
	}

}
