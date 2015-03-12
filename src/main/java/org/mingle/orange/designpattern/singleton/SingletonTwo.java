package org.mingle.orange.designpattern.singleton;

public class SingletonTwo {
	
	private static SingletonTwo singleton = null;
	
	private SingletonTwo() {
		
	}

	public static SingletonTwo getInstance() {
		if (null == singleton) {
			singleton = new SingletonTwo();
		}
		
		return singleton;
	}
}
