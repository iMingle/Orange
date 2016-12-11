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

import java.awt.Color;
import java.util.ListResourceBundle;

/**
 * These are the Chinese non-string resources for the retirement calculator.
 * 
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
