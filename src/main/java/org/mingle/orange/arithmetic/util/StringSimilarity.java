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
	private static int[][] distances;
	
	public static int calculateStringDistance(String src, int srcBegin,
			int srcEnd, String dst, int dstBegin, int dstEnd) {
		distances = new int[src.length() + 1][dst.length() + 1];
		
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

		if (src.charAt(srcBegin) == dst.charAt(dstBegin)) {
			return distances[srcBegin + 1][dstBegin + 1] == 0 ? (distances[srcBegin + 1][dstBegin + 1] = calculateStringDistance(src, srcBegin + 1, srcEnd, dst,
					dstBegin + 1, dstEnd)) : distances[srcBegin + 1][dstBegin + 1];
		}
		else {
			int t1 = distances[srcBegin][dstBegin + 1] == 0 ? (distances[srcBegin][dstBegin + 1] = calculateStringDistance(src, srcBegin, srcEnd, dst,
					dstBegin + 1, dstEnd)) : distances[srcBegin][dstBegin + 1];
			int t2 = distances[srcBegin + 1][dstBegin] == 0 ? (distances[srcBegin + 1][dstBegin] = calculateStringDistance(src, srcBegin + 1, srcEnd, dst,
					dstBegin, dstEnd)) : distances[srcBegin + 1][dstBegin];
			int t3 = distances[srcBegin + 1][dstBegin + 1] == 0 ? (distances[srcBegin + 1][dstBegin + 1] = calculateStringDistance(src, srcBegin + 1, srcEnd, dst,
					dstBegin + 1, dstEnd)) : distances[srcBegin + 1][dstBegin + 1];

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