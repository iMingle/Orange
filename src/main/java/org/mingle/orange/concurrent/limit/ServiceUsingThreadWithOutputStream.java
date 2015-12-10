/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.limit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 使用线程私有成员变量类
 * 
 * @since 1.8
 * @author Mingle
 */
public class ServiceUsingThreadWithOutputStream {
	public void service() throws IOException {
		OutputStream output = new FileOutputStream("...");
		Runnable r = new Runnable() {
			public void run() {
				try {
					doService();
				} catch (IOException e) {
				}
			}
		};
		new ThreadWithOutputStream(r, output).start();
	}

	@SuppressWarnings("static-access")
	void doService() throws IOException {
		ThreadWithOutputStream.current().getOutput().write(0);
	}
}
