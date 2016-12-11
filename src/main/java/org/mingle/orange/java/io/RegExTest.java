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

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This program tests regular expression matching.
 * Enter a pattern and strings to match, or hit Cancel
 * to exit. If the pattern contains groups, the group
 * boundaries are displayed in the match.
 * @author mingle
 */
public class RegExTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter pattern:");
        String patternString = in.nextLine();
        
        Pattern pattern = null;
        pattern = Pattern.compile(patternString);
        
        while (true) {
            System.out.println("Enter string to match: ");
            String input = in.nextLine();
            if (input == null || input.equals("")) {
                in.close();
                return;
            }
            
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                System.out.println("Match");
                int g = matcher.groupCount();
                
                if (g > 0) {
                    for (int i = 0; i < input.length(); i++) {
                        for (int j = 1; j <= g; j++) {
                            if (i == matcher.start(j)) {
                                System.out.print('(');
                            }
                        }
                        
                        System.out.print(input.charAt(i));
                        for (int j = 1; j <= g; j++) {
                            if (i + 1 == matcher.end(j)) {
                                System.out.print(')');
                            }
                        }
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No match");
            }
        }
    }

}
