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

package org.mingle.orange.util.file.xml;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This program evaluates XPath expressions.
 * 
 * @author mingle
 */
public class XPathTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new XPathFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}

/**
 * This frame shows an XML document, a panel to type an XPath expression, and a
 * text field to display the result.
 */
class XPathFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 4118430781650583410L;

    private DocumentBuilder builder;
    private Document doc;
    private XPath path;
    private JTextField expression;
    private JTextField result;
    private JTextArea docText;
    private JComboBox<String> typeCombo;

    /**
     * 
     */
    public XPathFrame() {
        this.setTitle("XPathTest");

        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        fileMenu.add(openItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);

        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                evaluate();
            }
        };

        expression = new JTextField(20);
        expression.addActionListener(listener);
        JButton evaluateButton = new JButton("Evaluate");
        evaluateButton.addActionListener(listener);

        typeCombo = new JComboBox<String>(new String[] { "STRING", "NODE",
                "NODESET", "NUMBER", "BOOLEAN" });
        typeCombo.setSelectedItem("STRING");

        JPanel panel = new JPanel();
        panel.add(expression);
        panel.add(typeCombo);
        panel.add(evaluateButton);

        docText = new JTextArea(10, 40);
        result = new JTextField();
        result.setBorder(new TitledBorder("Result"));

        this.add(panel, BorderLayout.NORTH);
        this.add(new JScrollPane(docText), BorderLayout.CENTER);
        this.add(result, BorderLayout.SOUTH);

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            JOptionPane.showMessageDialog(this, e);
        }

        XPathFactory xpfactory = XPathFactory.newInstance();
        path = xpfactory.newXPath();
        this.pack();
    }

    /**
     * Open a file and load the document.
     */
    public void openFile() {
        JFileChooser chooser = new JFileChooser(new File("."));
        chooser.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
                return "XML files";
            }

            @Override
            public boolean accept(File f) {
                return f.isDirectory()
                        || f.getName().toLowerCase().endsWith("xml");
            }
        });

        int r = chooser.showOpenDialog(this);
        if (r != JFileChooser.APPROVE_OPTION)
            return;
        File f = chooser.getSelectedFile();
        byte[] bytes = new byte[(int) f.length()];
        try {
            new FileInputStream(f).read(bytes);
            docText.setText(new String(bytes));
            doc = builder.parse(f);
        } catch (IOException | SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void evaluate() {
        try {
            String typeName = (String) typeCombo.getSelectedItem();
            QName returnType = (QName) XPathConstants.class.getField(typeName).get(null);
            Object evalResult = path.evaluate(expression.getText(), doc, returnType);
            
            if (typeName.equals("NODESET")) {
                NodeList list = (NodeList) evalResult;
                StringBuilder builder = new StringBuilder();
                builder.append("{");
                for (int i = 0; i < list.getLength(); i++) {
                    if (i > 0) builder.append(", ");
                    builder.append("" + list.item(i));
                }
                builder.append("}");
                result.setText("" + builder);
            } else {
                result.setText("" + evalResult);
            }
        } catch (IllegalArgumentException | IllegalAccessException
                | NoSuchFieldException | SecurityException | XPathExpressionException e) {
            result.setText("" + e);
            e.printStackTrace();
        }
    }
}
