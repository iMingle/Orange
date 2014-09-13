package org.mingle.orange.arithmetic.base;

/*************************************************************************
 *  Compilation:  javac BinarySearch.java
 *  Execution:    java BinarySearch whitelist.txt < input.txt
 *  Data files:   http://algs4.cs.princeton.edu/11model/tinyW.txt
 *                http://algs4.cs.princeton.edu/11model/tinyT.txt
 *                http://algs4.cs.princeton.edu/11model/largeW.txt
 *                http://algs4.cs.princeton.edu/11model/largeT.txt
 *
 *  % java BinarySearch tinyW.txt < tinyT.txt
 *  50
 *  99
 *  13
 *
 *  % java BinarySearch largeW.txt < largeT.txt | more
 *  499569
 *  984875
 *  295754
 *  207807
 *  140925
 *  161828
 *  [3,675,966 total values]
 *  
 *************************************************************************/

public class BinarySearch1 {

    // precondition: array a[] is sorted
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        int min = 0;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else {
            	min = mid;
            	hi = mid;
            	
            	break;
            }
        }
        
        while (lo < hi) {
        	int mid = lo + (hi - lo) / 2;
        	if (key > a[mid]) lo = mid + 1;
        	else {
        		min = mid;
        		hi = mid;
        	}
        }
        
        return min;
    }

    public static void main(String[] args) {
/*        
 		@SuppressWarnings("deprecation")
		int[] whitelist = In.readInts(args[0]);

        Arrays.sort(whitelist);

        // read key; print if not in whitelist
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whitelist) == -1)
                StdOut.println(key);
        }
*/   
    	int[] a = {1, 2, 3, 4, 4, 4, 5, 6, 6, 7, 7, 8, 9, 10};
    	int j = 0;
    	
    	for (int i = 0; i < a.length; i++) {
    		j = i;
    		if (j == a.length - 1) break;
    		while (a[j + 1] == a[i]) {
    			a[j + 1] = 0;
    			j++;
    		}
    	}
    	
    	for (int i = 0; i < a.length; i++) {
    		System.out.println(a[i]);
 	}
    }
}
