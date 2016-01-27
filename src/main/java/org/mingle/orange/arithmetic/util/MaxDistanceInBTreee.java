/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;

/**
 * 二叉树中节点的最大距离
 * 
 * @since 1.8
 * @author Mingle
 */
public class MaxDistanceInBTreee<E> {
    public static class Node<E> {
        Node<E> left;
        Node<E> right;
        E data;
        int maxLeft;    // 左子树中的最长距离
        int maxRight;    // 右子树中的最长距离
    }
    
    public int maxLength;
    
    /**
     * 寻找树中最长的两段距离
     * 
     * @param root
     */
    public void findMaxLength(Node<E> root) {
        // 遍历到叶子节点,返回
        if (root == null)
            return;
        // 如果左子树为空,那么该节点的左边最长距离为0
        if (root.left == null)
            root.maxLeft = 0;
        if (root.right == null)
            root.maxRight = 0;
        // 不为空则递归寻找最长距离
        if (root.left != null)
            findMaxLength(root.left);
        if (root.right != null)
            findMaxLength(root.right);
        // 计算子树最长节点距离
        if (root.left != null) {
            int maxTemp = 0;
            if (root.left.maxLeft > root.left.maxRight)
                maxTemp = root.left.maxLeft;
            else
                maxTemp = root.left.maxRight;
            root.maxLeft = maxTemp + 1;
        }
        if (root.right != null) {
            int maxTemp = 0;
            if (root.right.maxLeft > root.right.maxRight)
                maxTemp = root.right.maxLeft;
            else
                maxTemp = root.right.maxRight;
            root.maxRight = maxTemp + 1;
        }
        
        if (root.maxLeft + root.maxRight > maxLength)
            maxLength = root.maxLeft + root.maxRight;
    }
}
