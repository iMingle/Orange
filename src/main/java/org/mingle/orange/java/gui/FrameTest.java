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

package org.mingle.orange.java.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 *
 * @author mingle
 */
public class FrameTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                SimpleFrame sf = new SimpleFrame();
                sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                sf.setVisible(true);
                // make the frame max
                sf.setExtendedState(Frame.MAXIMIZED_BOTH);
                // the frame must be displayable
//                sf.setUndecorated(true);
            }
            
        });
    }

}

class SimpleFrame extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -2571992970831415674L;
    
//    private static final int width = 800;
//    private static final int height = 600;
    
    /**
     * 
     */
    public SimpleFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dimension = kit.getScreenSize();
        
        Image image = kit.getImage("src/main/resources/images/icon.gif");
        
        this.setSize(dimension.width, dimension.height);
        this.setIconImage(image);
        this.setTitle("Mingle's frame test");
    }
}
