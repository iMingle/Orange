/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

/**
 * String连接性能测试
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class StringTest {
	
	/**
	 * 性能差
	 * @param fields
	 * @return
	 */
	public String implicit(String[] fields) {
		String result = "";
		for (int i = 0; i < fields.length; i++)
			result += fields[i];
		return result;
	}
	
	/**
	 * 性能好，应该优先使用StringBuilder
	 * @param fields
	 * @return
	 */
	public String explicit(String[] fields) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < fields.length; i++)
			result.append(fields[i]);
		return result.toString();
	}
}
