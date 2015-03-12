package org.mingle.orange.arithmetic.base;

import java.io.IOException;

@SuppressWarnings("serial")
public class ExceptionTest extends IOException {
	
	public static void testException() {
		try {
			throw new IOException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			ExceptionTest.testException();
		} catch (Exception e) {
			System.out.println("Exception");
		}

	}

}
