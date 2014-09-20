/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.generic;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class PairTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] words = {"Mary", "had", "a", "little", "lamb"};
		Pair<String> mm = ArrayAlg1.minmax(words);
		System.out.println("min = " + mm.getFirst());
		System.out.println("max = " + mm.getSecond());
		
		System.out.println("middle1 = " + ArrayAlg1.<String>getMiddle(words));
		System.out.println("middle2 = " + ArrayAlg1.getMiddle(words));
	}
}

class ArrayAlg1 {

	/**
	 * get the minimum and maximum of an array of strings.
	 * @param words an array of strings
	 * @return a pair with the min and max value, or null if words is null or empty
	 */
	public static Pair<String> minmax(String[] words) {
		if (null == words || 0 == words.length) return null;
		String min = words[0];
		String max = words[0];
		
		for (int i = 0; i < words.length; i++) {
			if (min.compareTo(words[i]) > 0) min = words[i];
			if (max.compareTo(words[i]) < 0) max = words[i];
		}
		
		return new Pair<String>(min, max);
	}
	
	public static <T> T getMiddle(T[] t) {
		return t[t.length / 2];
	}
}