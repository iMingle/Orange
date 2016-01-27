/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @since 1.8
 * @author Mingle
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
