package org.mingle.orange.designpattern.adapter;

public class Mingle {
	
	public static void main(String[] args) {
		Person person = new Person("Mingle", 26, "man", "Beijing", "15524566289");
		
		Adaptee adaptee = new AdapteeImpl(person);
		Adapter adapter = new AdapterImpl(adaptee);
		
		System.out.println(adapter.getBasicInfo().getName());
		
		System.out.println(adapter.getExtendInfo().getAddress());
	}

}
