/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.util.LinkedList;


/**
 * LinkedList实现的Stack数据结构
 *
 * @since 1.8
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
