/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.generic;

/**
 * generic class pair
 * 
 * @since 1.8
 * @author Mingle
 */
public class Pair<T> {
	private T first;
	private T second;
	
	/**
	 * 
	 */
	public Pair() {
	}
	
	/**
	 * @param first
	 * @param second
	 */
	public Pair(T first, T second) {
		super();
		this.first = first;
		this.second = second;
	}
	/**
	 * @return the first
	 */
	public T getFirst() {
		return first;
	}
	/**
	 * @param first the first to set
	 */
	public void setFirst(T first) {
		this.first = first;
	}
	/**
	 * @return the second
	 */
	public T getSecond() {
		return second;
	}
	/**
	 * @param second the second to set
	 */
	public void setSecond(T second) {
		System.out.println("Pair setSecond");
		this.second = second;
	}
}
