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

package org.orange.java.exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

/**
 *
 * @author mingle
 */
public class LoggerTest {

    public static void main(String[] args) {
        Logger logger = null;
        logger = Logger.getAnonymousLogger();
        logger.setLevel(Level.OFF);
        logger.info("File->Open");
        
        logger.setLevel(Level.INFO);
        logger = Logger.getLogger("org.orange.orgle");
        logger.info("Open Frame");
        
        logger.logp(Level.INFO, "LoggerTest", "main", "LoggerTest main method");
        
        logger.entering("LoggerTest", "main");
        Handler handler = null;
        try {
            handler = new FileHandler();
            // C:\Users\mingle\javan.log
            logger.addHandler(handler);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println(System.getProperty("java.util.logging.config.class"));
        System.out.println(System.getProperty("java.util.logging.config.file"));
        
        Formatter formatter = new Formatter() {
            
            @Override
            public String format(LogRecord record) {
                return record.getMessage() + " Mingle";
            }
        };
        
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File("D:\\123.txt"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        StreamHandler streamHandler = new StreamHandler(out, formatter);
        
        Logger.getLogger("org.orange.orangle").addHandler(streamHandler);
        
        Logger.getLogger("org.orange.orangle").info("ooooooooo");
        
    }
}
