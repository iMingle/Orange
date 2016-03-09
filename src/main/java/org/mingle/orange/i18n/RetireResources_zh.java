/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.i18n;

import java.awt.Color;
import java.util.ListResourceBundle;

/**
 * These are the Chinese non-string resources for the retirement calculator.
 * 
 * @since 1.0
 * @author Mingle
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
