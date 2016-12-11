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

package org.mingle.orange.xml;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URISyntaxException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * This program shows how to use an XML file to describe a gridbag layout.
 * 
 * @since 1.0
 * @author Mingle
 */
public class GridBagTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                String filename = ".";
                try {
                    filename = new File(GridBagTest.class.getResource(
                            "/documents/xml/fontdialog-schema.xml").toURI()).getPath();
                } catch (URISyntaxException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                JFrame frame = new FontFrame(filename);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}

/**
 * This frame contains a font selection dialog that is described by an XML file.
 * @param filename the file containing the user interface components for the dialog.
 */
class FontFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1326443867812163843L;

    private GridBagPane gridbag;
    private JComboBox<String> face;
    private JComboBox<String> size;
    private JCheckBox bold;
    private JCheckBox italic;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    @SuppressWarnings("unchecked")
    public FontFrame(String filename) {
        this.setTitle("GridBagTest");
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        gridbag = new GridBagPane(filename);
        this.add(gridbag);

        face = (JComboBox<String>) gridbag.get("face");
        size = (JComboBox<String>) gridbag.get("size");
        bold = (JCheckBox) gridbag.get("bold");
        italic = (JCheckBox) gridbag.get("italic");

        face.setModel(new DefaultComboBoxModel<String>(new String[] { "Serif",
                "SansSerif", "Monospaced", "Dialog", "DialogInput" }));
        size.setModel(new DefaultComboBoxModel<String>(new String[] { "8",
                "10", "12", "15", "18", "24", "36", "48" }));

        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setSample();
            }
        };

        face.addActionListener(listener);
        size.addActionListener(listener);
        bold.addActionListener(listener);
        italic.addActionListener(listener);

        this.setSample();
    }

    /**
     * This method sets the text sample to the selected font.
     */
    public void setSample() {
        String fontFace = (String) face.getSelectedItem();
        int fontSize = Integer.parseInt((String) size.getSelectedItem());
        JTextArea sample = (JTextArea) gridbag.get("sample");
        int fontStyle = (bold.isSelected() ? Font.BOLD : 0)
                + (italic.isSelected() ? Font.ITALIC : 0);

        sample.setFont(new Font(fontFace, fontStyle, fontSize));
        sample.repaint();
    }
}