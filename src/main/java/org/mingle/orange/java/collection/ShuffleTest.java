/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class ShuffleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i = 1; i < 49; i++) {
			numbers.add(i);
		}
		
		Collections.shuffle(numbers);
		List<Integer> winningCombination = numbers.subList(0, 6);
		Collections.sort(winningCombination);
		System.out.println(winningCombination);
	}

}
