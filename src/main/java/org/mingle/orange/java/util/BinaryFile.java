/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.util;

import java.io.*;

/**
 * 二进制文件
 *
 * @since 1.8
 * @author Mingle
 */
public class BinaryFile {
	public static byte[] read(File bFile) throws IOException {
		BufferedInputStream bf = new BufferedInputStream(new FileInputStream(
				bFile));
		try {
			byte[] data = new byte[bf.available()];
			bf.read(data);
			return data;
		} finally {
			bf.close();
		}
	}

	public static byte[] read(String bFile) throws IOException {
		return read(new File(bFile).getAbsoluteFile());
	}
}