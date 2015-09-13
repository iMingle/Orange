/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java7;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 动态类型语言
 * 
 * @since 1.8
 * @author Mingle
 */
public class MethodHandleTest {
	
	static class ClassA {
		public void println(String s) {
			System.out.println(s);
		}
	}

	public static void main(String[] args) throws Throwable {
		Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
		
		getPrintlnMH(obj).invokeExact("mingle");
	}

	private static MethodHandle getPrintlnMH(Object receiver) throws Throwable {
		MethodType mt = MethodType.methodType(void.class, String.class);
		
		return MethodHandles.lookup().findVirtual(receiver.getClass(), "println", mt).bindTo(receiver);
	}
}
