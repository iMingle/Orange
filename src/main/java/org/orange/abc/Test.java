/*
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
 * limitations under the License.
 */

package org.orange.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串由单词和下划线组成，要求单词倒序输出、下划线正序输出
 * 示例：
 * 输入：“my_name____is__dd”
 * 输出：“dd_is____name__my”
 * @author mingle
 */
public class Test {
    public static void main(String[] args) {
        String s = "__my_name____is__dd_";
        System.out.println(Test.reverse(s));

    }

    public static String reverse(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        List<String> words = new ArrayList<>();
        List<String> lines = new ArrayList<>();

        int j = 0;
        boolean isWord = str.charAt(0) != '_';
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == '_') {
                if (isWord) {
                    words.add(str.substring(j, i));
                    j = i;
                }
                isWord = false;
            } else {
                if (!isWord) {
                    lines.add(str.substring(j, i));
                    j = i;
                }
                isWord = true;
            }
        }

        if (isWord) {
            words.add(str.substring(j));
        } else {
            lines.add(str.substring(j));
        }


        StringBuilder sb = new StringBuilder();
        if (str.charAt(0) == '_') {
            int p = words.size() - 1;
            int q = 0;
            while (p >= 0 || q < lines.size()) {
                if (q < lines.size()) {
                    sb.append(lines.get(q++));
                }
                if (p >= 0) {
                    sb.append(words.get(p--));
                }
            }
        } else {
            int p = words.size() - 1;
            int q = 0;
            while (p >= 0 || q < lines.size()) {
                if (p >= 0) {
                    sb.append(words.get(p--));
                }
                if (q < lines.size()) {
                    sb.append(lines.get(q++));
                }
            }
        }

        return sb.toString();
    }
}
