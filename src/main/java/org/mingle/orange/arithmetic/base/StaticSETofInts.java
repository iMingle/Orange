package org.mingle.orange.arithmetic.base;

import java.util.Arrays;

public class StaticSETofInts {
	private int[] a;

	public StaticSETofInts(int[] keys) {
		a = new int[keys.length];
		for (int i = 0; i < keys.length; i++)
			a[i] = keys[i]; // defensive copy
		Arrays.sort(a);
	}

	public boolean contains(int key) {
		return rank(key) != -1;
	}
	
	public int howMany(int key) {
		int lo = 0;
		int hi = a.length - 1;
		int mid = 0;
		int min = 0;
		int max = 0;
		while (lo <= hi) { // Key is in a[lo..hi] or not present.
			mid = lo + (hi - lo) / 2;
			if (key < a[mid])
				hi = mid - 1;
			else if (key > a[mid])
				lo = mid + 1;
			else {
				min = mid;
				max = mid;
				break;
			}
		}
		
		lo = 0;
		hi = mid;
        while (lo < hi) {
        	int midd = lo + (hi - lo) / 2;
        	if (key > a[midd]) lo = mid + 1;
        	else {
        		min = midd;
        		hi = midd;
        	}
        }

        lo = mid;
        hi = a.length - 1;
        while (lo < hi) {
        	int midd = lo + (hi - lo) / 2;
        	if (key < a[midd]) hi = mid - 1;
        	else {
        		max = midd;
        		lo = midd;
        	}
        }
	System.out.println(max);
	System.out.println(min);
		return max - min;
	}

	private int rank(int key) { // Binary search.
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) { // Key is in a[lo..hi] or not present.
			int mid = lo + (hi - lo) / 2;
			if (key < a[mid])
				hi = mid - 1;
			else if (key > a[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] a = {1, 2, 3, 3, 3, 3, 3, 3, 4, 5, 6};
		StaticSETofInts se = new StaticSETofInts(a);
		
		System.out.println(se.howMany(3));
	}
}
