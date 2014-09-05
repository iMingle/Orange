﻿package org.mingle.orange.arithmetic.sort;

import edu.princeton.cs.introcs.StdRandom;

public class Quick3way {
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;
		
		int lt = lo;
		int i = lo + 1;
		int gt = hi;
		
		Comparable v = a[lo];
		
		while(i <= gt) {
			int cmp = a[i].compareTo(v);
			
			if (cmp < 0) exch(a, lt++, i++);
			else if (cmp > 0) exch(a, i, gt--);
			else i++;
		}
		
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
 	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	@SuppressWarnings("rawtypes")
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
	
	public static void main(String[] args) {
		Integer[] array = {4, 5, 2, 1, 3, 9, 8, 7, 6, 10};
		sort(array);
		
		assert isSorted(array);
		
		show(array);
	}
}
