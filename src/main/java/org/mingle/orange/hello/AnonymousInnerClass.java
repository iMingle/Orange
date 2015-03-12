package org.mingle.orange.hello;


public class AnonymousInnerClass {

	public static void main(String[] args) {
		System.out.println(new Interface() {

			public int getAge() {
				return 5;
			}
		});
	}
	
}
