package org.mingle.orange.java.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {
    
    public static void main(String[] args) {
        String regMail = "^\\w+@\\w+[.com|.cn|.net]+$";
        
        Pattern pattern = Pattern.compile(regMail);

        System.out.println(pattern.pattern());
        
        String mail = "jinminglei@yeah.com.cn.net";
        
        Matcher matcher = pattern.matcher(mail);
        
        if (matcher.find()) {
            System.out.println("start = " + matcher.start() + "; end = " + matcher.end());
            System.out.println("the email address is correct: " + matcher.group());
        } else {
            System.out.println("the email address is incorrect");
        }

        matcher.reset("mingle@163.com");
        if (matcher.find()) {
            System.out.println("the email address is correct: " + matcher.group());
        } else {
            System.out.println("the email address is incorrect");
        }

        pattern = Pattern.compile("(xy).*(y)");
        matcher = pattern.matcher("xyzzy");
        if (matcher.matches()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println("group" + i + " = " + matcher.group(i) + ":start = " +
                        matcher.start(i) + ":end = " + matcher.end(i));
            }
        } else {
            System.out.println("find group incorrect");
        }

        System.out.println(matcher.replaceFirst("$1")); // xy

        pattern = Pattern.compile("cat");
        matcher = pattern.matcher("one cat two cats in the yard");
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "dog");
        }
        System.out.println(sb.toString()); // one dog two dog
        matcher.appendTail(sb);
        System.out.println(sb.toString()); // one dog two dogs in the yard
        
        String html = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
        pattern = Pattern.compile(html);
        String htmlString = "<a href='http://mobile.sina.com.cn/'>";
        matcher = pattern.matcher(htmlString);
        if (matcher.find()) {
            System.out.println("html id correct");
        }
    }

}
