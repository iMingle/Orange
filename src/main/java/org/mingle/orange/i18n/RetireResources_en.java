/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.i18n;

import java.awt.Color;
import java.util.ListResourceBundle;

/**
 * These are the English non-string resources for the retirement calculator.
 * 
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class RetireResources_en extends ListResourceBundle {
	static final Object[][] contents = {
			// BEGIN LOCALIZE
			{ "colorPre", Color.blue }, { "colorGain", Color.white },
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
