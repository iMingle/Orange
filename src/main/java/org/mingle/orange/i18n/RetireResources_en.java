/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.i18n;

import java.awt.Color;
import java.util.ListResourceBundle;

/**
 * These are the English non-string resources for the retirement calculator.
 * 
 * @since 1.8
 * @author Mingle
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
