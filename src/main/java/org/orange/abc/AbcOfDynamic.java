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

import java.util.Objects;

/**
 * @author mingle
 */
public class AbcOfDynamic {
    /**
     * 二维数组找最大重量
     *
     * @return 最大重量
     */
    public static int knapsack(int[] items, int weight) {
        final int itemSize = items.length;
        boolean[][] states = new boolean[itemSize][weight + 1];
        states[0][0] = true;
        if (items[0] <= weight)
            states[0][items[0]] = true;

        for (int i = 1; i < itemSize; i++) {
            for (int j = 0; j <= weight; j++) {
                if (states[i - 1][j])
                    states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= weight - items[i]; j++) {
                if (states[i - 1][j])
                    states[i][j + items[i]] = true;
            }
        }

        for (int j = weight; j >= 0; j--) {
            if (states[itemSize - 1][j]) return j;
        }

        return 0;
    }

    /**
     * 二维数组找最大重量
     *
     * @return 最大重量
     */
    public static int knapsack2(int[] items, int weight) {
        final int itemSize = items.length;
        boolean[] states = new boolean[weight + 1];
        if (items[0] <= weight)
            states[items[0]] = true;

        for (int i = 1; i < itemSize; i++) {
            for (int j = weight - items[i]; j >= 0; j--) {
                if (states[j])
                    states[j + items[i]] = true;
            }
        }

        for (int j = weight; j >= 0; j--) {
            if (states[j]) return j;
        }

        return 0;
    }

    /**
     * 在满足背包最大重量限制的前提下，二维数组找最大价值
     *
     * @return 最大价值
     */
    public static int knapsack3(int[] items, int[] values, int weight) {
        int maxValue = -1;
        final int itemSize = items.length;
        int[][] states = new int[itemSize][weight + 1];
        for (int i = 0; i < itemSize; i++) {
            for (int j = 0; j <= weight; j++) {
                states[i][j] = -1;
            }
        }

        states[0][0] = 0;
        if (items[0] <= weight)
            states[0][items[0]] = values[0];

        for (int i = 1; i < itemSize; i++) {
            for (int j = 0; j <= weight; j++) {
                if (states[i - 1][j] >= 0)
                    states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= weight - items[i]; j++) {
                if (states[i - 1][j] >= 0) {
                    int value = states[i - 1][j] + values[i];
                    if (value > states[i][j + items[i]])
                        states[i][j + items[i]] = states[i - 1][j] + values[i];
                }
            }
        }

        for (int j = weight; j >= 0; j--)
            if (states[itemSize - 1][j] > maxValue)
                return states[itemSize - 1][j];

        return maxValue;
    }

    public static int shortest(int[][] matrix) {
        if (Objects.isNull(matrix) || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] states = new int[rows][columns];
        int sum = 0;

        for (int j = 0; j < columns; j++) {
            sum += matrix[0][j];
            states[0][j] = sum;
        }

        sum = 0;
        for (int i = 0; i < rows; i++) {
            sum += matrix[i][0];
            states[i][0] = sum;
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                states[i][j] = matrix[i][j] + Math.min(states[i - 1][j], states[i][j - 1]);
            }
        }

        return states[rows - 1][columns - 1];
    }

    public static void main(String[] args) {
        int[] items = new int[]{2, 2, 4, 6, 3};
        int[] values = new int[]{3, 4, 8, 9, 6};
        System.out.println("maxWeight: " + AbcOfDynamic.knapsack(items, 9));
        System.out.println("maxWeight: " + AbcOfDynamic.knapsack2(items, 9));
        System.out.println("maxValue: " + AbcOfDynamic.knapsack3(items, values, 9));

        int[][] matrix = new int[][]{
                {1, 3, 5, 9},
                {2, 1, 3, 4},
                {5, 2, 6, 7},
                {6, 8, 4, 3}
        };

        System.out.println("matrix shortest path: " + AbcOfDynamic.shortest(matrix));
    }
}
