/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class InheritTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Father f = new Father(48);
		Son s = new Son(24);
		Father fs = new Son(12);
		System.out.println(f.inherit(f));	// I am father, i am age of 48
		System.out.println(s.inherit(s));	// I am son, i am age of 24
		System.out.println(s.inherit(f));	// I am father son, i am age of 48
		System.out.println(f.inherit(s));	// I am father, i am age of 24
		System.out.println(fs.inherit(f));	// I am father son, i am age of 48
		System.out.println(fs.inherit(s));	// I am father son, i am age of 24
	}

}

class Father {
	private int age;
	
	/**
	 * @param age
	 */
	public Father(int age) {
		super();
		this.age = age;
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

	public String inherit(Father f) {
		return "I am father, i am age of " + f.age;
	}
}

class Son extends Father {
	/**
	 * @param age
	 */
	public Son(int age) {
		super(age);
	}
	
	public String inherit(Father f) {
		return "I am father son, i am age of " + f.getAge();
	}

	public String inherit(Son f) {
		return "I am son, i am age of " + f.getAge();
	}
}