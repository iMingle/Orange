package org.mingle.orange.arithmetic.base;

import java.util.Iterator;
import java.util.Stack;

public class FixedCapacityStack<Item> {
	private Item[] a;
	private int N;
	
	private void resize(int max) {
		@SuppressWarnings("unchecked")
		Item[] temp = (Item[])new Object[max];
		
		for (int i = 0; i < N; i++) {
			temp[i] = a[i];
		}
		
		a = temp;
	}
	
	@SuppressWarnings("unchecked")
	public FixedCapacityStack(int capacity) {
		a = (Item[])new Object[capacity];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void push(Item item) {
		if (N == a.length) {
			resize(2 * a.length);
		}
		
		a[N++] = item;
	}
	
	public Item pop() {
		Item item = a[--N];
		a[N] = null;
		
		if (N > 0 && N == a.length / 4) {
			resize(a.length / 2);
		}
		return item;
	}
	
	@SuppressWarnings("unused")
	private class ReverseArrayIterator implements Iterator<Item> {
		private int i = N;

		public boolean hasNext() {
			return i > 0;
		}

		public Item next() {
			return a[--i];
		}

		public void remove() {
		}
	}
	
	public static void main(String[] args) {
		FixedCapacityStack<String> s = new FixedCapacityStack<String>(100);
		FixedCapacityStack<Integer> i = new FixedCapacityStack<Integer>(100);
		
		s.push("jin");
//		System.out.println(s.pop());
		
		for (int j = 0; j < 10; j++) {
			i.push(j);
		}
		
		Stack<String> str = new Stack<String>();
		
		for (int j = 0; j < 10; j++) {
			str.push(j + "");
		}
		
		Iterator<String> it = str.iterator();

		while (it.hasNext()) {
			String str1 = it.next();
			
			System.out.println(str1);
			it.remove();
		}

		System.out.println(it.hasNext());
		
		while (it.hasNext()) {
			String str2 = it.next();
			System.out.println(str2);
		}
	}
}
