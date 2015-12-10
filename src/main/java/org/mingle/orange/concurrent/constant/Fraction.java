/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.constant;

/**
 * 抽象数据类型ADT,分数
 * 
 * @since 1.8
 * @author Mingle
 */
public class Fraction {
	protected final long numerator;
	protected final long denominator;

	public Fraction(long num, long den) {
		// normalize:
		boolean sameSign = (num >= 0) == (den >= 0);
		long n = (num >= 0) ? num : -num;
		long d = (den >= 0) ? den : -den;
		long g = gcd(n, d);
		numerator = (sameSign) ? n / g : -n / g;
		denominator = d / g;
	}

	static long gcd(long a, long b) {
		if (b == 0)
			return a;
		long r = a % b;
		return gcd(b, r);
	}

	public Fraction plus(Fraction f) {
		return new Fraction(numerator * f.denominator + f.numerator
				* denominator, denominator * f.denominator);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Fraction))
			return false;
		Fraction f = (Fraction) (other);
		return numerator * f.denominator == denominator * f.numerator;
	}

	@Override
	public int hashCode() {
		return (int) (numerator ^ denominator);
	}
	
}
