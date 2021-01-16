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
 * 莱文斯坦距离允许增加、删除、替换字符这三个编辑操作。
 *
 * @author mingle
 */
public class LevenshteinDistance {

    /**
     * 如果：a[i]!=b[j]，那么：min_edist(i, j)就等于：
     * min(min_edist(i-1,j)+1, min_edist(i,j-1)+1, min_edist(i-1,j-1)+1)
     * <p>
     * 如果：a[i]==b[j]，那么：min_edist(i, j)就等于：
     * min(min_edist(i-1,j)+1, min_edist(i,j-1)+1，min_edist(i-1,j-1))
     * <p>
     * 其中，min表示求三数中的最小值。
     *
     * @param word1 word1
     * @param word2 word2
     * @return min distance
     */
    public static int minDistance(String word1, String word2) {
        if (Objects.isNull(word1) || Objects.isNull(word2)) {
            return Objects.nonNull(word1) ? word1.length() : Objects.nonNull(word2) ? word2.length() : 0;
        }

        int word1Length = word1.length();
        int word2Length = word2.length();

        // 有一个字符串为空串
        if (word1Length * word2Length == 0) {
            return word1Length + word2Length;
        }

        // DP 数组
        int[][] minDistance = new int[word1Length + 1][word2Length + 1];
        // 边界状态初始化
        for (int i = 0; i < word1Length + 1; i++) {
            minDistance[i][0] = i;
        }
        for (int j = 0; j < word2Length + 1; j++) {
            minDistance[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < word1Length + 1; i++) {
            for (int j = 1; j < word2Length + 1; j++) {
                int left = minDistance[i - 1][j] + 1;
                int down = minDistance[i][j - 1] + 1;
                int leftDown = minDistance[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    leftDown += 1;
                }

                minDistance[i][j] = Math.min(left, Math.min(down, leftDown));
            }
        }

        return minDistance[word1Length][word2Length];
    }

    public static void main(String[] args) {
        String word1 = "mitcmu";
        String word2 = "mtacnu";

        System.out.println(LevenshteinDistance.minDistance(word1, word2));
    }
}
