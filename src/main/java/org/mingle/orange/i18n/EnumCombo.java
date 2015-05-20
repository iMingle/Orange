/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.i18n;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.JComboBox;

/**
 * A combo box that lets users choose from among static field
 * values whose names are given in the constructor.
 * @since 1.8
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
