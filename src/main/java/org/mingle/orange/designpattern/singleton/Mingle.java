/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.singleton;

public class Mingle {
	
	public static void main(String[] args) {
//		SingletonOne singleton = SingletonOne.getInstance();
		SingletonTwo singleton = SingletonTwo.getInstance();
		
		System.out.println(singleton == SingletonTwo.getInstance());
	}

}
