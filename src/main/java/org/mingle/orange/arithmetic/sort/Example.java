package org.mingle.orange.arithmetic.sort;

import edu.princeton.cs.introcs.In;

public class Example {
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	@SuppressWarnings({ "unused", "rawtypes" })
	private static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	@SuppressWarnings("rawtypes")
	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		
		System.out.println();
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i - 1])) return false;
		}
		
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		String[] s = In.readStrings();
		sort(s);
		
		assert isSorted(s);
		
		show(s);
	}
}
