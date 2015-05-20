/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class LabelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 0;
		outer:
//		for (; true; ) {
		while (true) {
			inner:
			for (; i < 10; i++) {
				System.out.println("i = " + i);
				if (i == 2) {
					System.out.println("continue");
					continue;
				}
				if (i == 3) {
					System.out.println("break");
					i++;
					break;
				}
				if (i == 7) {
					System.out.println("continue outer");
					i++;
					continue outer;
				}
				if (i == 8) {
					System.out.println("break outer");
					break outer;
				}
				for (int k = 0; k < 5; k++) {
					if (k == 3) {
						System.out.println("continue inner");
						continue inner;
					}
				}
			}
		}
	}

}
