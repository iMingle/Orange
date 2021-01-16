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

import java.util.Objects;

/**
 * 量化两个字符串的相似度
 * <p>
 * 编辑距离指的就是，将一个字符串转化成另一个字符串，需要的最少编辑操作次数（比如增加一个字符、删除一个字符、替换一个字符）。
 * 编辑距离越大，说明两个字符串的相似程度越小；相反，编辑距离就越小，说明两个字符串的相似程度越大。
 * 对于两个完全相同的字符串来说，编辑距离就是 0。
 * <p>
 * 回溯是一个递归处理的过程。如果 a[i]与 b[j]匹配，我们递归考察 a[i+1]和 b[j+1]。
 * 如果 a[i]与 b[j]不匹配，那我们有多种处理方式可选：
 * 1. 可以删除 a[i]，然后递归考察 a[i+1]和 b[j]；
 * 2. 可以删除 b[j]，然后递归考察 a[i]和 b[j+1]；
 * 3. 可以在 a[i]前面添加一个跟 b[j]相同的字符，然后递归考察 a[i]和 b[j+1]；
 * 4. 可以在 b[j]前面添加一个跟 a[i]相同的字符，然后递归考察a[i+1]和 b[j]；
 * 5. 可以将 a[i]替换成 b[j]，或者将 b[j]替换成 a[i]，然后递归考察 a[i+1]和 b[j+1]。
 *
 * @author mingle
 */
public class WordDistance {

    private int minDistance = Integer.MAX_VALUE;

    public int minDistance(String word1, String word2) {
        if (Objects.isNull(word1) || Objects.isNull(word2)) {
            return Objects.nonNull(word1) ? word1.length() : Objects.nonNull(word2) ? word2.length() : 0;
        }

        distance(0, 0, 0, word1, word1.length(), word2, word2.length());

        return minDistance;
    }

    private void distance(int i, int j, int distance, String word1, int word1Length, String word2, int word2Length) {
        if (i == word1Length || j == word2Length) {
            if (i < word1Length) distance += (word1Length - i);
            if (j < word2Length) distance += (word2Length - j);
            if (distance < minDistance) minDistance = distance;
            return;
        }

        if (word1.charAt(i) == word2.charAt(j)) {
            distance(i + 1, j + 1, distance, word1, word1Length, word2, word2Length);
        } else {
            // 删除a[i]或者b[j]前添加一个字符
            distance(i + 1, j, distance + 1, word1, word1Length, word2, word2Length);
            // 删除b[j]或者a[i]前添加一个字符
            distance(i, j + 1, distance + 1, word1, word1Length, word2, word2Length);
            // 将a[i]和b[j]替换为相同字符
            distance(i + 1, j + 1, distance + 1, word1, word1Length, word2, word2Length);
        }
    }

    public static void main(String[] args) {
        String word1 = "mitcmu";
        String word2 = "mtacnu";

        WordDistance distance = new WordDistance();
        System.out.println(distance.minDistance(word1, word2));
    }
}
