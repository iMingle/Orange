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

package org.orange.arithmetic.base;

public class Rational {
    private int numerator;
    private int denominator;
    
    public Rational(int numerator, int denominator) {
        super();
        this.numerator = numerator;
        this.denominator = denominator;
    }
    
    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public Rational plus(Rational b) {
        Rational result = new Rational(1, 1);
        int temp = Gcd.gcd(this.getDenominator(), b.getDenominator());

        result.setDenominator(this.getDenominator() * b.getDenominator() / temp);
        result.setNumerator(this.getNumerator() * b.getDenominator() / temp + b.getNumerator() * this.getDenominator() / temp);
        
        temp = Gcd.gcd(result.getDenominator(), result.getNumerator());

        result.setDenominator(result.getDenominator() / temp);
        result.setNumerator(result.getNumerator() / temp);
        
        return result;
    }
    
    public Rational minus(Rational b) {
        Rational result = new Rational(1, 1);
        int temp = Gcd.gcd(this.getDenominator(), b.getDenominator());

        result.setDenominator(this.getDenominator() * b.getDenominator() / temp);
        result.setNumerator(this.getNumerator() * b.getDenominator() / temp - b.getNumerator() * this.getDenominator() / temp);
        
        temp = Gcd.gcd(result.getDenominator(), result.getNumerator());
        temp = Math.abs(temp);

        result.setDenominator(result.getDenominator() / temp);
        result.setNumerator(result.getNumerator() / temp);
        
        assert result.getDenominator() == 0 : "分母为0";
        
        return result;
    }
    
    public Rational times(Rational b) {
        Rational result = new Rational(1, 1);
        int temp = 1;

        result.setDenominator(this.getDenominator() * b.getDenominator());
        result.setNumerator(this.getNumerator() * b.getNumerator());
        
        temp = Gcd.gcd(result.getDenominator(), result.getNumerator());
        temp = Math.abs(temp);

        result.setDenominator(result.getDenominator() / temp);
        result.setNumerator(result.getNumerator() / temp);
        
        assert result.getDenominator() == 0 : "分母为0";
        
        return result;
    }
    
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + denominator;
        result = prime * result + numerator;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rational other = (Rational) obj;
        if (denominator != other.denominator)
            return false;
        if (numerator != other.numerator)
            return false;
        return true;
    }

    public boolean equals(Rational obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Rational that = (Rational)obj;
        if (this.numerator != that.numerator) return false;
        if (this.denominator != that.denominator) return false;
        
        return true;
    }
    
    public static void main(String[] args) {
        Rational r1 = new Rational(5, 6);
        Rational r2 = new Rational(3, 8);
        Rational result = new Rational(1, 1);
        result = r2.times(r1);
        
        System.out.println(result.getNumerator());
        System.out.println(result.getDenominator());
        
    }

}
