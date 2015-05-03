/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author <a href="mailto:jinml@legendsec.com">mingle</a>
 * @version 1.0
 */
public class StringFilterHTMLTagUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String html = "<div>123<span>456</span>789</div>";
		
		System.out.println(StringFilterHTMLTagUtil.removeHTMLTag(html));
	}

	public static String removeHTMLTag(String in) {
		String regular = "<[^>]+>";

		Pattern pattern = Pattern.compile(regular);
		Matcher matcher = pattern.matcher(in);
		
		return matcher.replaceAll("");
	}
}
