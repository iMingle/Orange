/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.io;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @since 1.8
 * @author Mingle
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
		in.close();
	}

}
