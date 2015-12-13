/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.collection;

import java.util.HashMap;
import java.util.IdentityHashMap;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class IdentityHashMapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IdentityHashMap<Person, String> identity = new IdentityHashMap<Person, String>();
		identity.put(new Person("Mingle", 26), "one");
		identity.put(new Person("Mingle", 26), "one");
		identity.put(new Person("Mingle", 26), "three");
		
		System.out.println(identity.size());
		
		HashMap<Person, String> map = new HashMap<Person, String>();
		map.put(new Person("Mingle", 26), "one");
		map.put(new Person("Mingle", 26), "one");
		map.put(new Person("Mingle", 26), "three");
		
		System.out.println(map.size());
	}
}

class Person {
	private String name;
	private int age;
	
	/**
	 * @param name
	 * @param age
	 */
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}