/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.limit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 线程内限制,每线程一个会话
 * 
 * @since 1.8
 * @author Mingle
 */
public class ThreadPerSessionBasedService {
	public void service() {
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				OutputStream output = null;
				try {
					output = new FileOutputStream("...");
					doService(output);
				} catch (IOException e) {
					handleIOFailure();
				} finally {
					try {
						if (output != null)
							output.close();
					} catch (IOException ignore) {}
				}
			}
		};
		
		new Thread(r).start();
	}
	
	void handleIOFailure() {}

	void doService(OutputStream s) throws IOException {
		s.write(0);
		// ... possibly more handoffs ...
	}
}
