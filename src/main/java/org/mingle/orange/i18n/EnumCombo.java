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

package org.mingle.orange.i18n;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.JComboBox;

/**
 * A combo box that lets users choose from among static field
 * values whose names are given in the constructor.
 * @author Mingle
 */
public class EnumCombo extends JComboBox<String> {
    private static final long serialVersionUID = -7321373208563123189L;

    private Map<String, Integer> table = new TreeMap<String, Integer>();

    /**
     * Constructs an EnumCombo.
     * 
     * @param cl
     *            a class
     * @param labels
     *            an array of static field names of cl
     */
    public EnumCombo(Class<?> cl, String[] labels) {
        for (String label : labels) {
            String name = label.toUpperCase().replace(' ', '_');
            int value = 0;
            try {
                java.lang.reflect.Field f = cl.getField(name);
                value = f.getInt(cl);
            } catch (Exception e) {
                label = "(" + label + ")";
            }
            table.put(label, value);
            addItem(label);
        }
        setSelectedItem(labels[0]);
    }

    /**
     * Returns the value of the field that the user selected.
     * 
     * @return the static field value
     */
    public int getValue() {
        return table.get(getSelectedItem());
    }

}
