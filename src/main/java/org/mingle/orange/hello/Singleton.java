package org.mingle.orange.hello;

public class Singleton {
	
	private static Singleton singleton;
	
	private Singleton() {
		
	}
	
	public static Singleton GetInstance() {
		if (null == singleton) {
			singleton = new Singleton();
		}
		
		return singleton;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
