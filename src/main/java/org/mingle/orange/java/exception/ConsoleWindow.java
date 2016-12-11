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

package org.mingle.orange.java.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author mingle
 */
public class ConsoleWindow {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    private static final int DEFAULT_LEFT = 200;
    private static final int DEFAULT_TOP = 200;
    
    public static void init() {
        JFrame frame = new JFrame();
        frame.setTitle("ConsoleWindow");
        final JTextArea output = new JTextArea();
        output.setEditable(false);
        frame.add(new JScrollPane(output));
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        frame.setLocation(DEFAULT_LEFT, DEFAULT_TOP);
        frame.setFocusableWindowState(false);
        frame.setVisible(true);
        
        // define a PrintStream that sends its bytes to the output text area
        PrintStream consoleStream = new PrintStream(new OutputStream() {
            
            @Override
            public void write(int b) throws IOException {}
            /* (non-Javadoc)
             * @see java.io.OutputStream#write(byte[], int, int)
             */
            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                output.append(new String(b, off, len));
            }
        });
        
        // set both the System.out and the System.err to the stream
        System.setOut(consoleStream);
        System.setErr(consoleStream);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ConsoleWindow.init();
        System.out.println("Hello, Console");
    }

}
