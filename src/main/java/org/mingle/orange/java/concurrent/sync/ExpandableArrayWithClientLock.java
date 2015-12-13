/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.sync;

/**
 * 索引化遍历和客户端锁
 * 
 * @since 1.8
 * @author Mingle
 */
public class ExpandableArrayWithClientLock extends ExpandableArray {

	public ExpandableArrayWithClientLock(int cap) {
		super(cap);
	}

	public static void main(String[] args) {
		ExpandableArrayWithClientLock v = new ExpandableArrayWithClientLock(5);
		v.add("One");
		v.add("Two");
		
		/**
		 * 必须同步size()和get(),但是如果一个线程删除了当前的最后一个元素,那调用v.get(i)可能会出错
		 */
		for (int i = 0; i < v.size(); i++) {
			System.out.println(v.get(i));	// Do not use
		}
		
		/**
		 * 如果ExpandableArray类支持重新设置元素位置的方法,那么在遍历过程中，如果v被修改了,那么同样的元素就可能被打印两次
		 */
		for (int i = 0; true; i++) {
			Object obj = null;
			synchronized (v) {
				if (i < v.size())
					obj = v.get(i);
				else
					break;
			}
			System.out.println(obj);
		}
		
		/**
		 * 如果对元素的操作耗时,则可以先拷贝数组用来做遍历
		 */
		Object[] snapshot;
		synchronized (v) {
			snapshot = new Object[v.size()];
			for (int i = 0; i < snapshot.length; i++) {
				snapshot[i] = v.get(i);
			}
		}
		
		for (int i = 0; i < snapshot.length; i++) {
			System.out.println(snapshot[i]);
		}
	}
}
