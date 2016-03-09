/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.base;

import java.util.Iterator;

/**
 * 链表实现Bag
 * 
 * @since 1.0
 * @author Mingle
 */
public class Bag<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    public static void main(String[] args) {

    }
    
    private class Node {
        Item item;
        Node next;
    }
    
    public boolean isEmpty() {
        return null == first;
    }
    
    public int size() {
        return N;
    }
    
    public void add(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        
        N++;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
        }
    }
}
