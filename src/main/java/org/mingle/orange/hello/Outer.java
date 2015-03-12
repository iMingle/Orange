package org.mingle.orange.hello;

public class Outer {
	private int age;
	private static String name;

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Outer.Inner in = new Outer().new Inner();
		Outer outer = new Outer();
		in.getAge();

	}

	private class Inner {
		public Inner() {
			
		}
		
		public int getAge() {
			return age;
		}
	}
	
	@SuppressWarnings("unused")
	private static class InnerStatic {
		public String getName() {
			return name;
		}
	}
	
	@SuppressWarnings("unused")
	private class InnerInner {
		
		private int getAge() {
			final class Stu {
				private int age;
			}
			
			return new Stu().age;
		}
	}
}
