/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.mvel;

/**
 * mvel测试实体类
 * 
 * @since 1.8
 * @author Mingle
 */
public class MvelDomain {
	private String name;
	private int age;

	public MvelDomain() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param name
	 * @param age
	 */
	public MvelDomain(String name, int age) {
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
	 * @param name
	 *            the name to set
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
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
}
