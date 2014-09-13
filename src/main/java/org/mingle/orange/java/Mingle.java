/**
 * @version 1.0 2014年6月24日
 * @author mingle
 */
package org.mingle.orange.java;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @version 1.0 2014年6月24日
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 *
 */
public class Mingle {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws ParseException, InstantiationException, IllegalAccessException {
		Object obj = new Student();
		Object o = new Object();
		Student[] students = new Student[10];
		((Student)obj).setName("Mingle");
	
//		System.out.println(obj.getClass().getSuperclass().getName());
//		
//		System.out.println(obj instanceof Person);
//
//		System.out.println(students.getClass().getComponentType());
		
		try {
			Method m = obj.getClass().getMethod("getName");
			
//			System.out.println(m.invoke(obj));
			
			m = Math.class.getMethod("sqrt", double.class);
			
//			System.out.println(m.invoke(null, 16));
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Student s1 = new Student(1, "Mingle", "jinminglei@yeah.net", 26, 0, new Date());
		
		try {
			Student s2 = s1.clone();
			s2.setName("Jack");
			Calendar calendar = Calendar.getInstance();
			calendar.set(2013, 6, 26, 23, 34, 56);
//			s2.setBirthday(calendar.getTime());
//			s2.setBirthday(new Date(2000, 3, 5));
			
//			System.out.println("s1: " + s1.getBirthday().hashCode());
//			System.out.println("s2: " + s2.getBirthday().hashCode());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
