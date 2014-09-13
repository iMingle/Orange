/**
 * @version 1.0 2014年6月24日
 * @author mingle
 */
package org.mingle.orange.java;

/**
 * @version 1.0 2014年6月24日
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 *
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
