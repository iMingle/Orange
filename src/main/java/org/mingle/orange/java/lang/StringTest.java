/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.java.lang;

/**
 * String测试类
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class StringTest {
	private static String sta = "static";
	private String name = "Mingle";
	private String email;
	private String notInit;
	
	static {
		System.out.print("静态初始化块");
		System.out.println(sta);
	}
	
	{
		System.out.print("初始化块");
		System.out.println(name);
	}
	
	public StringTest(String email) {
		System.out.print("构造方法");
		this.email = email;
		System.out.println(this.email);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new StringTest("jinminglei@yeah.net").notInit);			// null
	}

}
