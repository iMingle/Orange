/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.orange.java.concurrent.constant;

/**
 * 抽象数据类型ADT,分数
 *
 * @author mingle
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
