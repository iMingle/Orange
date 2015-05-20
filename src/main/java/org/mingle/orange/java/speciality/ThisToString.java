/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

/**
 * this.toString()造成递归
 *
 * @since 1.8
 * @author Mingle
 */
public class ThisToString {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ThisToString [toString()=" + this.toString() + "]";
//		return "ThisToString [toString()=" + super.toString() + "]";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new ThisToString());		// 递归错误
	}

}
