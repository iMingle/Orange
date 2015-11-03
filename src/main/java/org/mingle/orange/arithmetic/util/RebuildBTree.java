/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 根据前序和中序遍历结果重建二叉树
 * 
 * @since 1.8
 * @author Mingle
 */
public class RebuildBTree<E> {
	public static class Node<E> {
		Node<E> left;
		Node<E> right;
		E data;
	}
	
	public void build(Node<E> root) {
		Node<Character> left = new Node<>();
		left.data = 'l';
		root.left = (Node<E>) left;
	}
	
	public static void main(String[] args) {
		RebuildBTree<Character> bTree = new RebuildBTree<>();
		Node<Character> root = new Node<>();
		root.data = 'r';
		bTree.build(root);
	}
	
	/**
	 * @param preorder 前序遍历结果
	 * @param inorder 中序遍历结果
	 * @param treeLength 树的节点个数
	 * @param root 根节点
	 */
	public void rebuild(String preorder, String inorder, int treeLength, Node<E> root) {
		if (StringUtils.isBlank(preorder) || StringUtils.isBlank(inorder))
			return;
		
		// 获得前序遍历的第一个节点
		Node<Character> temp = new Node<>();
		temp.data = preorder.charAt(0);
		temp.left = null;
		temp.right = null;
		
		// 如果根节点为空,将当前结点复制到根节点
		if (root == null)
			root = (Node<E>) temp;
		
		// 已经是最后一个节点
		if (treeLength == 1)
			return;
		
		// 寻找子树长度
		String orgInorder = inorder;
		String leftEnd = inorder;
		int tempLength = 0;
		int i = 0;
		
		if (preorder == null || leftEnd == null)
			return;
		
		// 找到左子树的结尾
		while (preorder.charAt(0) != leftEnd.charAt(i)) {
			tempLength++;
			if (tempLength > treeLength)
				break;
			i++;
		}
		
		// 寻找左子树长度
		int leftLength = 0;
		leftLength = i;
		
		// 寻找右子树长度
		int rightLength = 0;
		rightLength = treeLength - leftLength - 1;
		
		// 重建左子树
		if (leftLength > 0)
			rebuild(preorder.substring(1), orgInorder, leftLength, root.left);
		
		// 重建右子树
		if (rightLength > 0)
			rebuild(preorder.substring(leftLength + 1), orgInorder.substring(leftLength + 1), rightLength, root.right);
	}
}
