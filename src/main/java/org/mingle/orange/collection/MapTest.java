/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.collection;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @date 2014年9月2日
 * @version 1.0
 */
public class MapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Employee> staff = new HashMap<String, Employee>();
		staff.put("135-4647-9335", new Employee("Jack"));
		staff.put("155-2456-6289", new Employee("Mingle"));
		staff.put("135-0372-3456", new Employee("Mary"));
		staff.put("139-3998-1143", new Employee("Mom"));
		
		System.out.println(staff);
		
		staff.remove("135-0372-3456");
		
		System.out.println(staff);
		
		staff.put("139-3998-1143", new Employee("Mother"));
		
		System.out.println(staff.get("155-2456-6289"));
		
		// iterate through all entries
		for (Map.Entry<String, Employee> entry : staff.entrySet()) {
			String key = entry.getKey();
			Employee value = entry.getValue();
			System.out.println("key = " + key + ", value = " + value);
		}
	}
}

/**
 * A minimalist employee class for testing purposes.
 */
class Employee {
	private String name;
	private double salary;
	/**
	 * @param name
	 */
	public Employee(String name) {
		super();
		this.name = name;
		this.salary = 0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + "]";
	}
}