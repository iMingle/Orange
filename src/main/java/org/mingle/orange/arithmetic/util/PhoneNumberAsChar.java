/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;



/**
 * 电话号码对应英文单词
 * 
 * @since 1.8
 * @author Mingle
 */
public class PhoneNumberAsChar {
	private static final String[] NUMBER_CHAR = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
	private static final int[] total = {0, 0, 3, 3, 3 , 3, 3, 4, 3, 4};
	
	/**
	 * 
	 * @param number
	 * @param answer 存放数字在字符数组中的位置
	 */
	public static void changeNumberToStr(Long number, int[] answer) {
		String numStr = number.toString();
		
		while (true) {
			for (int i = 0; i < numStr.length(); i++) {
				if (!"".equals(NUMBER_CHAR[numStr.charAt(i) - '0']))
					System.out.print(NUMBER_CHAR[numStr.charAt(i) - '0'].charAt(answer[i]));
			}
			System.out.println();
			int k = numStr.length() - 1;
			
			while (k >= 0) {
				if (answer[k] < total[numStr.charAt(k) - '0'] - 1) {
					answer[k]++;
					break;
				} else {
					answer[k] = 0;
					k--;
				}
			}
			
			if (k < 0)
				break;
		}
	}
	
	/**
	 * 递归实现
	 * 
	 * @param number
	 * @param answer
	 * @param index 从电话号码的第几位开始循环
	 */
	public static void changeNumberToStrRecursive(Long number, int[] answer, int index) {
		String numStr = number.toString();
		
		if (index == numStr.length()) {
			for (int i = 0; i < index; i++) {
				if (!"".equals(NUMBER_CHAR[numStr.charAt(i) - '0']))
					System.out.print(NUMBER_CHAR[numStr.charAt(i) - '0'].charAt(answer[i]));
			}
			System.out.println();
			return;
		}
		
		for (answer[index] = 0; answer[index] < total[numStr.charAt(index) - '0']; answer[index]++)
			changeNumberToStrRecursive(number, answer, index + 1);
	}
}
