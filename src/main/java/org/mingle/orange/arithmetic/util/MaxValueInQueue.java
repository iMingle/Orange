/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;


/**
 * 队列中取最大值
 * 
 * @since 1.8
 * @author Mingle
 */
public class MaxValueInQueue<E extends Comparable<E>> {
	private final Stack<E> stackA;
	private final Stack<E> stackB;
	
	public MaxValueInQueue(Stack<E> stackA, Stack<E> stackB) {
		this.stackA = stackA;
		this.stackB = stackB;
	}

	private E maxValue(E x, E y) {
		if (x == null || y == null)
			return null;
		if (x.compareTo(y) == 1)
			return x;
		else
			return y;
	}
	
	public E max() {
		return maxValue(stackA.max(), stackB.max());
	}
	
	public void enqueue(E element) {
		stackB.push(element);
	}
	
	public E dequeue() {
		if (stackA.empty()) {
			while (!stackB.empty())
				stackA.push(stackB.pop());
		}
		
		return stackA.pop();
	}
	
	public static class Stack<E extends Comparable<E>> {
		private final int size;
		Object item[];
		int top;
		int link2NextMaxItem[];
		int maxStackItemIndex;
		
		public Stack(int size) {
			this.size = size;
			item = new Object[size];
			link2NextMaxItem = new int[size];
			top = -1;
			maxStackItemIndex = -1;
		}
		
		public boolean empty() {
			return top == -1;
		}

		public void push(E element) {
			top++;
			if (top >= size)
				;	// 超出栈的最大存储量
			else {
				item[top] = element;
				if (max() != null) {
					if (element.compareTo(max()) == 1) {
						link2NextMaxItem[top] = maxStackItemIndex;
						maxStackItemIndex = top;
					} else
						link2NextMaxItem[top] = -1;
				} else {
					link2NextMaxItem[top] = maxStackItemIndex;
					maxStackItemIndex = top;
				}
			}
		}
		
		@SuppressWarnings("unchecked")
		public E pop() {
			E result = null;
			if (top < 0)
				;	// 没有元素了
			else {
				result = (E) item[top];
				if (top == maxStackItemIndex)
					maxStackItemIndex = link2NextMaxItem[top];
				top--;
			}
			
			return result;
		}
		
		@SuppressWarnings("unchecked")
		private E max() {
			if (maxStackItemIndex >= 0)
				return (E) item[maxStackItemIndex];
			else
				return null;
		}
	}
	
}
