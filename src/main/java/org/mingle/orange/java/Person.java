/**
 * @version 1.0 2014年6月24日
 * @author mingle
 */
package org.mingle.orange.java;

/**
 * @version 1.0 2014年6月24日
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 *
 */
public class Person {
	private String name;
	private int age;
	private int sex;
	
	public Person() {
		
	}
	
	public Person(String name, int age, int sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
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
	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}
	
}
