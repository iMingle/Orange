package org.mingle.orange.hello;

public class IntegerTest {

	public static void main(String[] args) {
		Integer i = new Integer(6);
		Integer j = Integer.valueOf(7);
		
		System.out.println(i + " " + j);
		
		System.out.println(Integer.toBinaryString(2560));
		System.out.println(Integer.toHexString(16 * 15));
	}

}
