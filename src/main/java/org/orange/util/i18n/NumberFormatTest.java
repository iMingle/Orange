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

package org.orange.util.i18n;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * This program demonstrates formatting numbers under various locales.
 * 
 * @author <a href="mailto:jinminglei@yeah.net">Mingle</a>
 * @author mingle
 */
public class NumberFormatTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new NumberFormatFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}

/**
 * This frame contains radio buttons to select a number format, a combo box to
 * pick a locale, a text field to display a formatted number, and a button to
 * parse the text field contents.
 */
class NumberFormatFrame extends JFrame {

    private static final long serialVersionUID = -6340221190096179751L;

    private Locale[] locales;
    private double currentNumber;
    private JComboBox<String> localeCombo = new JComboBox<String>();
    private JButton parseButton = new JButton("Parse");
    private JTextField numberText = new JTextField(30);
    private JRadioButton numberRadioButton = new JRadioButton("Number");
    private JRadioButton currencyRadioButton = new JRadioButton("Currency");
    private JRadioButton percentRadioButton = new JRadioButton("Percent");
    private ButtonGroup rbGroup = new ButtonGroup();
    private NumberFormat currentNumberFormat;

    public NumberFormatFrame() {
        this.setTitle("NumberFormatTest");
        this.setLayout(new GridBagLayout());

        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateDisplay();
            }

        };

        JPanel p = new JPanel();
        this.addRadioButton(p, numberRadioButton, rbGroup, listener);
        this.addRadioButton(p, currencyRadioButton, rbGroup, listener);
        this.addRadioButton(p, percentRadioButton, rbGroup, listener);

        this.add(new JLabel("Locale:"), new GBC(0, 0).setAnchor(GBC.EAST));
        this.add(p, new GBC(1, 1));
        this.add(parseButton, new GBC(0, 2).setInsets(2));
        this.add(localeCombo, new GBC(1, 0).setAnchor(GBC.WEST));
        this.add(numberText, new GBC(1, 2).setFill(GBC.HORIZONTAL));
        locales = NumberFormat.getAvailableLocales();
        Arrays.sort(locales, new Comparator<Locale>() {

            @Override
            public int compare(Locale o1, Locale o2) {
                return o1.getDisplayName().compareTo(o2.getDisplayName());
            }
        });

        for (Locale loc : locales) {
            localeCombo.addItem(loc.getDisplayName());
        }
        localeCombo.setSelectedItem(Locale.getDefault().getDisplayName());
        currentNumber = 123456.78;
        this.updateDisplay();

        localeCombo.addActionListener(listener);

        parseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s = numberText.getText().trim();
                try {
                    Number n = currentNumberFormat.parse(s);
                    if (n != null) {
                        currentNumber = n.doubleValue();
                        updateDisplay();
                    } else {
                        numberText.setText("Parse error: " + s);
                    }
                } catch (ParseException e1) {
                    numberText.setText("Parse error: " + s);
                }
            }
        });

        this.pack();
    }

    /**
     * Adds a radio button to a container.
     * 
     * @param p
     *            the container into which to place the button
     * @param b
     *            the button
     * @param g
     *            the button group
     * @param listener
     *            the button listener
     */
    private void addRadioButton(JPanel p, JRadioButton b, ButtonGroup g,
            ActionListener listener) {
        b.setSelected(g.getButtonCount() == 0);
        b.addActionListener(listener);
        g.add(b);
        p.add(b);
    }

    /**
     * Updates the display and formats the number according to the user
     * settings.
     */
    private void updateDisplay() {
        Locale currentLocale = this.locales[localeCombo.getSelectedIndex()];
        currentNumberFormat = null;

        if (numberRadioButton.isSelected())
            currentNumberFormat = NumberFormat.getNumberInstance(currentLocale);
        else if (currencyRadioButton.isSelected())
            currentNumberFormat = NumberFormat
                    .getCurrencyInstance(currentLocale);
        else if (percentRadioButton.isSelected())
            currentNumberFormat = NumberFormat
                    .getPercentInstance(currentLocale);

        String str = currentNumberFormat.format(currentNumber);
        this.numberText.setText(str);
    }
}