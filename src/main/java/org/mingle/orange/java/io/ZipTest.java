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

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
 *
 * @author Mingle
 */
public class ZipTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ZipTestFrame frame = new ZipTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}

/**
 * A frame with a text area to show the contents of a file inside a ZIP archive,
 * a combo box to select different files in the archive, and a menu to load a
 * new archive.
 */
class ZipTestFrame extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -2418402476748694818L;
    
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;

    private JComboBox<String> fileCombo;
    private JTextArea fileText;
    private String zipname;

    public ZipTestFrame() {
        this.setTitle("ZipTest");
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // add the menu and the Open and Exit menu items
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("."));
                int r = chooser.showOpenDialog(ZipTestFrame.this);
                if (r == JFileChooser.APPROVE_OPTION) {
                    zipname = chooser.getSelectedFile().getPath();
                    fileCombo.removeAllItems();
                    scanZipFile();
                }
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        // add the text area and combo box
        fileText = new JTextArea();
        fileCombo = new JComboBox<String>();
        fileCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loadZipFile((String) fileCombo.getSelectedItem());
            }
        });

        this.add(fileCombo, BorderLayout.SOUTH);
        this.add(new JScrollPane(fileText), BorderLayout.CENTER);
    }

    /**
     * Scans the contents of the ZIP archive and populates the combo box.
     */
    public void scanZipFile() {
        new SwingWorker<Void, String>() {

            @Override
            protected Void doInBackground() throws Exception {
                ZipInputStream in = new ZipInputStream(new FileInputStream(
                        zipname));
                ZipEntry entry;
                while ((entry = in.getNextEntry()) != null) {
                    publish(entry.getName());
                    in.closeEntry();
                }
                in.close();
                return null;
            }

            /*
             * (non-Javadoc)
             * 
             * @see javax.swing.SwingWorker#process(java.util.List)
             */
            @Override
            protected void process(List<String> names) {
                for (String name : names)
                    fileCombo.addItem(name);
            }
        }.execute();
    }

    /**
     * Loads a file from the ZIP archive into the text area
     * 
     * @param name the name of the file in the archive
     */
    public void loadZipFile(final String name) {
        fileCombo.setEnabled(false);
        fileText.setText("");

        new SwingWorker<Void, Void>() {
            protected Void doInBackground() throws Exception {
                try {
                    ZipInputStream zin = new ZipInputStream(
                            new FileInputStream(zipname));
                    ZipEntry entry;
                    Scanner in = null;

                    // find entry with matching name in archive
                    while ((entry = zin.getNextEntry()) != null) {
                        if (entry.getName().equals(name)) {
                            // read entry into text area
                            in = new Scanner(zin);
                            while (in.hasNextLine()) {
                                fileText.append(in.nextLine());
                                fileText.append("\n");
                            }
                        }
                        zin.closeEntry();
                    }
                    in.close();
                    zin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void done() {
                fileCombo.setEnabled(true);
            }
        }.execute();
    }
}
