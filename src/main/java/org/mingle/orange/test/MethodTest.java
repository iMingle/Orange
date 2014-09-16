/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package org.mingle.orange.test;

/**
 *
 * @since 1.0 2014年7月18日
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class MethodTest {
	
	/**
	 * test if parameter will change.
	 * @param id
	 * @return
	 */
	public static Long returnLong(Long id) {
		id += 1;
		return id;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Long id = 12L;
		
		System.out.println(MethodTest.returnLong(id));
		System.out.println("id = " + id);
	}

}
