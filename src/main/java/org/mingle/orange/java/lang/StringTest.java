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

package org.mingle.orange.java.lang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String测试类
 * 
 * @author mingle
 */
public class StringTest {
    private static String sta = "static";
    private String name = "Mingle";
    private String email;
    private String notInit;

    static {
        System.out.print("静态初始化块");
        System.out.println(sta);
    }

    {
        System.out.print("初始化块");
        System.out.println(name);
    }

    public StringTest(String email) {
        System.out.print("构造方法");
        this.email = email;
        System.out.println(this.email);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new StringTest("jinminglei@yeah.net").notInit); // null
        System.out.println(new char[0].length);
        System.out.println(-1 >>> 1);
        System.out.println(Integer.toBinaryString(-1)); // 32个1
        byte b = 127;
        System.out.println((byte) (b + 1)); // -128
        String chin = "电影";
        String chin1 = "电视";
        char[] chs = chin.toCharArray();
        for (char c : chs) {
            System.out.println(c);
        }
        System.out.println(chin.compareTo(chin1)); // 12167
        System.out.println("Love".contains("Lo"));
        System.out.printf("Row 1: [%d %f]", 5, 6.5);
        System.out.format("Row 1: [%d %f]", 5, 6.5);
    }

}

/**
 * 字符串连接性能测试,StringBuilder比+性能更好
 */
class StringLink {
    /**
     * 性能差，编译实际使用的是StringBuilder
     * 
     * @param fields
     * @return
     */
    public static String implicit(String[] fields) {
        String result = "";
        for (int i = 0; i < fields.length; i++)
            result += fields[i];
        return result;
    }

    /**
     * 性能好，应该优先使用StringBuilder
     * 
     * @param fields
     * @return
     */
    public static String explicit(String[] fields) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fields.length; i++)
            result.append(fields[i]);
        return result.toString();
    }

}

/**
 * 格式化功能由Formatter类处理
 */
class FormatterTest {
    private static Formatter formatter = new Formatter(System.out);

    public static void main(String[] args) {
        formatter.format("%-15s %5s %10s\n", "Item", "Qty", "Price");
        formatter.format("%-15s %5s %5.2f\n", "Tax", "", 9.499);
    }
}

class RegularExpressionTest {
    public static void main(String[] args) {
        System.out.println("I found you".replaceFirst("f\\w+", "love")); // I love you
        // 贪婪型
        System.out.println("I found found you".replaceAll("f\\w+", "love")); // I love love you
        // 勉强型
        System.out.println("I found found you".replaceAll("f\\w+?", "love")); // I loveund loveund you
        // 占有型
        System.out.println("I found found you".replaceAll("f\\w++", "love")); // I love love you

        String[] patterns = new String[] { "^Java", "\\Breg.*",
                "n.w\\s+h(a|i)s", "s?", "s*", "s+", "s{4}", "s{1}.", "s{0,3}" };
        Pattern p = null;
        Matcher m = null;
        for (String pattern : patterns) {
            p = Pattern.compile(pattern);
            m = p.matcher("Java now has regular expressions");
            System.out.print(m.find() + ", ");
            // true, false, true, true, true, true, false, true, true,
        }
        System.out.println();

        p = Pattern.compile("s{4}");
        m = p.matcher("assss");
        System.out.println("s{4}: " + m.find()); // true
        System.out.println(m.find(2)); // false,从指定的索引开始查找
        System.out.println(m.matches()); // false,匹配整个区域
        System.out.println(m.lookingAt()); // false,从最开始匹配

        // 贪婪型
        m = Pattern.compile("\\w+").matcher(
                "Evening is full of the linnet's wings");
        while (m.find()) {
            System.out.print(m.group() + ", ");
            // Evening, is, full, of, the, linnet, s, wings,
        }
        System.out.println();
        // 勉强型
        m = Pattern.compile("\\w+?").matcher(
                "Evening is full of the linnet's wings");
        while (m.find()) {
            System.out.print(m.group() + ", ");
            // E, v, e, n, i, n, g, i, s, f, u, l, l, o, f, t, h, e, l, i, n, n,
            // e, t, s, w, i, n, g, s,
        }
        System.out.println();

        // Pattern标记
        m = Pattern.compile("^java",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE).matcher(
                "java has regex\nJava has regex\nJAVA has pretty");
        while (m.find()) {
            System.out.print(m.group() + ", ");
        }
        System.out.println();

        // split()
        System.out.println(Arrays.toString(Pattern.compile("!!").split(
                "This!!unusual use!!of exclamation!!points")));
        
        // appendReplacement()
        m = Pattern.compile("java").matcher("java is very good, java has regex.");
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, m.group().toUpperCase());
        }
        m.appendTail(sb);
        System.out.println(sb.toString()); // JAVA is very good, JAVA has regex.
        
        m.reset("java is not very good.");
        sb.delete(0, sb.length());
        while (m.find()) {
            m.appendReplacement(sb, m.group().toUpperCase());
        }
        m.appendTail(sb);
        System.out.println(sb.toString()); // JAVA is not very good.
        
        final String threatData = 
            "56.3.42.23@02/10/2015\n" +
            "23.45.56.1@23/12/2015\n" +
            "[Next log section with different data format]";
        Scanner scanner = new Scanner(threatData);
        String pattern = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@(\\d{2}/\\d{2}/\\d{4})";
        while (scanner.hasNext(pattern)) {
            scanner.next(pattern);
            MatchResult match = scanner.match();
            String ip = match.group(1);
            String date = match.group(2);
            System.out.format("Threat at %s from %s\n", date, ip);
        }
        scanner.close();
    }
}

