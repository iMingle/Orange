package org.mingle.orange.world;

public class Son extends Father {
	
	public Son(int age) {
		super(age);
	}
	
	@SuppressWarnings("unused")
	private class Inner {
		
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Son s = new Son(5);
//		s.Inner in = s.new Inner();
		
		String str = "Œ“AB";
		
		System.out.println((str.charAt(0) + "").getBytes().length);
	}

}
