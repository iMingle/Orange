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

import java.util.Objects;

/**
 * 量化两个字符串的相似度
 * <p>
 * 编辑距离指的就是，将一个字符串转化成另一个字符串，需要的最少编辑操作次数（比如增加一个字符、删除一个字符、替换一个字符）。
 * 编辑距离越大，说明两个字符串的相似程度越小；相反，编辑距离就越小，说明两个字符串的相似程度越大。
 * 对于两个完全相同的字符串来说，编辑距离就是 0。
 * <p>
 * 最长公共子串长度只允许增加、删除字符这两个编辑操作。
 *
 * @author mingle
 */
public class LongestCommonSubsequence {

    /**
     * 如果：a[i]==b[j]，那么：max_lcs(i, j)就等于：
     * max(max_lcs(i-1,j-1)+1, max_lcs(i-1, j), max_lcs(i, j-1))；
     * <p>
     * 如果：a[i]!=b[j]，那么：max_lcs(i, j)就等于：
     * max(max_lcs(i-1,j-1), max_lcs(i-1, j), max_lcs(i, j-1))；
     * <p>
     * 其中max表示求三数中的最大值。
     *
     * @param text1 text1
     * @param text2 text2
     * @return longest common subsequence
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        if (Objects.isNull(text1) || Objects.isNull(text2)) {
            return Objects.nonNull(text1) ? text1.length() : Objects.nonNull(text2) ? text2.length() : 0;
        }

        int text1Length = text1.length();
        int text2Length = text2.length();

        // 有一个字符串为空串
        if (text1Length * text2Length == 0) {
            return 0;
        }

        // DP 数组
        int[][] dp = new int[text1Length + 1][text2Length + 1];
        // 边界状态初始化
        for (int i = 0; i < text1Length + 1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < text2Length + 1; j++) {
            dp[0][j] = 0;
        }

        // 计算所有 DP 值
        for (int i = 1; i < text1Length + 1; i++) {
            for (int j = 1; j < text2Length + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[text1Length][text2Length];
    }

    public static void main(String[] args) {
        String text1 = "mitcmu";
        String text2 = "mtacnu";

        System.out.println(LongestCommonSubsequence.longestCommonSubsequence(text1, text2));
    }
}
