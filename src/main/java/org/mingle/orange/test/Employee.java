package org.mingle.orange.test;

import java.io.Serializable;

public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8436177960389183024L;
	protected String mobile = "15524566289";
	
	protected String getInfo() {
		return "Phone: " + mobile;
	}
}
