/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.facade;

import java.io.InputStream;

/**
 * 接受字符串并产生一个标识符流,一次产生一个标识符(Token)
 * 
 * @since 1.0
 * @author Mingle
 */
public class Scanner {
    private final InputStream input;

    public Scanner(InputStream input) {
        this.input = input;
    }
    
    public Token scan() {
        return new Token();
    }

    public InputStream getInput() {
        return input;
    }
}
