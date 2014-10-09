/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.io;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class InputOutputStreamTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		InputStream in = new InputStream() {
			
			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		
		System.out.println(in.read());
	}

}
