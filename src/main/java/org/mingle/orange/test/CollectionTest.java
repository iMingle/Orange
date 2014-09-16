/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package org.mingle.orange.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @since 1.0 2014年7月22日
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class CollectionTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "a");
		map.put("2", "b");
		map.put("3", "c");
		map.put("4", "d");
		map.put("5", "e");
		map.put("6", "f");
		map.put("7", "g");
		map.put("8", "h");
		map.put("9", "i");
		map.put(null, null);
		map.put(null, null);
		
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		
		System.out.println(map.get("10"));
		System.out.println(map.get(null));
		
		if (it.hasNext()) {
			// 执行一次next游标下移
//			System.out.println("map's size = " + map.size());
//			System.out.println(it.next().getKey());
//			System.out.println(it.next().getKey());
		}
	}
}
