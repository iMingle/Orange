package org.mingle.orange.hello;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String s = "Jin.Ming.lei";
		
		System.out.println(s.trim().toLowerCase());
		System.out.println(s.startsWith(" Jin"));
		System.out.println(s.length());
		System.out.println(s.indexOf('i', 3));
		System.out.println(s.lastIndexOf('i', 9));
		
		System.out.println(Arrays.toString(s.getBytes("UTF-8")));
		
		System.out.println(Arrays.toString(s.split("\\.")));
		
		System.out.println(s.replaceAll("in", "oo"));
	}

}
