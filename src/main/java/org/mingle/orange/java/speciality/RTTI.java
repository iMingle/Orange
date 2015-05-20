/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

class Useful {
	public void f() {
	}

	public void g() {
	}
}

class MoreUseful extends Useful {
	public void f() {
	}

	public void g() {
	}

	public void u() {
	}

	public void v() {
	}

	public void w() {
	}
}

/**
 * Run-Time Type Information 运行时类型识别
 * 
 * @since 1.8
 * @author Mingle
 */
public class RTTI {
	public static void main(String[] args) {
		Useful[] x = { new Useful(), new MoreUseful() };
		x[0].f();
		x[1].g();
		// Compile time: method not found in Useful:
		// ! x[1].u();
		((MoreUseful) x[1]).u(); // Downcast/RTTI
		((MoreUseful) x[0]).u(); // Exception thrown	java.lang.ClassCastException
	}
}
