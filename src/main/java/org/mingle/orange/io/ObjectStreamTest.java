/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class ObjectStreamTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Employee4 harry = new Employee4("Harry Hacker", 50000, 1989, 10, 1);
		Manager3 carl = new Manager3("Carl Cracker", 80000, 1987, 12, 15);
		carl.setSecretary(harry);
		Manager3 tony = new Manager3("Tony Tester", 40000, 1990, 3, 15);
		tony.setSecretary(harry);

		Employee4[] staff = new Employee4[3];

		staff[0] = carl;
		staff[1] = harry;
		staff[2] = tony;

		try {
			// save all employee records to the file out_employee.txt
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(
							new File(TextFileTest.class.getResource(
									"/documents/out_employee.txt").toURI())));
			out.writeObject(staff);
			out.close();

			// retrieve all records into a new array
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					new File(TextFileTest.class.getResource(
							"/documents/out_employee.txt").toURI())));
			Employee4[] newStaff = (Employee4[]) in.readObject();
			in.close();
			
			// raise secretary's salary
			newStaff[1].raiseSalary(10);
			
			// print the newly read employee records
			for (Employee4 e : newStaff)
				System.out.println(e);
		} catch (IOException | URISyntaxException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class Employee3 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3449212078994340481L;

	private String name;
	private double salary;
	private Date hireDay;

	public Employee3() {
	}

	public Employee3(String n, double s, int year, int month, int day) {
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

class Manager3 extends Employee4 {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4400747033029207694L;
	private Employee4 secretary;

	/**
	 * Constructs a Manager without a secretary
	 * 
	 * @param n the employee's name
	 * @param s the salary
	 * @param year the hire year
	 * @param month the hire month
	 * @param day the hire day
	 */
	public Manager3(String n, double s, int year, int month, int day) {
		super(n, s, year, month, day);
		secretary = null;
	}

	/**
	 * Assigns a secretary to the manager.
	 * 
	 * @param s the secretary
	 */
	public void setSecretary(Employee4 s) {
		secretary = s;
	}

	public String toString() {
		return super.toString() + "[secretary=" + secretary + "]";
	}

}