/*
 *
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 *
 */

package org.mingle.orange.arithmetic.util;

/**
 * 二叉树中节点的最大距离
 * 
 * @author mingle
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
