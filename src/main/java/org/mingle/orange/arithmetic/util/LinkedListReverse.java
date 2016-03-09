/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;

/**
 * 单链表反转
 * 
 * @since 1.0
 * @author Mingle
 */
public class LinkedListReverse {
    private static class Node<E> {
        E data;
        Node<E> next;
        
        @Override
        public String toString() {
            return "Node [data=" + data + "]";
        }
        
    }
    
    public static <E> Node<E> reverse(Node<E> head) {
        if (null == head || null == head.next)
            return head;
        Node<E> pre = head;
        Node<E> current = head.next;
        Node<E> next;
        while (current != null) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        head.next = null;
        head = pre;
        
        return head;
    }
    
    public static <E> Node<E> reverseRecursive(Node<E> head) {
        if (null == head || null == head.next)
            return head;
        Node<E> recursiveHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        
        return recursiveHead;
    }

    public static void main(String[] args) {
        Node<String> head = new Node<>();
        Node<String> temp, current = null;
        head.data = "head";
        for (int i = 0; i < 10; i++) {
            temp = new Node<>();
            temp.data = "node" + i;
            if (0 == i)
                head.next = temp;
            else
                current.next = temp;
            current = temp;
        }
        
//        head = LinkedListReverse.reverse(head);
        head = LinkedListReverse.reverseRecursive(head);
        
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }
    }

}
