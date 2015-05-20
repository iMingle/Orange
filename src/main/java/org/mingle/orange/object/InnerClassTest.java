/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.object;

import java.util.Date;

/**
 * 
 * @since 1.8
 * @author Mingle
 */
public class InnerClassTest {
	private static int age;
	
	public static class Inner {
		public void test() {
			System.out.println(age);
			final String methodVar = "method variable";
			
			class methodClass {
				public String t() {
					return methodVar;
				}
			}
			
			methodClass m = new methodClass();
			
			System.out.println(m.t());
		}
		
		
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		InnerClassTest outer = new InnerClassTest();
		outer.age = 13;
		Inner inner = new Inner();
		
		inner.test();
		
		System.out.println(new Person("Mingle", 3, 3) {
			@SuppressWarnings("unused")
			private Date birthday;
			
			public String toString() {
				return this.getClass().getName() + "####" + this.getName() + this.getAge() + this.getSex();
			}
		});
	}
}
