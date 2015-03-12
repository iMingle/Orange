package org.mingle.orange.hello;

public class SearchAlgorithm {
	
	public static int binarySearch(int[] array, int key) {
		int start = 0;
		int end = array.length - 1;
		int middle = 0;
		
		while (start <= end) {
			middle = start + (end - start) / 2;
			
			if (key > array[middle]) {
				start = middle + 1;
			} else if (key < array[middle]) {
				end = middle - 1;
			} else {
				return middle;
			}
		}
		
		return -1;
	}

	public static void main(String[] args) {
		int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		System.out.println(array[SearchAlgorithm.binarySearch(array, 6)]);
	}

}
