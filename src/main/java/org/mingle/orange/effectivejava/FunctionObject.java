/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

import java.util.Arrays;
import java.util.Comparator;
import java.io.Serializable;

/**
 * 函数对象
 * 
 * @since 1.8
 * @author Mingle
 */
public class FunctionObject {
	private static class StrLenCmp implements Comparator<String>, Serializable {
		private static final long serialVersionUID = 3833101215017572079L;

		@Override
		public int compare(String o1, String o2) {
			return o1.length() - o2.length();
		}
		
	}
	
	public static final Comparator<String> STRING_LENGTH_COMPARATOR = new StrLenCmp();
	
	public static void main(String[] args) {
		String[] stringArray = new String[] {"asda", "dsdas", "bsd"};
		Arrays.sort(stringArray, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		Arrays.sort(stringArray, STRING_LENGTH_COMPARATOR);
	}
}
