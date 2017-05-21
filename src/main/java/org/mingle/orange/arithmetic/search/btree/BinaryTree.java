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

package org.mingle.orange.arithmetic.search.btree;

import lombok.Data;

import java.util.LinkedList;

/**
 * 二叉树的遍历
 *
 * @author mingle
 */
public class BinaryTree<T> {
    // 根节点
    private Node<T> root;

    @Data private static class Node<V> {
        private V value;
        private Node<V> left;
        private Node<V> right;

        public Node() {
        }

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
        root = new Node<>();
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
        LinkedList<Node<T>> queue = new LinkedList<>();
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
                parent.setLeft(current);
                isLeft = false;
            } else {
                parent.setRight(current);
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
        LinkedList<Node<T>> queue = new LinkedList<>();
        queue.addLast(root);
        Node<T> current;
        while (!queue.isEmpty()) {
            current = queue.removeFirst();
            if (current.getLeft() != null)
                queue.addLast(current.getLeft());
            if (current.getRight() != null)
                queue.addLast(current.getRight());
            System.out.print(current.getValue());
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
        int llen = getDepthRecursion(node.getLeft());
        int rlen = getDepthRecursion(node.getRight());
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
        if (node.getLeft() != null)
            inorderTraverseRecursion(node.getLeft());
        System.out.print(node.getValue());
        if (node.getRight() != null)
            inorderTraverseRecursion(node.getRight());
    }

    private void preorderTraverseRecursion(Node<T> node) {
        System.out.print(node.getValue());
        if (node.getLeft() != null)
            preorderTraverseRecursion(node.getLeft());
        if (node.getRight() != null)
            preorderTraverseRecursion(node.getRight());
    }

    private void postorderTraverseRecursion(Node<T> node) {
        if (node.getLeft() != null)
            preorderTraverseRecursion(node.getLeft());
        if (node.getRight() != null)
            preorderTraverseRecursion(node.getRight());
        System.out.print(node.getValue());
    }

    /**
     * 非递归先序遍历
     */
    public void preorderNoRecursion() {
        System.out.print("binaryTree非递归先序遍历:");
        LinkedList<Node<T>> stack = new LinkedList<>();
        stack.push(root);
        Node<T> current;
        while (!stack.isEmpty()) {
            current = stack.pop();
            System.out.print(current.getValue());
            if (current.getRight() != null)
                stack.push(current.getRight());
            if (current.getLeft() != null)
                stack.push(current.getLeft());
        }
        System.out.println();
    }

    /**
     * 非递归中序遍历 栈内保存将要访问的元素
     */
    public void inorderNoRecursion() {
        System.out.print("binaryTree非递归中序遍历:");
        LinkedList<Node<T>> stack = new LinkedList<>();
        Node<T> current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.print(current.getValue());
                current = current.getRight();
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
        Node<T> rNode = null;
        Node<T> current = root;
        LinkedList<Node<T>> stack = new LinkedList<>();
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            current = stack.pop();
            while (current != null
                    && (current.getRight() == null || current
                    .getRight() == rNode)) {
                System.out.print(current.getValue());
                rNode = current;
                if (stack.isEmpty()) {
                    System.out.println();
                    return;
                }
                current = stack.pop();
            }
            stack.push(current);
            current = current.getRight();
        }

    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree(new String[]{"-", "+", "/", "a", "*",
                "e", "f", "", "", "b", "-", "", "", "", "", "", "",
                "", "", "", "", "c", "d"});
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
