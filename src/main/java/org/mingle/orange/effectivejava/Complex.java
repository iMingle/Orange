/*
 *
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
 * imitations under the License.
 *
 */

package org.mingle.orange.effectivejava;

/**
 * 复数,不可变类
 * 
 * @since 1.0
 * @author Mingle
 */
public class Complex {
    private final double re;
    private final double im;
    
    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);
    public static final Complex I = new Complex(0, 1);
    
    private Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }
    
    public static Complex valueOf(double re, double im) {
        return new Complex(re, im);
    }
    
    /**
     * 基于极坐标创建复数
     * 
     * @param r
     * @param theta
     * @return
     */
    public static Complex valueOfPolar(double r, double theta) {
        return new Complex(r * Math.cos(theta), r * Math.sin(theta));
    }
    
    public double realPart() {
        return re;
    }
    
    public double imaginaryPart() {
        return im;
    }
    
    public Complex add(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }
    
    public Complex subtract(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }
    
    public Complex multiply(Complex c) {
        return new Complex(re * c.re - im * c.im, re * c.re + im * c.im);
    }
    
    public Complex divide(Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / tmp, (re * c.re - im * c.im) / tmp);
    }
    
    @Override public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Complex))
            return false;
        Complex c = (Complex) o;
        return Double.compare(re, c.re) == 0 && Double.compare(im, c.im) == 0;
    }
    
    @Override public int hashCode() {
        int result = 17 + hashDouble(re);
        result = 31 * result + hashDouble(im);
        return result;
    }
    
    private int hashDouble(double val) {
        long longBits = Double.doubleToLongBits(val);
        return (int) (longBits ^ (longBits >>> 32));
    }
    
    @Override public String toString() {
        return "(" + re + " + " + im + "i)";
    }
}
