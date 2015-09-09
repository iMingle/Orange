/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

/**
 * javap -c Atomicity
 *
 * @since 1.8
 * @author Mingle
 */
public class Atomicity {
	int i;
	
	void f1() {
		i++;
	}
	
	void f2() {
		i += 3;
	}

	public static void main(String[] args) {
		
	}

}
