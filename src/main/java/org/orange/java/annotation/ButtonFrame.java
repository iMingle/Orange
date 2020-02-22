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

package org.orange.java.annotation;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author mingle
 */
public class ButtonFrame extends JFrame {
    private static final long serialVersionUID = 9035834462481324499L;
    
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    
    private JPanel panel;
    private JButton yellowButton;
    private JButton redButton;
    private JButton blueButton;
    

    public ButtonFrame() {
        this.setTitle("ButtonTest");
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        panel = new JPanel();
        this.add(panel);
        
        yellowButton = new JButton("YELLOW");
        blueButton = new JButton("BLUE");
        redButton = new JButton("RED");
        
        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);
        
        ActionListenerInstaller.processAnnotations(this);
    }
    
    @ActionListenerFor(source = "yellowButton")
    public void yellowBackground() {
        this.panel.setBackground(Color.YELLOW);
    }
    
    @ActionListenerFor(source = "blueButton")
    public void blueBackground() {
        this.panel.setBackground(Color.BLUE);
    }
    
    @ActionListenerFor(source = "redButton")
    public void redBackground() {
        this.panel.setBackground(Color.RED);
    }
}
