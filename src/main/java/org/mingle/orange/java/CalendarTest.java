/**
 * @version 1.0 2014年6月23日
 * @author mingle
 */
package org.mingle.orange.java;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @version 1.0 2014年6月23日
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 *
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
