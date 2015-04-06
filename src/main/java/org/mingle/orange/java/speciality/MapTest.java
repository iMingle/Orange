/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Map测试类
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class MapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random(47);
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < 1000; i++) {
			int r = rand.nextInt(10);
			Integer freq = map.get(r);
			map.put(r, freq == null ? 1 : freq + 1);
		}
		System.out.println(map);
		
		Set<Map.Entry<Integer, Integer>> entrys = map.entrySet();
		System.out.println(entrys);
		Set<Integer> keys = map.keySet();
		System.out.println(keys);
	}

}
