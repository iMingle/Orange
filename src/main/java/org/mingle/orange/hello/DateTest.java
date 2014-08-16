package org.mingle.orange.hello;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
/*		
		Date date = new Date();
		
		System.out.println(date);
		
		System.out.println(date.getDate());
		
		date.setTime(60 * 100);
		System.out.println(date);
*/

/*
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
		
		calendar.set(Calendar.YEAR, 2012);
		calendar.set(Calendar.DAY_OF_MONTH, 15);
		
		calendar.add(Calendar.DAY_OF_YEAR, -2);
		calendar.add(Calendar.MONTH, 4);
		
		System.out.println(calendar.get(Calendar.YEAR));
*/
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		String nowDate = "2014-03-14";
		
		try {
			System.out.println(simple.parse(nowDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimpleDateFormat simpleOne = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		
		String s = simpleOne.format(new Date());
		
		System.out.println(s);
	}

		
}
