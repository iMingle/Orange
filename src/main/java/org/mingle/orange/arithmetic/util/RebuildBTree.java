/*
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
 * limitations under the License.
 */

package org.mingle.orange.arithmetic.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 根据前序和中序遍历结果重建二叉树
 * 
 * @author mingle
 */
public class RebuildBTree<E> {
    public static class Node<E> {
        Node<E> left;
        Node<E> right;
        E data;
    }
    
    /**
     * @param preorder 前序遍历结果
     * @param inorder 中序遍历结果
     * @param treeLength 树的节点个数
     * @param root 根节点
     */
    public void rebuild(String preorder, String inorder, int treeLength, Node<Character> root) {
        if (StringUtils.isBlank(preorder) || StringUtils.isBlank(inorder))
            return;
        
        // 已经是最后一个节点
        if (treeLength == 1)
            return;
        
        // 寻找子树长度
        String orginInorder = inorder;
        String leftEnd = inorder;
        int tempLength = 0;
        int i = 0;
        
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
        if (leftLength > 0) {
            Node<Character> left = new Node<>();
            left.data = preorder.charAt(1);
            root.left = left;
            rebuild(preorder.substring(1), orginInorder, leftLength, left);
        }
        
        // 重建右子树
        if (rightLength > 0) {
            Node<Character> right = new Node<>();
            right.data = preorder.charAt(leftLength + 1);
            root.right = right;
            rebuild(preorder.substring(leftLength + 1), orginInorder.substring(leftLength + 1), rightLength, right);
        }
    }
}
