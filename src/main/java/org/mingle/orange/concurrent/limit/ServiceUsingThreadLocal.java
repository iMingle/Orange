/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.limit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 使用ThreadLocal
 * 
 * @since 1.8
 * @author Mingle
 */
public class ServiceUsingThreadLocal {
	static ThreadLocal<OutputStream> output = new ThreadLocal<>();

	public void service() {
		try {
			final OutputStream s = new FileOutputStream("...");
			Runnable r = new Runnable() {
				public void run() {
					output.set(s);
					try {
						doService();
					} catch (IOException e) {
					}

					finally {
						try {
							s.close();
						} catch (IOException ignore) {
						}
					}
				}
			};
			new Thread(r).start();
		} catch (IOException e) {
		}
	}

	void doService() throws IOException {
		((OutputStream) (output.get())).write(0);
	}
}
