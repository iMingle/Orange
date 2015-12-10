/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java8;

/**
 * 方法与构造函数引用
 * 
 * @since 1.8
 * @author Mingle
 */
public class MethodQuote {

	public static void main(String[] args) {
		Converter<String, Integer> converter = Integer::valueOf;
		Integer converted = converter.convert("123");
		System.out.println(converted); // 123

		Converter<String, Boolean> converter1 = "JavaScript"::startsWith;
		boolean isStartsWith = converter1.convert("Java");
		System.out.println(isStartsWith); // true
		
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");
		System.out.println(person); // Person: Peter Parker
	}

}

class Person {
	String firstName;
	String lastName;

	Person() {}

	Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Person: " + firstName +  " " + lastName;
	}
}

interface PersonFactory<P extends Person> {
	P create(String firstName, String lastName);
}