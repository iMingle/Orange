/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.java.util;

import java.util.Random;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class RandomTest {
	public static void main(String[] args) {
		Random random = new Random();
		Random random1 = new Random();
		Random ran = new Random(100l);
		Random ran1 = new Random(100l);
		byte[] bytes = new byte[10];
		System.out.println("random.nextBoolean() = " + random.nextBoolean());	// false or true
		System.out.println("random.nextDouble() = " + random.nextDouble());		// 0.0d <= x < 1.0d
		System.out.println("random1.nextDouble() = " + random1.nextDouble());
		System.out.println("random.nextFloat() = " + random.nextFloat());		// 0.0f <= x < 1.0f
		System.out.println("random.nextGaussian() = " + random.nextGaussian());
		System.out.println("random.nextInt() = " + random.nextInt());
		System.out.println("random.nextInt(5) = " + random.nextInt(5));
		System.out.println("random.nextLong() = " + random.nextLong());
		random.nextBytes(bytes);
		for (byte b : bytes) {
			System.out.print(b + ", ");
		}
		System.out.println();
		System.out.println("ran.nextDouble() = " + ran.nextDouble());	// 同一个种子，生成的随机数相同
		System.out.println("ran.nextDouble() = " + ran1.nextDouble());
	}
}
