/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.object;

/**
 * java final test
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class FinalTest {
	
	private final int getId() {
		return 1;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int num = 5;
//		num = 6;					// error
		System.out.println(num);
		
		final String s = "final";
//		s = "final2";				// error
		System.out.println(s);
		
		final int[] a = new int[1];
		a[0] = 1;
		a[0] = 2;					// 引用不可变，值可变

		for (int i : a) {
			System.out.println(i);
		}
		
		FinalTest ft = new FinalTest();
		System.out.println(ft.getId());
		
	}

}

class FinalChildTest extends FinalTest {
	public final int getId() {
		return 1;
	}
}