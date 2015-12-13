/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.limit;

import java.io.OutputStream;

/**
 * 子类化非同步对象
 * 
 * @since 1.8
 * @author Mingle
 */
public class SynchronizedAddress extends Address {
	public synchronized String getStreet() {
		return super.getStreet();
	}

	public synchronized void setStreet(String s) {
		super.setStreet(s);
	}

	public synchronized void printLabel(OutputStream s) {
		super.printLabel(s);
	}
}

class Address {
	protected String street;
	protected String city;

	public String getStreet() {
		return street;
	}

	public void setStreet(String s) {
		street = s;
	}

	public void printLabel(OutputStream s) {}
}