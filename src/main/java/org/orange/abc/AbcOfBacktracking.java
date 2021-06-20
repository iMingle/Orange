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

/**
 * @author mingle
 */
public class AbcOfBacktracking {
    private static int knapsackMaxWeight = Integer.MIN_VALUE;

    /**
     * 二维数组找最大重量
     *
     * @return 最大重量
     */
    public static void knapsack(int[] items, int weight, int index, int currentWeight) {
        final int itemSize = items.length;
        boolean[][] mem = new boolean[itemSize][weight + 1];

        if (currentWeight == weight || index == itemSize) {
            if (currentWeight > knapsackMaxWeight) knapsackMaxWeight = currentWeight;
            return;
        }

        if (mem[index][currentWeight]) return;
        mem[index][currentWeight] = true;

        knapsack(items, weight, index + 1, currentWeight);
        if (currentWeight + items[index] <= weight)
            knapsack(items, weight, index + 1, currentWeight + items[index]);
    }

    public static void main(String[] args) {
        int[] items = new int[]{2, 2, 4, 6, 3};
        int[] values = new int[]{3, 4, 8, 9, 6};
        AbcOfBacktracking.knapsack(items, 9, 0, 0);
        System.out.println("maxWeight: " + AbcOfBacktracking.knapsackMaxWeight);
    }
}
