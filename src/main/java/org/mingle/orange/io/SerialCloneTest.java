/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class SerialCloneTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee4 harry = new Employee4("Harry Hacker", 35000, 1989, 10, 1);
		// clone harry
		Employee4 harry2 = (Employee4) harry.clone();

		// mutate harry
		harry.raiseSalary(10);

		// now harry and the clone are different
		System.out.println(harry);
		System.out.println(harry2);
	}

}

/**
 * A class whose clone method uses serialization.
 */
class SerialCloneable implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4802191062063297367L;

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() {
		try {
			// save the object to a byte array
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bout);
			out.writeObject(this);
			out.close();
			
			// read a clone of the object from the byte array
			ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
			ObjectInputStream in = new ObjectInputStream(bin);
			Object ret = in.readObject();
			in.close();
			
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}

/**
 * The familiar Employee class, redefined to extend the
 * SerialCloneable class. 
 */
class Employee4 extends SerialCloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3449212078994340481L;

	private String name;
	private double salary;
	private Date hireDay;

	public Employee4() {
	}

	public Employee4(String n, double s, int year, int month, int day) {
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		hireDay = calendar.getTime();
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public Date getHireDay() {
		return hireDay;
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}

	public String toString() {
		return getClass().getName() + "[name=" + name + ",salary=" + salary
				+ ",hireDay=" + hireDay + "]";
	}

}