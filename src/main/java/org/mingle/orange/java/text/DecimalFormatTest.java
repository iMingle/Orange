/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.text;

import java.text.DecimalFormat;

/**
 * 格式化浮点数
 * 
 * @since 1.8
 * @author Mingle
 */
public class DecimalFormatTest {

	public static void main(String[] args) {
		double d = 190.000;
		DecimalFormat format = new DecimalFormat("#.##");
		System.out.println(format.format(d));		// 190
		format.applyPattern("0.00");
		System.out.println(format.format(d));		// 190.00
		
	}

}
