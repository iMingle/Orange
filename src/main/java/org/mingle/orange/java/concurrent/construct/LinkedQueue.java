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

package org.mingle.orange.java.concurrent.construct;

/**
 * 
 * 
 * @author mingle
 */
public class LinkedQueue {
    protected Node head = new Node(null);
    protected Node last = head;

    protected final Object pollLock = new Object();
    protected final Object putLock = new Object();

    public void put(Object x) {
        Node node = new Node(x);
        synchronized (putLock) { // insert at end of list
            synchronized (last) {
                last.next = node; // extend list
                last = node;
            }
        }
    }

    public Object poll() { // returns null if empty
        synchronized (pollLock) {
            synchronized (head) {
                Object x = null;
                Node first = head.next; // get to first real node
                if (first != null) {
                    x = first.object;
                    first.object = null; // forget old object
                    head = first; // first becomes new head
                }
                return x;
            }
        }
    }

    static class Node { // local node class for queue
        Object object;
        Node next = null;

        Node(Object x) {
            object = x;
        }
    }
}
