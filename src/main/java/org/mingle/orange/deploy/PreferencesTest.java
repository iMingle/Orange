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

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.prefs.Preferences;

/**
 *
 * @author mingle
 */
public class PreferencesTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                PreferencesFrame frame = new PreferencesFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}

class PreferencesFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -918914712960141078L;
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    
    /**
     * 
     */
    public PreferencesFrame() {
        Preferences root = Preferences.userRoot();
        final Preferences node = root.node("/org/mingle/corejava");
        int left = node.getInt("left", 0);
        int top = node.getInt("top", 0);
        int width = node.getInt("width", DEFAULT_WIDTH);
        int height = node.getInt("height", DEFAULT_HEIGHT);
        
        this.setBounds(left, top, width, height);
        
        String title = node.get("title", "");
        if (title.equals("")) {
            title = JOptionPane.showInputDialog("Please input a frame title:");
        }
        if (null == title) title = "";
        
        this.setTitle(title);
        
        // set up file chooser that shows XML files
        final JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        
        // accept all files ending with .xml
        chooser.setFileFilter(new FileFilter() {
            
            @Override
            public String getDescription() {
                return "XML files";
            }
            
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".xml") || f.isDirectory();
            }
        });
        
        // set up menus
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        
        JMenuItem exportItem = new JMenuItem("Export preferences");
        menu.add(exportItem);
        exportItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chooser.showSaveDialog(PreferencesFrame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        OutputStream out = new FileOutputStream(chooser.getSelectedFile());
                        node.exportSubtree(out);
                        out.close();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
        
        JMenuItem importItem = new JMenuItem("Import preferences");
        menu.add(importItem);
        importItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chooser.showOpenDialog(PreferencesFrame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        InputStream in = new FileInputStream(chooser.getSelectedFile());
                        Preferences.importPreferences(in);
                        in.close();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
        
        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                node.putInt("left", getX());
                node.putInt("top", getY());
                node.putInt("width", getWidth());
                node.putInt("height", getHeight());
                node.put("title", getTitle());
                System.exit(0);
            }
        });
    }
}
