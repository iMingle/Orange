/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.i18n;

import java.awt.Color;
import java.util.ListResourceBundle;

/**
 * These are the Chinese non-string resources for the retirement calculator.
 * 
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class RetireResources_zh extends ListResourceBundle {
	static final Object[][] contents = {
			// BEGIN LOCALIZE
			{ "colorPre", Color.red }, { "colorGain", Color.blue },
			{ "colorLoss", Color.yellow }
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
