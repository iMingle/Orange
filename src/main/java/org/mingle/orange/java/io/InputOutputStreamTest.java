/*
 *
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
 * imitations under the License.
 *
 */

package org.mingle.orange.java.io;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @since 1.0
 * @author Mingle
 */
public class InputOutputStreamTest {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        InputStream in = new InputStream() {
            
            @Override
            public int read() throws IOException {
                // TODO Auto-generated method stub
                return 0;
            }
        };
        
        System.out.println(in.read());
        in.close();
    }

}
