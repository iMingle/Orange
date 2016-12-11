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

package org.mingle.orange.arithmetic.util;


/**
 * 从无头单链表中删除节点
 * 
 * @author mingle
 */
public class DeleteRandomNode {
    public static class Node<E> {
        E data;
        Node<E> next;
    }
    
    public static void deleteRandomNode(Node<String> current) {
        Node<String> next = current.next;
        if (next != null) {
            current.next = next.next;
            current.data = next.data;
            next = null;
        }
    }
}
