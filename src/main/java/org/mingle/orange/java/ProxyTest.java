/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @version 1.0 2014Äê6ÔÂ28ÈÕ
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 *
 */
public class ProxyTest {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		Object[] elements = new Object[1000];
		
		for (int i = 0; i < elements.length; i++) {
			Integer value = i + 1;
			InvocationHandler handler = new TraceHandler(value);
			elements[i] = Proxy.newProxyInstance(null, new Class[] { Comparable.class, List.class }, handler);
		}
		
		Integer key = new Random().nextInt(elements.length) + 1;
		
		int result = Arrays.binarySearch(elements, key);
		
		if (result >= 0) System.out.println(elements[result]);
		
		Class proxyClass = Proxy.getProxyClass(null, Comparable.class);
		
		System.out.println(proxyClass);
	}
}

class TraceHandler implements InvocationHandler {
	private Object target;

	public TraceHandler(Object target) {
		super();
		this.target = target;
	}

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println(method.getName());
		System.out.print(target);
		System.out.print("." + method.getName() + "(");
		System.out.println(Arrays.toString(args));
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				System.out.print(args[i]);
				if (i < args.length - 1) System.out.print(", ");
			}
		}
		System.out.println(")");
		
		return method.invoke(target, args);
	}
	
}