class ReadComment {
    private static BufferedReader reader;
    
    public static void init(String fname) {
        try {
            reader = new BufferedReader(new FileReader(new File(
                    ReadComment.class.getResource(fname).toURI())));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    public static String readComment(BufferedReader reader) {
        StringBuilder comments = new StringBuilder();
        String[] commentStyle = new String[] {"//", "/\\*", "\\*/"};
        String line = null;
        Matcher m = null;
        int start = 0;
        try {
            line = reader.readLine();
            while (line != null) {
                 m = Pattern.compile(commentStyle[0]).matcher(line);
                 if (m.find()) {
                     comments.append(line.substring(m.start())).append("\n");
                     line = reader.readLine();
                 } else {
                     m = Pattern.compile(commentStyle[1]).matcher(line);
                     if (m.find()) {
                         start = m.start();
                         
                         do {
                             m = Pattern.compile(commentStyle[2]).matcher(line);
                             if (m.find()) {
                                 comments.append(line.substring(start, m.end()));
                                 // 刚好到结尾，则重新读取一行
                                 if (m.end() == line.length()) {
                                     comments.append("\n");
                                     line = reader.readLine();
                                     break;
                                 } else {
                                     line = line.substring(m.end());
                                     break;
                                 }
                             } else {
                                 comments.append(line.substring(start)).append("\n");
                                 line = reader.readLine();
                             }
                         } while (!m.find() && line != null);
                     } else {
                         line = reader.readLine();
                     }
                 }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return comments.toString();
    }
    
    public static void main(String[] args) {
        ReadComment.init("/documents/regex/Callbacks.java");
        System.out.println(ReadComment.readComment(ReadComment.reader));
        
        Matcher m = Pattern.compile("//").matcher("// comment");
        while (m.find()) {
            System.out.println(m.group());
        }
        
        m = Pattern.compile("/\\*.+\\*/").matcher("/* comment */");
        while (m.find()) {
            System.out.println(m.group());
        }
    }
}

class StringIntern {
    public static void main(String[] args) {
        String str1 = new StringBuffer("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);    // true,JDK1.6 false
        
        String str2 = new StringBuffer("ja").append("va").toString();
        System.out.println(str2.intern() == str2);    // false,JDK1.6 false
        
        String str3 = "hello";
        System.out.println(str3.intern() == str3);    // true,JDK1.6 true
    }
}
