/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;

import java.util.ArrayList;

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
        
        if (0 == level) {
            System.out.print(root.data + " ");
            return 1;
        }
        
        return printNodeAtLevel(root.left, level - 1) + printNodeAtLevel(root.right, level - 1);
    }
    
    public static <E> void printNodeByLevel(Node<E> root) {
        for (int i = 0; ; i++) {
            if (0 == printNodeAtLevel(root, i))
                break;
            System.out.println();
        }
    }
    
    public static <E> void printNodeByLevelBetter(Node<E> root) {
        if (null == root)
            return;
        
        ArrayList<Node<E>> stack = new ArrayList<>();
        stack.add(root);
        
        int current = 0;
        int last = 1;
        
        while (current < stack.size()) {
            last = stack.size();    // 新的一行访问开始,重新定位last于当前行最后一个节点的下一个位置
            
            while (current < last) {
                System.out.print(stack.get(current).data + " ");
                if (stack.get(current).left != null)
                    stack.add(stack.get(current).left);
                if (stack.get(current).right != null)
                    stack.add(stack.get(current).right);
                current++;
            }
            System.out.println();
        }
    }
    
}
