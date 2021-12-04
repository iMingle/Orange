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

package org.orange.arithmetic.search.btree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的遍历
 *
 * @author mingle
 */
public class BinaryTree<T> {
    // 根节点
    private Node<T> root;

    static class Node<V> {
        final V value;
        Node<V> left;
        Node<V> right;

        public Node(V value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    // 二叉树中节点数量
//    private int size;

    // 无参构造器
    public BinaryTree() {
        root = new Node<>(null);
    }

    // 数组构造器
    public BinaryTree(T[] values) {
        System.out.print("新建binaryTree:");
        for (T i : values) {
            System.out.print(i);
        }
        System.out.println();
        boolean isLeft = true;
        int len = values.length;
        if (len == 0)
            return;
        Deque<Node<T>> queue = new LinkedList<>();
        root = new Node<>(values[0]);
        queue.addLast(root);
        Node<T> parent;
        Node<T> current;
        for (int i = 1; i < len; i++) {
            current = new Node<>(values[i]);
            queue.addLast(current);
            if (isLeft)
                parent = queue.getFirst();
            else
                parent = queue.removeFirst();
            if (isLeft) {
                parent.left = current;
                isLeft = false;
            } else {
                parent.right = current;
                isLeft = true;
            }
        }
    }

    /**
     * 递归中序遍历
     */
    public void inorder() {
        System.out.print("binaryTree递归中序遍历:");
        inorderTraverseRecursion(root);
        System.out.println();
    }

    /**
     * 层次遍历
     */
    public void layerorder() {
        System.out.print("binaryTree层次遍历:");
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        Node<T> current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            if (current.left != null)
                queue.offer(current.left);
            if (current.right != null)
                queue.offer(current.right);
            System.out.print(current.value);
        }
        System.out.println();
    }

    /**
     * 获得二叉树深度（非递归）
     *
     * @return
     */
    public int getDepth() {
        return getDepthRecursion(root);
    }

    private int getDepthRecursion(Node<T> node) {
        if (node == null)
            return 0;
        int llen = getDepthRecursion(node.left);
        int rlen = getDepthRecursion(node.right);
        int maxlen = Math.max(llen, rlen);
        return maxlen + 1;
    }

    /**
     * 递归先序遍历
     */
    public void preorder() {
        System.out.print("binaryTree递归先序遍历:");
        preorderTraverseRecursion(root);
        System.out.println();
    }

    /**
     * 递归后序遍历
     */
    public void postorder() {
        System.out.print("binaryTree递归后序遍历:");
        postorderTraverseRecursion(root);
        System.out.println();
    }

    private void inorderTraverseRecursion(Node<T> node) {
        if (node.left != null)
            inorderTraverseRecursion(node.left);
        System.out.print(node.value);
        if (node.right != null)
            inorderTraverseRecursion(node.right);
    }

    private void preorderTraverseRecursion(Node<T> node) {
        System.out.print(node.value);
        if (node.left != null)
            preorderTraverseRecursion(node.left);
        if (node.right != null)
            preorderTraverseRecursion(node.right);
    }

    private void postorderTraverseRecursion(Node<T> node) {
        if (node.left != null)
            postorderTraverseRecursion(node.left);
        if (node.right != null)
            postorderTraverseRecursion(node.right);
        System.out.print(node.value);
    }

    /**
     * 非递归先序遍历
     */
    public void preorderNoRecursion() {
        System.out.print("binaryTree非递归先序遍历:");
        Deque<Node<T>> stack = new LinkedList<>();
        stack.push(root);
        Node<T> current;
        while (!stack.isEmpty()) {
            current = stack.pop();
            System.out.print(current.value);
            if (current.right != null)
                stack.push(current.right);
            if (current.left != null)
                stack.push(current.left);
        }
        System.out.println();
    }

    /**
     * 非递归中序遍历 栈内保存将要访问的元素
     */
    public void inorderNoRecursion() {
        System.out.print("binaryTree非递归中序遍历:");
        Deque<Node<T>> stack = new LinkedList<>();
        Node<T> current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.print(current.value);
                current = current.right;
            }
        }
        System.out.println();
    }

    /**
     * 非递归后序遍历
     * <p>
     * 当上一个访问的结点是右孩子或者当前结点没有右孩子则访问当前结点
     */
    public void postorderNoRecursion() {
        System.out.print("binaryTree非递归后序遍历:");
        Node<T> prev = null;
        Node<T> current = root;
        Deque<Node<T>> stack = new LinkedList<>();
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            if (current.right == null || current.right == prev) {
                System.out.print(current.value);
                prev = current;
                current = null;
            } else {
                stack.push(current);
                current = current.right;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BinaryTree<String> bt = new BinaryTree<>(new String[]{"A", "B", "C", "D", "E", "F", "G"});
        bt.preorder();
        bt.inorder();
        bt.postorder();
        bt.layerorder();
        bt.preorderNoRecursion();
        bt.inorderNoRecursion();
        bt.postorderNoRecursion();
        System.out.println("深度为：" + bt.getDepth());
    }
}
