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

package org.mingle.orange.java.speciality;

import java.util.LinkedList;


/**
 * LinkedList实现的Stack数据结构
 *
 * @author Mingle
 */
public class LinkedListStack<T> {
    private LinkedList<T> storage = new LinkedList<>();
    
    public void push(T t) {
        storage.addFirst(t);
    }
    
    public T peek() {
        return storage.getFirst();
    }
    
    public T pop() {
        return storage.removeFirst();
    }
    
    public boolean empty() {
        return storage.isEmpty();
    }
    
    public String toString() {
        return storage.toString();
    }
    
    public static void main(String[] args) {
        LinkedListStack<String> stack = new LinkedListStack<>();
        for (String s : "My dog has fleas".split(" ")) {
            stack.push(s);
        }
        
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
