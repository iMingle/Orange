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

package org.mingle.orange.java.io;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This program displays all URLs in a web page by matching a regular expression that describes the
 * <a href=...> HTML tag. Start the program as <br>
 * java HrefMatch URL
 * 
 * @since 1.0
 * @author Mingle
 */
public class HrefMatch {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // get URL string from command line or use default
        String urlString = "";
        if (args.length > 0) urlString = args[0];
        else urlString = "http://www.sina.com/";
        
        // open reader for URL
        try {
            InputStreamReader in = new InputStreamReader(new URL(urlString).openStream());
            // read contents into string builder
            StringBuilder input = new StringBuilder();
            int ch;
            
            while ((ch = in.read()) != -1) {
                input.append((char)ch);
            }
            
            // search for all occurrences of pattern
             String patternString = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
             Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
             Matcher matcher = pattern.matcher(input);
             
             while (matcher.find()) {
                 int start = matcher.start();
                 int end = matcher.end();
                 String match = input.substring(start, end);
                 System.out.println(match);
             }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
