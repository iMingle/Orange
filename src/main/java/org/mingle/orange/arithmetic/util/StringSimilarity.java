/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;

/**
 * 字符串的相似度,把s->t所需要的操作次数定义为距离,则相似度为'距离+1'的倒数
 * 
 * @since 1.8
 * @author Mingle
 */
public class StringSimilarity {
	public static int calculateStringDistance(String src, int srcBegin,
			int srcEnd, String dst, int dstBegin, int dstEnd) {
		if (srcBegin > srcEnd) {
			if (dstBegin > dstEnd)
				return 0;
			else
				return dstEnd - dstBegin + 1;
		}

		if (dstBegin > dstEnd) {
			if (srcBegin > srcEnd)
				return 0;
			else
				return srcEnd - srcBegin + 1;
		}

		if (src.charAt(srcBegin) == dst.charAt(dstBegin))
			return calculateStringDistance(src, srcBegin + 1, srcEnd, dst,
					dstBegin + 1, dstEnd);
		else {
			int t1 = calculateStringDistance(src, srcBegin + 1, srcEnd, dst,
					dstBegin + 2, dstEnd);
			int t2 = calculateStringDistance(src, srcBegin + 2, srcEnd, dst,
					dstBegin + 1, dstEnd);
			int t3 = calculateStringDistance(src, srcBegin + 2, srcEnd, dst,
					dstBegin + 2, dstEnd);

			return Math.min(Math.min(t1, t2), t3) + 1;
		}
	}

	public static int calculateStringDistance(String src, String dst) {
		int lengthSrc = src.length();
		int lengthDst = dst.length();
		// Record the distance of all begin points of each string
		int[][] c = new int[lengthSrc + 1][lengthDst + 1]; 

		// i: begin point of strA
		// j: begin point of strB
		for (int i = 0; i < lengthSrc; i++)
			c[i][lengthDst] = lengthSrc - i;
		for (int j = 0; j < lengthDst; j++)
			c[lengthSrc][j] = lengthDst - j;
		c[lengthSrc][lengthDst] = 0;

		for (int i = lengthSrc - 1; i >= 0; i--)
			for (int j = lengthDst - 1; j >= 0; j--) {
				if (dst.charAt(j) == src.charAt(i))
					c[i][j] = c[i + 1][j + 1];
				else
					c[i][j] = Math.min(Math.min(c[i][j + 1], c[i + 1][j]), c[i + 1][j + 1]) + 1;
			}

		return c[0][0];
	}
}
