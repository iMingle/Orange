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

package org.orange.abc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author mingle
 */
public class AbcOfBinaryTree {
    public static void main(String[] args) {
        char[] arrayTree = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        System.out.println(Arrays.toString(arrayTree));

        System.out.println("----数组Tree遍历----");
        System.out.print("前序遍历: ");
        AbcOfBinaryTree.preOrder(arrayTree, 0);
        System.out.println();
        System.out.print("中序遍历: ");
        AbcOfBinaryTree.inOrder(arrayTree, 0);
        System.out.println();
        System.out.print("后序遍历: ");
        AbcOfBinaryTree.postOrder(arrayTree, 0);
        System.out.println();
        System.out.print("按层遍历: ");
        AbcOfBinaryTree.layerOrder(arrayTree);
        System.out.println();

        Node<Character> root = new Node<>('A',
                new Node<>('B',
                        new Node<>('D'), new Node<>('E')),
                new Node<>('C',
                        new Node<>('F'), new Node<>('G')));
        System.out.println("----链表Tree遍历----");
        System.out.print("前序遍历: ");
        AbcOfBinaryTree.preOrder(root);
        System.out.println();
        System.out.print("中序遍历: ");
        AbcOfBinaryTree.inOrder(root);
        System.out.println();
        System.out.print("后序遍历: ");
        AbcOfBinaryTree.postOrder(root);
        System.out.println();
        System.out.print("按层遍历: ");
        AbcOfBinaryTree.layerOrder(root);
        System.out.println();
    }

    public static void preOrder(Node<Character> root) {
        if (Objects.isNull(root))
            return;

        System.out.print(root.value + ", ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(Node<Character> root) {
        if (Objects.isNull(root))
            return;

        inOrder(root.left);
        System.out.print(root.value + ", ");
        inOrder(root.right);
    }

    public static void postOrder(Node<Character> root) {
        if (Objects.isNull(root))
            return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value + ", ");
    }

    public static void layerOrder(Node<Character> root) {
        if (Objects.isNull(root))
            return;

        LinkedList<Node<Character>> queue = new LinkedList<>();
        queue.add(root);
        Node<Character> current;
        while (!queue.isEmpty()) {
            current = queue.removeFirst();
            if (Objects.nonNull(current.left))
                queue.addLast(current.left);
            if (Objects.nonNull(current.right))
                queue.addLast(current.right);
            System.out.print(current.value + ", ");
        }
    }

    public static void preOrder(char[] tree, int i) {
        if (Objects.isNull(tree) || tree.length == 0 || i >= tree.length)
            return;

        System.out.print(tree[i] + ", ");
        preOrder(tree, 2 * i + 1);
        preOrder(tree, 2 * i + 2);
    }

    public static void inOrder(char[] tree, int i) {
        if (Objects.isNull(tree) || tree.length == 0 || i >= tree.length)
            return;

        inOrder(tree, 2 * i + 1);
        System.out.print(tree[i] + ", ");
        inOrder(tree, 2 * i + 2);
    }

    public static void postOrder(char[] tree, int i) {
        if (Objects.isNull(tree) || tree.length == 0 || i >= tree.length)
            return;

        postOrder(tree, 2 * i + 1);
        postOrder(tree, 2 * i + 2);
        System.out.print(tree[i] + ", ");
    }

    public static void layerOrder(char[] tree) {
        if (Objects.isNull(tree) || tree.length == 0)
            return;

        int length = tree.length;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        int current;
        while (!queue.isEmpty()) {
            current = queue.removeFirst();
            if ((2 * current + 1) <= (length - 1))
                queue.addLast(2 * current + 1);
            if ((2 * current + 2) <= (length - 1))
                queue.addLast(2 * current + 2);
            System.out.print(tree[current] + ", ");
        }
    }

    static class Node<V> {
        final V value;
        Node<V> left;
        Node<V> right;

        public Node(V value) {
            this(value, null, null);
        }

        public Node(V value, Node<V> left, Node<V> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
