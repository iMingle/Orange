/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package org.mingle.orange.util;

/**
 * 
 * @since 1.0 2015年3月13日
 * @author <a href="mailto:jinml@legendsec.com">靳明雷</a>
 * @version 1.0
 */
public class StringUtils {

	public static String reverse(String in, String seq) {
		if (in == null) return null;
		if (in == "") return "";
		StringBuilder result = new StringBuilder();
		String[] temp = in.split(" ");

		for (int i = temp.length - 1; i >= 0; i--) {
			result.append(temp[i]).append(" ");
		}
		
		seq = result.toString().trim();
		
		return seq;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String seq = "Hello";
		System.out.println(StringUtils.reverse("I  love the game", seq));
		System.out.println(seq);
	}

}
