/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.i18n;

import java.awt.Color;
import java.util.ListResourceBundle;

/**
 * These are the German non-string resources for the retirement calculator.
 * 
 * @since 1.8
 * @author Mingle
 */
public class RetireResources_de extends ListResourceBundle {
	static final Object[][] contents = {
			// BEGIN LOCALIZE
			{ "colorPre", Color.yellow }, { "colorGain", Color.black },
			{ "colorLoss", Color.red }
	// END LOCALIZE
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ListResourceBundle#getContents()
	 */
	@Override
	protected Object[][] getContents() {
		return contents;
	}
}
