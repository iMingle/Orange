package org.mingle.orange.reflect;

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
		
		try {
			// Inner must has a default constructor, otherwise error
			Class<?> clazz = Class.forName("org.mingle.orange.reflect.Inner");
			// public class has a default constructor
//			Class<?> clazz = Class.forName("org.mingle.orange.reflect.ReflectionTest");
			Inner inner = (Inner) clazz.newInstance();
			System.out.println(inner);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class Inner {
	private String name;
	
	public Inner() {
		
	}

	/**
	 * @param name
	 */
	public Inner(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Inner [name=" + name + "]";
	}
}