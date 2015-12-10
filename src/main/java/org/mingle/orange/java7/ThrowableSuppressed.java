/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java7;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * ①Throwable类增加addSuppressed方法和getSuppressed方法，支持原始异常中加入被抑制的异常。
 * 异常抑制：在try和finally中同时抛出异常时，finally中抛出的异常会在异常栈中向上传递，而try中产生的原始异常会消失。
 * 
 * @since 1.8
 * @author Mingle
 */
public class ThrowableSuppressed {
	public void read(String filename) throws Exception {
		FileInputStream input = null;
		IOException readException = null;
		try {
			input = new FileInputStream(filename);
		} catch (IOException ex) {
			readException = ex; // 保存原始异常
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException ex) {
					if (readException == null) {
						readException = ex;
					}
				}
			}
			if (readException != null) {
				throw new Exception(readException);
			}
		}
	}

	public void readNew(String filename) throws IOException {
		FileInputStream input = null;
		IOException readException = null;
		try {
			input = new FileInputStream(filename);
		} catch (IOException ex) {
			readException = ex;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException ex) {
					if (readException != null) { // 此处的区别
						readException.addSuppressed(ex);	// 记录被抑制的异常
					} else {
						readException = ex;
					}
				}
			}
			if (readException != null) {
				throw readException;
			}
		}
	}
}
