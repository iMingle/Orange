/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.object;

/**
 * 
 * @since 1.8
 * @author Mingle
 */
public abstract class Abs {
	
	private String name;
	public Abs(String name) {
		this.name = name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
