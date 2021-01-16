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

import java.util.Arrays;

/**
 * 0-1 背包问题有很多变体，我这里介绍一种比较基础的。我们有一个背包，背包总的承载重量是 Wkg。
 * 现在我们有 n 个物品，每个物品的重量不等，并且不可分割。我们现在期望选择几件物品，装载到背包中。
 * 在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大？
 *
 * @author mingle
 */
public class ZeroOneKnapsack {
    /**
     * 存储背包中物品总重量的最大值
     */
    private int maxWeight = Integer.MIN_VALUE;

    /**
     * 存储背包中物品总价值的最大值
     */
    private int maxValue = Integer.MIN_VALUE;

    /**
     * 每个物品的重量
     */
    private final int[] weightItems;

    /**
     * 物品的价值
     */
    private final int[] weightValue;

    /**
     * 表示物品个数
     */
    private final int weightItemsSize;

    /**
     * 背包重量
     */
    private final int weight;

    /**
     * 备忘录，默认值false
     */
    private final boolean[][] memory;

    public ZeroOneKnapsack(int[] items, int[] weightValue, int weight) {
        this.weightItems = items;
        this.weightItemsSize = items.length;
        this.weightValue = weightValue;
        this.weight = weight;
        this.memory = new boolean[this.weightItemsSize][this.weight + 1];
    }

    /**
     * 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，
     * 那可以这样调用函数：f(0, 0, a, 10, 100)
     *
     * @param index         考察到哪个物品了
     * @param currentWeight 当前已经装进去的物品的重量和
     */
    public void find(int index, int currentWeight) {
        // currentWeight==weight表示装满了，index==n表示物品都考察完了
        if (currentWeight == weight || index == weightItemsSize) {
            if (currentWeight > maxWeight) maxWeight = currentWeight;
            return;
        }
        if (memory[index][currentWeight]) return; // 重复状态
        memory[index][currentWeight] = true; // 记录(index, currentWeight)这个状态
        find(index + 1, currentWeight); // 选择不装第i个物品
        if (currentWeight + weightItems[index] <= weight) {
            find(index + 1, currentWeight + weightItems[index]); // 选择装第i个物品
        }
    }

    /**
     * 二维数组找最大重量
     *
     * @return 最大重量
     */
    public int knapsack() {
        // 默认值false
        boolean[][] states = new boolean[weightItemsSize][weight + 1];
        // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0][0] = true;
        if (weightItems[0] <= weight) {
            states[0][weightItems[0]] = true;
        }

        // 动态规划状态转移
        for (int i = 1; i < weightItemsSize; ++i) {
            // 不把第i个物品放入背包
            for (int j = 0; j <= weight; ++j) {
                if (states[i - 1][j])
                    states[i][j] = states[i - 1][j];
            }
            // 把第i个物品放入背包
            for (int j = 0; j <= weight - weightItems[i]; ++j) {
                if (states[i - 1][j])
                    states[i][j + weightItems[i]] = true;
            }
        }

        // 输出结果
        for (int j = weight; j >= 0; --j) {
            if (states[weightItemsSize - 1][j]) return j;
        }

        return 0;
    }

    /**
     * 一维数组找最大重量
     *
     * @return 最大重量
     */
    public int knapsack2() {
        // 默认值false
        boolean[] states = new boolean[weight + 1];
        // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0] = true;
        if (weightItems[0] <= weight) {
            states[weightItems[0]] = true;
        }

        // 动态规划
        for (int i = 1; i < weightItemsSize; ++i) {
            // 把第i个物品放入背包
            for (int j = weight - weightItems[i]; j >= 0; --j) {
                if (states[j])
                    states[j + weightItems[i]] = true;
            }
        }

        // 输出结果
        for (int i = weight; i >= 0; --i) {
            if (states[i]) return i;
        }

        return 0;
    }

    /**
     * 在满足背包最大重量限制的前提下，二维数组找最大价值
     *
     * @return 最大价值
     */
    public int knapsack3() {
        // 存储价值
        int[][] states = new int[weightItemsSize][weight + 1];
        // 初始化
        for (int i = 0; i < weightItemsSize; i++) {
            for (int j = 0; j <= weight; j++) {
                states[i][j] = -1;
            }
        }

        states[0][0] = 0;
        if (weightItems[0] < weight) {
            states[0][weightItems[0]] = weightValue[0];
        }

        for (int i = 1; i < weightItemsSize; i++) {
            // 不选择第i个物品
            for (int j = 0; j <= weight; j++) {
                if (states[i - 1][j] >= 0)
                    states[i][j] = states[i - 1][j];
            }

            // 选择第i个物品
            for (int j = 0; j <= weight - weightItems[i]; j++) {
                if (states[i - 1][j] >= 0) {
                    int value = states[i - 1][j] + weightValue[i];
                    if (value > states[i][j + weightItems[i]]) {
                        states[i][j + weightItems[i]] = value;
                    }
                }
            }
        }

        // 找最大值
        for (int j = 0; j <= weight; j++) {
            if (states[weightItemsSize - 1][j] > maxValue)
                maxValue = states[weightItemsSize - 1][j];
        }

        return maxValue;
    }

    public static void main(String[] args) {
        int[] items = new int[]{2, 2, 4, 6, 3};
        int[] value = new int[]{3, 4, 8, 9, 6};
        ZeroOneKnapsack knapsack = new ZeroOneKnapsack(items, value, 9);
        knapsack.find(0, 0);
        System.out.println("weightItems: " + Arrays.toString(items));
        System.out.println("weightValue: " + Arrays.toString(value));
        System.out.println("maxWeight: " + knapsack.maxWeight);

        System.out.println("maxWeight: " + knapsack.knapsack());
        System.out.println("maxWeight: " + knapsack.knapsack2());
        System.out.println("maxValue: " + knapsack.knapsack3());
    }
}
