/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.i18n;

import java.util.Locale;

/**
 * @author <a href="mailto:jinminglei@yeah.net">Mingle</a>
 * @version 1.0
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
