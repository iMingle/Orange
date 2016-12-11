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

package org.mingle.orange.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @since 1.0
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
