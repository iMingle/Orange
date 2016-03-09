/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.exception;

import java.io.FileNotFoundException;

/**
 *
 * @since 1.0
 * @author Mingle
 */
public class ExceptionTest {
    
    public static void throwException() throws Exception {
        int a = 12;
        
        if (a > 13) throw new NumberFormatException();
    }

    /**
     * @param args
     * @throws Throwable 
     */
    public static void main(String[] args) throws Throwable {
        try {
            ExceptionTest.throwException();
        } catch (Exception e) {
            Throwable th = new FileNotFoundException();
            th.initCause(e);
            throw th;
        }
    }

}
