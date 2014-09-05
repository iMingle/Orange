package org.mingle.orange.arithmetic.base;

public class FixedCapacityStackOfStrings {
	private String[] a;
	private int N;
	
	public String[] getA() {
		return a;
	}

	public void setA(String[] a) {
		this.a = a;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public FixedCapacityStackOfStrings(int capacity) {
		a = new String[capacity];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public boolean isFull() {
		return N == this.a.length;
	}
	
	public int size() {
		return N;
	}
	
	public void push(String item) {
		a[N++] = item;
	}
	
	public String pop() {
		return a[--N];
	}
}
