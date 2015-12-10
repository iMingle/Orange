/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java8;

import java.util.HashMap;
import java.util.Map;

/**
 * Map增加的操作
 * 
 * @since 1.8
 * @author Mingle
 */
public class EnhanceMap {

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<>();

		for (int i = 0; i < 10; i++) {
		    map.putIfAbsent(i, "val" + i); // putIfAbsent不需要我们做额外的存在性检查
		}

		map.forEach((id, val) -> System.out.println(val));
		
		map.computeIfPresent(3, (num, val) -> val + num);
		map.get(3);             // val33

		map.computeIfPresent(9, (num, val) -> null);
		map.containsKey(9);     // false

		map.computeIfAbsent(23, num -> "val" + num);
		map.containsKey(23);    // true

		map.computeIfAbsent(3, num -> "bam");
		map.get(3);             // val33
		
		map.remove(3, "val3");
		map.get(3);             // val33

		map.remove(3, "val33");
		map.get(3);             // null
		
		map.getOrDefault(42, "not found");  // not found
		
		map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
		map.get(9);             // val9

		map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
		map.get(9);             // val9concat
	}

}
