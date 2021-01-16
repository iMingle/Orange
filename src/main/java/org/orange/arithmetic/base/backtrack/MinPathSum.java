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
 * 假设我们有一个 n 乘以 n 的矩阵 w[n][n]。矩阵存储的都是正整数。棋子起始位置在左上角，终止位置在右下角。
 * 我们将棋子从左上角移动到右下角。每次只能向右或者向下移动一位。从左上角到右下角，会有很多不同的路径可以走。
 * 我们把每条路径经过的数字加起来看作路径的长度。那从左上角移动到右下角的最短路径长度是多少呢
 *
 * @author mingle
 */
public class MinPathSum {
    /**
     * 最短路径
     */
    private int minPath = Integer.MAX_VALUE;

    public void shortest(int i, int j, int distance, int[][] matrix, int rows, int columns) {
        distance += matrix[i][j];
        // 到达n-1, rows-1位置
        if (i == rows - 1 && j == columns - 1) {
            if (distance < minPath)
                minPath = distance;
            return;
        }

        // 往下走，更新i=i+1, j=j
        if (i < rows - 1) {
            shortest(i + 1, j, distance, matrix, rows, columns);
        }

        // 往右走，更新i=i, j=j+1
        if (j < columns - 1) {
            shortest(i, j + 1, distance, matrix, rows, columns);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 3, 5, 9},
                {2, 1, 3, 4},
                {5, 2, 6, 7},
                {6, 8, 4, 3}
        };

        MinPathSum minPathSum = new MinPathSum();
        minPathSum.shortest(0, 0, 0, matrix, 4, 4);
        System.out.println(minPathSum.minPath);
    }
}
