/**
 * Copyright (c) 2016, Mingle. All rights reserved.
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
 * @since 1.8
 * @author Mingle
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
