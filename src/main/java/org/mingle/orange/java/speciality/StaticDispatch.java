/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

/**
 * 静态分派
 * 
 * @since 1.8
 * @author Mingle
 */
public class StaticDispatch {
	
	static abstract class Human {}
	static class Man extends Human {}
	static class Woman extends Human {}
	
	public void sayHello(Human guy) {
		System.out.println("hello,guy!");
	}
	
	public void sayHello(Man guy) {
		System.out.println("hello,gentleman!");
	}
	
	public void sayHello(Woman guy) {
		System.out.println("hello,lady!");
	}

	public static void main(String[] args) {
		Human man = new Man();
		Human woman = new Woman();
		StaticDispatch sr = new StaticDispatch();
		sr.sayHello(man);		// hello,guy!
		sr.sayHello(woman);		// hello,guy!
	}

}
