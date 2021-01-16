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

package org.orange.arithmetic.base.backtrack;

/**
 * 正则表达式中，最重要的就是通配符，通配符结合在一起，可以表达非常丰富的语义。
 * 为了方便讲解，我假设正则表达式中只包含“*”和“?”这两种通配符，并且对这两个通配符的语义稍微做些改变，
 * 其中，“*”匹配任意多个（大于等于 0 个）任意字符，“?”匹配零个或者一个任意字符。基于以上背景假设，
 * 我们看下，如何用回溯算法，判断一个给定的文本，能否跟给定的正则表达式匹配？
 *
 * @author mingle
 */
public class Pattern {
    private boolean matched = false;
    private final char[] pattern; // 正则表达式
    private final int length; // 正则表达式长度

    public Pattern(String pattern) {
        this.pattern = pattern.toCharArray();
        this.length = pattern.length();
    }

    public boolean match(String text) { // 文本串及长度
        matched = false;
        rmatch(0, 0, text.toCharArray(), text.length());
        return matched;
    }

    private void rmatch(int textIndex, int patternIndex, char[] text, int textLength) {
        if (matched) return; // 如果已经匹配了，就不要继续递归了
        if (patternIndex == length) { // 正则表达式到结尾了
            if (textIndex == textLength) matched = true; // 文本串也到结尾了
            return;
        }
        if (pattern[patternIndex] == '*') { // *匹配任意个字符
            for (int k = 0; k <= textLength - textIndex; ++k) {
                rmatch(textIndex + k, patternIndex + 1, text, textLength);
            }
        } else if (pattern[patternIndex] == '?') { // ?匹配0个或者1个字符
            rmatch(textIndex, patternIndex + 1, text, textLength);
            rmatch(textIndex + 1, patternIndex + 1, text, textLength);
        } else if (textIndex < textLength && pattern[patternIndex] == text[textIndex]) { // 纯字符匹配才行
            rmatch(textIndex + 1, patternIndex + 1, text, textLength);
        }
    }

    public static void main(String[] args) {
        Pattern pattern = new Pattern("*ava?");
        System.out.println(pattern.match("jav"));
        System.out.println(pattern.match("javas"));
        System.out.println(pattern.match("javasc"));
    }
}
