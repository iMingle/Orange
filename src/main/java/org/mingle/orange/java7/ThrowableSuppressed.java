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

package org.mingle.orange.java7;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * ①Throwable类增加addSuppressed方法和getSuppressed方法，支持原始异常中加入被抑制的异常。
 * 异常抑制：在try和finally中同时抛出异常时，finally中抛出的异常会在异常栈中向上传递，而try中产生的原始异常会消失。
 * 
 * @author mingle
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
                        readException.addSuppressed(ex);    // 记录被抑制的异常
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
