/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.util.ArrayList;
import java.util.Collection;

/**
 * foreach List测试
 * 
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
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
