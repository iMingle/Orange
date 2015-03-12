/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.i18n;

import java.awt.Color;
import java.util.ListResourceBundle;

/**
 * These are the German non-string resources for the retirement calculator.
 * 
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
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
