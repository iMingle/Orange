/**
 * @version 1.0 2014年6月28日
 * @author mingle
 */
package org.mingle.orange.object;

import java.util.Date;

/**
 * @version 1.0 2014年6月28日
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 *
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
