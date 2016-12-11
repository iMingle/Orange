/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mingle.orange.designpattern.structural.facade;

import java.io.InputStream;

/**
 * 接受字符串并产生一个标识符流,一次产生一个标识符(Token)
 * 
 * @author mingle
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
