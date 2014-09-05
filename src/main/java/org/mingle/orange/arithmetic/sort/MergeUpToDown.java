package org.mingle.orange.arithmetic.sort;


public class MergeUpToDown {
	@SuppressWarnings("rawtypes")
	private static Comparable[] aux;
	private static int copyCount;
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		
		sort(a, 0, a.length - 1);
	}
	
	@SuppressWarnings("rawtypes")
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;
		
		int middle = lo + (hi - lo) / 2;
		
		sort(a, lo, middle);
		sort(a, middle + 1, hi);
		
		merge(a, lo, middle, hi);
 	}
	
	@SuppressWarnings("rawtypes")
	private static void merge(Comparable[] a, int lo, int middle, int hi) {
		int i = lo;
		int j = middle + 1;
		
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
			copyCount++;
		}
		
		for (int k = lo; k <= hi; k++) {
			
			System.out.printf("i = %d, j = %d, k = %d\n", i, j, k);
			
			if (i > middle) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (less(aux[i], aux[j])) a[k] = aux[i++];
			else a[k] = aux[j++];
			copyCount++;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
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
		
		System.out.println(copyCount);
		
		assert isSorted(array);
		
		show(array);
	}
}
