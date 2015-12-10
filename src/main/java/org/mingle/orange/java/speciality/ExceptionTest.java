/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.Logger;

/**
 * 异常测试类
 *
 * @since 1.8
 * @author Mingle
 */
public class ExceptionTest {
	
	public static void throwException() throws SimpleException {
		throw new SimpleException();
	}
	
	public static void nullPointException(Integer i) {
		if (i == null)
			throw new NullPointerException();
	}
	
	public static void reThrowException() throws Exception {
		try {
			throwException();
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw (Exception)e.fillInStackTrace();		// 异常的新发生地
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer i = 1;
		i = null;
		try {
			ExceptionTest.nullPointException(i);
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		
		try {
			throw new SimpleException();
		} catch (SimpleException e) {
			System.err.println("Caught: " + e);
			System.out.println("e.getMessage():" + e.getMessage());
			System.out.println("e.getLocalizedMessage():" + e.getLocalizedMessage());
			e.printStackTrace(System.out);
		} catch (Exception e) {
			System.out.println("catch Exception");
		}
		
		try {
			ExceptionTest.reThrowException();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class SimpleException extends Exception {

	private static final long serialVersionUID = -772921057602481279L;
	private static Logger logger = Logger.getLogger("SimpleException");
	
	/**
	 * 
	 */
	public SimpleException() {
		StringWriter trace = new StringWriter();
		printStackTrace(new PrintWriter(trace));
		logger.severe(trace.toString());
	}
	
}

class VeryImportantException extends Exception {

	private static final long serialVersionUID = 4348440400833020083L;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VeryImportantException";
	}
	
}

class NotImportantException extends Exception {

	private static final long serialVersionUID = 7627625481759864961L;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NotImportantException";
	}
	
}

class LostMessage {
	void throwImportantException() throws VeryImportantException {
		throw new VeryImportantException();
	}
	
	void throwNotImportantException() throws NotImportantException {
		throw new NotImportantException();
	}
	
	public static void main(String[] args) {
		try {
			LostMessage lm = new LostMessage();
			try {
				lm.throwImportantException();		// 异常丢失
			} finally {
				lm.throwNotImportantException();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

class CleanUp {
	private BufferedReader in;
	
	public CleanUp(String fname) throws Exception {
		try {
			in = new BufferedReader(new FileReader(fname));
		} catch (FileNotFoundException e) {
			throw e;
		} catch (Exception e) {
			try {
				in.close();
			} catch (IOException e1) {
				System.out.println("close unsuccessful");
			}
			throw e;
		} finally {
			
		}
	}
	
	public void dispose() {
		System.out.println("CleanUp clean");
	}
	
	public static void main(String[] args) {
		try {
			CleanUp needClean = new CleanUp("imp");
			try {
				needClean.toString();
			} catch (Exception e) {
				
			} finally {
				needClean.dispose();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(new Date(1428949095000L));
	}
}