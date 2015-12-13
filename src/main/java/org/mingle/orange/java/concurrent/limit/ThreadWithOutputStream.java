/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.limit;

import java.io.OutputStream;

/**
 * 线程私有成员变量
 * 
 * @since 1.8
 * @author Mingle
 */
public class ThreadWithOutputStream extends Thread {
	private OutputStream output;
	
	public ThreadWithOutputStream(Runnable r, OutputStream s) {
		super(r);
		output = s;
	}
	
	public static ThreadWithOutputStream current() {
		return (ThreadWithOutputStream) currentThread();
	}

	public static OutputStream getOutput() {
		return current().output;
	}

	public static void setOutput(OutputStream output) {
		current().output = output;
	}
}
