/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.object;

import java.io.UnsupportedEncodingException;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class StringTest {
	/**
	 * 转码测试
	 * @param source
	 * @return
	 */
	public static String encode(String source, String beforeCharsetName, String afterCharsetName) {
		String target = null;
		try {
			target = new String(source.getBytes(beforeCharsetName), afterCharsetName);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return target;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* 转码测试 */
		System.out.println("english" + StringTest.encode("abc", "GB2312", "ISO8859-1"));
		System.out.println("汉语" + StringTest.encode("靳明雷", "GB2312", "GB2312"));
		System.out.println("汉语" + StringTest.encode("靳明雷", "GB2312", "UTF-8"));
		
	}

}
