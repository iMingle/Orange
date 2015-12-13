/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class LinkedHashMapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> linked = new LinkedHashMap<String, String>(128, 0.75F, true);
		linked.put("4", "four");
		linked.put("5", "five");
		linked.put("6", "six");
		linked.put("1", "one");
		linked.put("2", "two");
		linked.put("3", "three");

		Iterator<String> ita = linked.keySet().iterator();
		while (ita.hasNext()) {
			System.out.print(ita.next() + ",");
		}
		System.out.println();
		
		Map<String, String> linkedMap = new LinkedHashMap<String, String>(128, 0.75F, true);
		linkedMap.put("4", "four");
		linkedMap.put("5", "five");
		linkedMap.put("6", "six");
		linkedMap.put("1", "one");
		linkedMap.put("2", "two");
		linkedMap.put("3", "three");
		linkedMap.get("4");
		Iterator<String> it = linkedMap.keySet().iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + ",");
		}
		System.out.println();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "one");
		map.put("2", "two");
		map.put("3", "three");
		map.put("4", "four");
		map.put("5", "five");
		map.put("6", "six");
		Iterator<String> its = map.keySet().iterator();
		while (its.hasNext()) {
			System.out.print(its.next() + ",");
		}
	}
}
