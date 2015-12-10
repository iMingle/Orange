/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.sync;

/**
 * 遍历-同步聚合操作
 * 
 * @since 1.8
 * @author Mingle
 */
public class ExpandableArrayWithApply extends ExpandableArray {

	public ExpandableArrayWithApply(int cap) {
		super(cap);
	}

	/**
	 * 拥有集合的锁的时间很长
	 * 
	 * @param p
	 */
	public synchronized void applyToAll(Procedure p) {
		for (int i = 0; i < size; ++i)
			p.apply(data[i]);
	}
	
	public static void main(String[] args) {
		ExpandableArrayWithApply v = new ExpandableArrayWithApply(5);
		v.add("One");
		v.add("Two");
		v.applyToAll(new Procedure() {
			
			@Override
			public void apply(Object obj) {
				System.out.println(obj);
			}
		});
	}
}
