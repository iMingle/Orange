/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;

/**
 * 分层遍历二叉树
 * 
 * @since 1.8
 * @author Mingle
 */
public class TraversalBTreeByLevel<E> {
	public static class Node<E> {
		Node<E> left;
		Node<E> right;
		E data;
		
		public Node(E data) {
			this.data = data;
		}
	}
	
	public static <E> int printNodeAtLevel(Node<E> root, int level) {
		if (root == null || level < 0)
			return 0;
		
		System.out.println("Level: " + level + ", Data: " + root.data);
		
		return printNodeAtLevel(root.left, level + 1) + printNodeAtLevel(root.right, level + 1);
	}
}
