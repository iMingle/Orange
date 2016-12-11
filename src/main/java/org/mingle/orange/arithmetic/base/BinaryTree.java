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

package org.mingle.orange.arithmetic.base;

import java.util.LinkedList;

/**
 * 二叉树的遍历
 * 
 * @author mingle
 */
public class BinaryTree {
    // 根节点
    private Node<String> root;

    // 二叉树中节点数量
//    private int size;

    // 无参构造器
    public BinaryTree() {
        root = new Node<String>();
    }

    // 数组构造器
    public BinaryTree(String[] values) {
        System.out.print("新建binaryTree:");
        for (String i : values) {
            System.out.print(i);
        }
        System.out.println();
        boolean isLeft = true;
        int len = values.length;
        if (len == 0)
            return;
        LinkedList<Node<String>> queue = new LinkedList<Node<String>>();
        root = new Node<String>(values[0]);
        queue.addLast(root);
        Node<String> parent = null;
        Node<String> current = null;
        for (int i = 1; i < len; i++) {
            current = new Node<String>(values[i]);
            queue.addLast(current);
            if (isLeft)
                parent = queue.getFirst();
            else
                parent = queue.removeFirst();
            if (isLeft) {
                parent.setLeftChild(current);
                isLeft = false;
            } else {
                parent.setRightChild(current);
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
        LinkedList<Node<String>> queue = new LinkedList<Node<String>>();
        queue.addLast(root);
        Node<String> current = null;
        while (!queue.isEmpty()) {
            current = queue.removeFirst();
            if (current.getLeftChild() != null)
                queue.addLast(current.getLeftChild());
            if (current.getRightChild() != null)
                queue.addLast(current.getRightChild());
            System.out.print(current.getValue());
        }
        System.out.println();
    }

    /**
     * 获得二叉树深度（非递归）
     * @return
     */
    public int getDepth() {
        return getDepthRecursion(root);
    }

    private int getDepthRecursion(Node<String> node) {
        if (node == null)
            return 0;
        int llen = getDepthRecursion(node.getLeftChild());
        int rlen = getDepthRecursion(node.getRightChild());
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

    private void inorderTraverseRecursion(Node<String> node) {
        // TODO Auto-generated method stub
        if (node.getLeftChild() != null)
            inorderTraverseRecursion(node.getLeftChild());
        System.out.print(node.getValue());
        if (node.getRightChild() != null)
            inorderTraverseRecursion(node.getRightChild());
    }

    private void preorderTraverseRecursion(Node<String> node) {
        System.out.print(node.getValue());
        if (node.getLeftChild() != null)
            preorderTraverseRecursion(node.getLeftChild());
        if (node.getRightChild() != null)
            preorderTraverseRecursion(node.getRightChild());
    }
    
    private void postorderTraverseRecursion(Node<String> node) {
        if (node.getLeftChild() != null)
            preorderTraverseRecursion(node.getLeftChild());
        if (node.getRightChild() != null)
            preorderTraverseRecursion(node.getRightChild());
        System.out.print(node.getValue());
    }

    /**
     * 非递归先序遍历
     */
    public void preorderNoRecursion() {
        System.out.print("binaryTree非递归先序遍历:");
        LinkedList<Node<String>> stack = new LinkedList<Node<String>>();
        stack.push(root);
        Node<String> current = null;
        while (!stack.isEmpty()) {
            current = stack.pop();
            System.out.print(current.getValue());
            if (current.getRightChild() != null)
                stack.push(current.getRightChild());
            if (current.getLeftChild() != null)
                stack.push(current.getLeftChild());
        }
        System.out.println();
    }

    /**
     * 非递归中序遍历 栈内保存将要访问的元素
     */
    public void inorderNoRecursion() {
        System.out.print("binaryTree非递归中序遍历:");
        LinkedList<Node<String>> stack = new LinkedList<Node<String>>();
        Node<String> current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeftChild();
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.print(current.getValue());
                current = current.getRightChild();
            }
        }
        System.out.println();
    }

    /**
     * 非递归后序遍历
     * 
     * 当上一个访问的结点是右孩子或者当前结点没有右孩子则访问当前结点
     */
    public void postorderNoRecursion() {
        System.out.print("binaryTree非递归后序遍历:");
        Node<String> rNode = null;
        Node<String> current = root;
        LinkedList<Node<String>> stack = new LinkedList<Node<String>>();
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeftChild();
            }
            current = stack.pop();
            while (current != null
                    && (current.getRightChild() == null || current
                            .getRightChild() == rNode)) {
                System.out.print(current.getValue());
                rNode = current;
                if (stack.isEmpty()) {
                    System.out.println();
                    return;
                }
                current = stack.pop();
            }
            stack.push(current);
            current = current.getRightChild();
        }

    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree(new String[] { "-", "+", "/", "a", "*",
                "e", "f", "", "", "b", "-", "", "", "", "", "", "",
                "", "", "", "", "c", "d" });
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

class Node<V> {
    private V value;
    private Node<V> leftChild;
    private Node<V> rightChild;

    public Node() {
    };

    public Node(V value) {
        this.value = value;
        leftChild = null;
        rightChild = null;
    }

    public void setLeftChild(Node<V> lNode) {
        this.leftChild = lNode;
    }

    public void setRightChild(Node<V> rNode) {
        this.rightChild = rNode;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<V> getLeftChild() {
        return leftChild;
    }

    public Node<V> getRightChild() {
        return rightChild;
    }

}
