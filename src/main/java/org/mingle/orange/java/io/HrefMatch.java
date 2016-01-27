/**
 * Copyright (c) 2016, Mingle. All rights reserved.
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
 * @since 1.8
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
