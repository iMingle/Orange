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

package org.mingle.orange.deploy;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author mingle
 */
public class ResourceTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ResourceTestFrame frame = new ResourceTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}

/**
 * A frame that loads image and text resources.
 */
class ResourceTestFrame extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -8173636348250176715L;
    
    public ResourceTestFrame() {
        setTitle("ResourceTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        URL aboutURL = getClass().getResource("/images/about.gif");
        Image img = Toolkit.getDefaultToolkit().getImage(aboutURL);
        setIconImage(img);

        JTextArea textArea = new JTextArea();
        InputStream stream = getClass().getResourceAsStream("/documents/deploy.txt");
        Scanner in = new Scanner(stream);
        while (in.hasNext())
            textArea.append(in.nextLine() + "\n");
        add(textArea);
        in.close();
    }

    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 300;
}
