/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class CharsetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Charset cset = Charset.forName("UTF-8");
		System.out.println(cset);
		Set<String> aliases = cset.aliases();
		for (String alias : aliases)
			System.out.println(alias);
		
		Map<String, Charset> charsets = Charset.availableCharsets();
		for (String name : charsets.keySet()) {
			System.out.println(name);
		}
		
		// encode and decode
		String str = "靳明雷";
		ByteBuffer buffer = cset.encode(str);
		System.out.println("after encode : " + buffer.toString());
		byte[] bytes = buffer.array();
		for (byte b : bytes) {
			System.out.println(b);
		}
		
		ByteBuffer bbuf = ByteBuffer.wrap(bytes, 0, bytes.length);
		CharBuffer cbuf = cset.decode(bbuf);
		System.out.println("after decode : " + cbuf.toString());
		
		
	}

}
