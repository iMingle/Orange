package org.mingle.orange.hello;

public class Son extends Person {
	
	public Son() {
		super("hello", 24);
	}
	
	public int getAg() {
		return super.getAge();
	}
	
	public void testStatic() {
		System.out.println("Son");
	}
	
	public void funcInSon() {
		System.out.println("I'm a function only in Son");
	}
	
	public static void main(String[] args) {
		Son s = new Son();
//		Person p = new Person("he", 24);
		
		Person p = s;
		
		s.funcInSon();
//		p.funcInSon(); error
		
		s.testStatic();
		
		p.testStatic();
		
		System.out.println(s.name);
	}
}
