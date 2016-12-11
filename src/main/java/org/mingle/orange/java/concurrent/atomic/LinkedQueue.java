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

package org.mingle.orange.java.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 非阻塞的链表
 * 
 * @author mingle
 */
public class LinkedQueue<E> {
    private static class Node<E> {
        @SuppressWarnings("unused")
        final E item;
        final AtomicReference<Node<E>> next;
        
        public Node(E item, AtomicReference<Node<E>> next) {
            super();
            this.item = item;
            this.next = next;
        }
    }
    
    private final Node<E> dummy = new Node<E>(null, null);
    @SuppressWarnings("unused")
    private final AtomicReference<Node<E>> head = new AtomicReference<Node<E>>(dummy);
    private final AtomicReference<Node<E>> tail = new AtomicReference<Node<E>>(dummy);
    
    public boolean put(E item) {
        Node<E> newNode = new Node<E>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if (curTail == tail.get()) {
                if (tailNext != null) {
                    // 队列处于中间状态,推进尾结点
                    tail.compareAndSet(curTail, tailNext);
                } else {
                    // 处于稳定状态,尝试插入新节点
                    if (curTail.next.compareAndSet(null, newNode)) {
                        // 插入操作成功,尝试推进尾结点
                        tail.compareAndSet(curTail, newNode);
                        return true;
                    }
                }
            }
        }
    }
}
