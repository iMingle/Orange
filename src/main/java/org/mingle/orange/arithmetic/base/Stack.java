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

package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 链表实现Stack
 * 
 * @since 1.0
 * @author Mingle
 */
public class Stack<Item> implements Iterable<Item> {
    private Node first;
    private int N;
    
    public static void main(String[] args) {
        Stack<String> s;
        s = new Stack<String>();
        while (!StdIn.isEmpty())
        {
        String item = StdIn.readString();
        if (!item.equals("-"))
        s.push(item);
        else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
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
    
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return first.item;
    }
    
    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }
    
    public Item pop() {
        Item item = first.item;
        
        if (first.next != null) {
            first = first.next;
        } else {
            first = null;
        }
        
        N--;
        
        return item;
    }
    
    public Stack<String> copy(Stack<String> stack) {
        Stack<String> result = new Stack<String>();
        @SuppressWarnings("unchecked")
        ListIterator listIterator = (ListIterator) stack.iterator();
        
        if (stack.isEmpty()) {
            result = null;
        }
        
        
        while (listIterator.hasNext()) {
            result.push(listIterator.next().toString());
        }
        
        return result;
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
            System.out.println(current.item);
            current = current.next;
            return item;
        }

        public void remove() {
        }
    }
}
