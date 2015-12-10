/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 
 * @since 1.8
 * @author Mingle
 */
public class CollectionTest {
	
	public static void MapTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		
		System.out.println(map);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		CollectionTest.MapTest();
		
		/*		
 		List<Integer> arrayList = new ArrayList<Integer>();
		Random random = new Random();
		List<Integer> arrayListTest = new ArrayList<Integer>();
		
		for (int i = 0; i < 10; i++) {
			arrayList.add(i);
		}
		
		for (int i = 0; i < 10; i++) {
			arrayListTest.add(i + 5);
		}
		
		arrayList.addAll(arrayListTest);
		arrayList.removeAll(arrayListTest);
		arrayList.retainAll(arrayListTest);
		
		arrayList.add(5, 10);
		arrayList.remove(5);
		arrayList.set(4, 8);
		
		Iterator it = arrayList.iterator();
		
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		for (Integer list : arrayList) {
			System.out.println(list);
		}
*/
/*		
		Queue<String> queue = new LinkedList<String>();
		
		queue.offer("Jin");
		queue.offer("Ming");
		queue.offer("Lei");
		
		
		System.out.println(queue.poll());
		System.out.println(queue);
*/	
/*
		Deque<String> deque = new LinkedList<String>();
		
		deque.push("Jin");
		deque.push("Ming");
		deque.push("Lei");
		
		System.out.println(deque);
		
		deque.addFirst("Lei");
		System.out.println(deque);
		
		System.out.println(deque.pop());
		System.out.println(deque);
*/
/*		
		Set<String> set = new TreeSet<String>();
		
		set.add("Jin");
		set.add("Ming");
		set.add("Lei");
		set.add("Lei");
		
		System.out.println(set);
		
		Point point1 = new Point(1, 2);
		Point point2 = new Point(1, 2);
		Set<Point> pointSet = new HashSet<Point>();
		
		pointSet.add(point1);
		pointSet.add(point2);
		
		System.out.println(point1.hashCode() == point2.hashCode());
		
		System.out.println(pointSet);
*/		
				
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		map.put(1, "JinMinglei");
		map.put(2, "WangJianzong");
		map.put(3, "YangJingang");
		map.put(4, "LiQiang");
		
		System.out.println(map);
		
		Set<Integer> keySet = map.keySet();
		
		for (Integer key : keySet) {
			System.out.println(map.get(key));
		}
		
		Set<Entry<Integer, String>> entrySet = map.entrySet();
		
		System.out.println(entrySet);
	}

}
