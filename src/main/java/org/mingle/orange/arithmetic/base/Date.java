package org.mingle.orange.arithmetic.base;

import java.util.Calendar;

public class Date implements Datable {
	
	private final int month;
	private final int day;
	private final int year;
	
	public Date(int month, int day, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	public int month() {
		// TODO Auto-generated method stub
		return month;
	}

	public int day() {
		// TODO Auto-generated method stub
		return day;
	}

	public int year() {
		// TODO Auto-generated method stub
		return year;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		Date that = (Date) obj;
		if (this.day != that.day) return false;
		if (this.month != that.month) return false;
		if (this.year != that.year) return false;
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		Integer in = new Integer(12);
		Datable date = new Date(1, 26, 1988);
		System.out.println(date.getClass());
		
		Calendar c = Calendar.getInstance();
		c.get(Calendar.DAY_OF_WEEK);
	}
}
