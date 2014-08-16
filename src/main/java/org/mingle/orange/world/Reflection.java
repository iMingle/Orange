package org.mingle.orange.world;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {
	
	private String name;
	
	public String getName(int age, String k) {
		System.out.println("Mingle" + age + k);
		return name;
	}

	public static void main(String[] args) {
		Field[] fields = new Field[10];
		Method[] methods = new Method[10];
		Reflection r = new Reflection();
		
		fields = r.getClass().getDeclaredFields();
		methods = Reflection.class.getDeclaredMethods();
		
		System.out.println("fields" + fields.length);
		System.out.println("methods" + methods.length);
		
		try {
			methods[1].invoke(r, 26, "test");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(fields[0].getName());
		System.out.println(methods[0].getName());
		System.out.println(methods[1].getName());
	}

}
