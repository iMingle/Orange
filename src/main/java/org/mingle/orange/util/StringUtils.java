/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package org.mingle.orange.util;

import java.util.LinkedList;

/**
 * String工具类
 * 
 * @since 1.0 2015年3月13日
 * @author <a href="mailto:jinml@legendsec.com">靳明雷</a>
 * @version 1.0
 */
public class StringUtils {
	/**
	 * Given an input string, reverse the string word by word.
	 * 
	 * @param in
	 * @return
	 */
	public static String reverse(String in) {
		if (in == null)
			return null;
		if (in == "")
			return "";

		LinkedList<String> stack = new LinkedList<String>();
		in = in.trim() + ' ';
		int length = in.length();
		int i = 0;
		int j = 0;
		while (i < length) {
			while (i < length && in.charAt(i) == ' ')
				i++;
			if (i < length) {
				j = in.indexOf(' ', i);
				stack.push(in.substring(i, j));
				i = j + 1;
			}
		}

		StringBuilder result = new StringBuilder();

		while (!stack.isEmpty())
			result.append(stack.poll()).append(" ");

		return result.toString().trim();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String seq = "Hello";
		System.out.println(StringUtils.reverse("I  love the game"));
		System.out.println(seq);
	}

}
