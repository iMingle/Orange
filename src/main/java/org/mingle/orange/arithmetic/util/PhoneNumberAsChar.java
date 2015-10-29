/**
 * Copyright (c) 2015, Mingle. All rights reserved.
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
}
