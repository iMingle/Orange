/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 非阻塞的链表
 * 
 * @since 1.8
 * @author Mingle
 */
public class LinkedQueue<E> {
	private static class Node<E> {
		final E item;
		final AtomicReference<Node<E>> next;
		
		public Node(E item, AtomicReference<Node<E>> next) {
			super();
			this.item = item;
			this.next = next;
		}
	}
	
	private final Node<E> dummy = new Node<E>(null, null);
	private final AtomicReference<Node<E>> head = new AtomicReference<Node<E>>(dummy);
	private final AtomicReference<Node<E>> tail = new AtomicReference<Node<E>>(dummy);
	
	public boolean put(E item) {
		Node<E> newNode = new Node<E>(item, null);
		while (true) {
			Node<E> curTail = tail.get();
			Node<E> tailNext = curTail.next.get();
			if (curTail == tail.get()) {
				if (tailNext != null) {
					// 队列处于中间状态,推进尾结点
					tail.compareAndSet(curTail, tailNext);
				} else {
					// 处于稳定状态,尝试插入新节点
					if (curTail.next.compareAndSet(null, newNode)) {
						// 插入操作成功,尝试推进尾结点
						tail.compareAndSet(curTail, newNode);
						return true;
					}
				}
			}
		}
	}
}
