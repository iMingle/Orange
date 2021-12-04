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

package org.orange.arithmetic.base.dynamic;

/**
 * 正则表达式中，最重要的就是通配符，通配符结合在一起，可以表达非常丰富的语义。
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 *
 * @author mingle
 */
public class PatternMatch {

    public boolean match(String text, String pattern) {
        int m = text.length();
        int n = pattern.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (pattern.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (pattern.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (pattern.charAt(j - 1) == '?' || pattern.charAt(j - 1) == text.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        PatternMatch pattern = new PatternMatch();
        System.out.println(pattern.match("bbbbbbbabbaabbabbbbaaabbabbabaaabbababbbabbbabaaabaab",
                "b*b*ab**ba*b**b***bba"));
        System.out.println(pattern.match("java", "*ava?"));
        System.out.println(pattern.match("javas", "*ava?"));
        System.out.println(pattern.match("javasc", "*ava?"));
    }
}
