package org.mingle.orange.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {
	
	public static void main(String[] args) {
		String regMail = "^\\w+@\\w+[\\.com | \\.cn | \\.net]+$";
		
		Pattern pattern = Pattern.compile(regMail);
		
		String mail = "jinminglei@yeah.com.cn.net";
		
		Matcher matcher = pattern.matcher(mail);
		
		if (matcher.find()) {
			System.out.println("the email address is correct");
		} else {
			System.out.println("the email address is incorrect");
		}
		
		String html = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
		pattern = Pattern.compile(html);
		String htmlString = "<a href='http://mobile.sina.com.cn/'>";
		matcher = pattern.matcher(htmlString);
		if (matcher.find()) {
			System.out.println("html id correct");
		}
	}

}
