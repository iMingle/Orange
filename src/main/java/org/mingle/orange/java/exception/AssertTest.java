/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.exception;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class AssertTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int a = 3;
        assert a > 3;
        assert a > 3 : "assert test";
    }

}
