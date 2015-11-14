/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java8;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class InterfaceDefaultMethod {
	public static void main(String[] args) {
		Formula formula = new Formula() {
			@Override public double calculate(int a) {
				return sqrt(a * 100);
			}
		};
		
		System.out.println(formula.calculate(100)); // 100.0
		System.out.println(formula.sqrt(16)); // 4.0
	}
}

interface Formula {
    double calculate(int a);

    /**
     * 默认方法
     * 
     * @param a
     * @return
     */
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
    
    /**
     * 静态方法
     * 
     * @param args
     */
    public static void main(String[] args) {
		
	}
}

