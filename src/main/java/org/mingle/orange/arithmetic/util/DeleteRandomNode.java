/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;


/**
 * 从无头单链表中删除节点
 * 
 * @since 1.8
 * @author Mingle
 */
public class DeleteRandomNode {
	public static class Node<E> {
		E data;
		Node<E> next;
	}
	
	public static void deleteRandomNode(Node<String> current) {
		Node<String> next = current.next;
		if (next != null) {
			current.next = next.next;
			current.data = next.data;
			next = null;
		}
	}
}
