/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

class Meal {
	Meal() {
		System.out.println("Meal()");
	}
}

class Bread {
	Bread() {
		System.out.println("Bread()");
	}
}

class Cheese {
	Cheese() {
		System.out.println("Cheese()");
	}
}

class Lettuce {
	Lettuce() {
		System.out.println("Lettuce()");
	}
}

class Lunch extends Meal {
	Lunch() {
		System.out.println("Lunch()");
	}
}

class PortableLunch extends Lunch {
	PortableLunch() {
		System.out.println("PortableLunch()");
	}
}

@SuppressWarnings("unused")
/**
 * 构造顺序测试
 * @since 1.8
 * @author Mingle
 */
public class ConstructingOrder extends PortableLunch {
	private Bread b = new Bread();
	private Cheese c = new Cheese();
	private Lettuce l = new Lettuce();

	public ConstructingOrder() {
		System.out.println("ConstructingOrder()");
	}

	public static void main(String[] args) {
		new ConstructingOrder();
	}
} /*
 * Output: Meal() Lunch() PortableLunch() Bread() Cheese() Lettuce() ConstructingOrder()
 */// :~
