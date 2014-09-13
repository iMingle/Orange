package org.mingle.orange.hello;

public class Person {

	public String name;
	private int age;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public void testStatic() {
		System.out.println("Father");
	}

	public static void main(String args[]) {
		
	}

}
