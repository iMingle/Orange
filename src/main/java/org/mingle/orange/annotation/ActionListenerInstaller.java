/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.annotation;

import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @version 1.0 2014年6月22日
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 *
 */
public class ActionListenerInstaller {

	/**
	 * 处理所有的ActionListenerFor
	 * @param obj 其方法可能包含ActionListenerFor注解
	 */
	public static void processAnnotations(Object obj) {
		try {
			Class<?> cl = obj.getClass();
			
			for (Method m : cl.getDeclaredMethods()) {
				ActionListenerFor a = m.getAnnotation(ActionListenerFor.class);
				if (a != null) {
					Field f = cl.getDeclaredField(a.source());
					f.setAccessible(true);
					addListener(f.get(obj), obj, m);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 添加事件处理器
	 * @param source
	 * @param param
	 * @param m
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private static void addListener(Object source, final Object param, final Method m) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		InvocationHandler handler = new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				return m.invoke(param);
			}
		};
		
		Object listener = Proxy.newProxyInstance(null, new Class[] {java.awt.event.ActionListener.class}, handler);
		Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
		adder.invoke(source, listener);
	}

}
