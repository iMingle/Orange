/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * 反射测试类
 *
 * @since 1.8
 * @author Mingle
 */
public class ReflectTest {
	private static String usage = 
			"usage:\n" +
			"ShowMethods qualified.class.name\n" +
			"To show all methods in class or:\n" +
			"ShowMethods qualified.class.name word\n" +
			"To search for methods involving 'word'";
	private static Pattern p = Pattern.compile("\\w+\\.");
	
	@SuppressWarnings({ "unused", "rawtypes" })
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println(usage);
			System.exit(0);
		}
		int lines = 0;
		
		try {
			Class<?> c = Class.forName(args[0]);
			Method[] methods = c.getMethods();
			Constructor[] ctors = c.getConstructors();
			if (args.length == 1) {
				for (Method method : methods) {
					System.out.println(p.matcher(method.toString()).replaceAll(""));
				}
				for (Constructor constructor : ctors) {
					System.out.println(p.matcher(constructor.toString()).replaceAll(""));
				}
				lines = methods.length + ctors.length;
			} else {
				for (Method method : methods) {
					if (method.toString().indexOf(args[1]) != -1) {
						System.out.println(p.matcher(method.toString()).replaceAll(""));
						lines++;
					}
				}
				for (Constructor constructor : ctors) {
					if (constructor.toString().indexOf(args[1]) != -1) {
						System.out.println(p.matcher(constructor.toString()).replaceAll(""));
						lines++;
					}
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("No such class: " + e);
		}
	}
}
