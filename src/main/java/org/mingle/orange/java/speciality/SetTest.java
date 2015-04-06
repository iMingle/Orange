/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Set测试类
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class SetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random(47);
//		Set<Integer> ints = new HashSet<>();		// Hash函数Set
//		Set<Integer> ints = new TreeSet<>();		// 排序的Set
		Set<Integer> ints = new LinkedHashSet<>();	// 插入顺序Set
		for (int i = 0; i < 1000; i++) {
			ints.add(rand.nextInt(10));
		}
		
		System.out.println(ints);
	}

}
