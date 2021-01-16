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
     * 每个物品的重量
     */
    private final int[] weightItems;

    /**
     * 表示物品个数
     */
    private final int weightItemsSize;

    /**
     * 背包重量
     */
    private final int weight;

    public ZeroOneKnapsack(int[] items, int weight) {
        this.weightItems = items;
        this.weightItemsSize = items.length;
        this.weight = weight;
    }

    /**
     * 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，
     * 那可以这样调用函数：f(0, 0, a, 10, 100)
     *
     * @param index         考察到哪个物品了
     * @param currentWeight 当前已经装进去的物品的重量和
     */
    public void find(int index, int currentWeight) {
        // currentWeight==weight表示装满了;index==n表示已经考察完所有的物品
        if (currentWeight == weight || index == weightItemsSize) {
            if (currentWeight > maxWeight) maxWeight = currentWeight;
            return;
        }
        find(index + 1, currentWeight);
        // 已经超过可以背包承受的重量的时候，就不要再装了
        if (currentWeight + weightItems[index] <= weight) {
            find(index + 1, currentWeight + weightItems[index]);
        }
    }

    public static void main(String[] args) {
        int[] items = new int[]{8, 79, 58, 86, 11, 28, 62, 15, 68};
        ZeroOneKnapsack knapsack = new ZeroOneKnapsack(items, 100);
        knapsack.find(0, 0);
        System.out.println(knapsack.maxWeight);
    }
}
