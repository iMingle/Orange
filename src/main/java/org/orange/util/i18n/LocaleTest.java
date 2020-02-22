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

import java.util.Locale;

/**
 * @author <a href="mailto:jinminglei@yeah.net">Mingle</a>
 * @author mingle
 */
public class LocaleTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Locale locale = Locale.CHINA;
        System.out.println("country: " + locale.getDisplayCountry());
        System.out.println("language: " + locale.getDisplayLanguage());
        System.out.println("name: " + locale.getDisplayName());
        System.out.println("script: " + locale.getDisplayScript());
        System.out.println("variant: " + locale.getDisplayVariant());
        System.out.println("lang: " + locale.getLanguage());
        System.out.println("country: " + locale.getCountry());
    }

}
