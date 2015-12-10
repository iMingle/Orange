/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.util.ArrayList;
import java.util.Collection;

/**
 * foreach List测试
 * 
 * @since 1.8
 * @author Mingle
 */
public class ForEachCollectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Collection<Integer> array = new ArrayList<Integer>();
		array.add(6);
		array.add(8);
		
		for (Integer i : array) {
			System.out.println(i);
		}
	}

}
