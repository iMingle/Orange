/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.io;

import java.nio.CharBuffer;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class BufferTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CharBuffer buffer = CharBuffer.allocate(5);
		buffer.put('1');
		buffer.put('2');
		buffer.put('3');
		
		buffer.flip();
		
		buffer.put('4');
		buffer.put('5');
		
		buffer.clear();
		
		System.out.println(buffer.get());
		System.out.println(buffer.get());
		System.out.println(buffer.get());
		System.out.println(buffer.get());
		System.out.println(buffer.get());
	}

}
