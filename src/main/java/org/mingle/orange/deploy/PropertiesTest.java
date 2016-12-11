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

package org.mingle.orange.deploy;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Mingle
 */
public class PropertiesTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                PropertiesFrame frame = new PropertiesFrame();
                frame.setVisible(true);
            }
        });
    }
}

class PropertiesFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 7839346953688015751L;
    
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    
    private File propertiesFile;
    private Properties settings;
    
    /**
     * 
     */
    public PropertiesFrame() {
        String userDir = System.getProperty("user.home");
        File propertiesDir = new File(userDir, ".corejava");
        if (!propertiesDir.exists()) propertiesDir.mkdir();
        propertiesFile = new File(propertiesDir, "program.properties");
        
        Properties defaultSettings = new Properties();
        defaultSettings.put("left", 0);
        defaultSettings.put("top", 0);
        defaultSettings.put("width", "" + DEFAULT_WIDTH);
        defaultSettings.put("height", "" + DEFAULT_HEIGHT);
        defaultSettings.put("title", "");
        
        settings = new Properties(defaultSettings);
        
        if (propertiesFile.exists()) {
            try {
                FileInputStream in = new FileInputStream(propertiesFile);
                settings.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        int left = Integer.parseInt(settings.getProperty("left"));
        int top = Integer.parseInt(settings.getProperty("top"));
        int width = Integer.parseInt(settings.getProperty("width"));
        int height = Integer.parseInt(settings.getProperty("height"));
        
        this.setBounds(left, top, width, height);
        
        String title = settings.getProperty("title");
        if (title.equals("")) {
            title = JOptionPane.showInputDialog("Please input a frame title:");
        }
        if (null == title) title = "";
        
        this.setTitle(title);
        
        this.addWindowListener(new WindowAdapter() {
            /* (non-Javadoc)
             * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
             */
            @Override
            public void windowClosing(WindowEvent e) {
                settings.put("left", "" + PropertiesFrame.this.getX());
                settings.put("top", "" + PropertiesFrame.this.getY());
                settings.put("width", "" + PropertiesFrame.this.getWidth());
                settings.put("height", "" + PropertiesFrame.this.getHeight());
                settings.put("title", PropertiesFrame.this.getTitle());
                
                try {
                    FileOutputStream out = new FileOutputStream(propertiesFile);
                    settings.store(out, "Program Properties");
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
    }
}
