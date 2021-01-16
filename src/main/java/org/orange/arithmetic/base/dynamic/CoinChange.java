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
 * 拼接m块钱需要最少的硬币数量
 *
 * @author mingle
 */
public class CoinChange {
    /**
     * @param coins  kinds of coins
     * @param amount coins total amount of money amount
     * @return the fewest number of coins that you need to make up
     */
    public static int coinChange(int[] coins, int amount) {
        // 0...n: [n+1]
        // 0...n-1: [n]
        int[] f = new int[amount + 1];
        int n = coins.length;

        // initialization
        f[0] = 0;
        // f[1],f[2],...,f[27]
        for (int i = 1; i <= amount; i++) {
            f[i] = Integer.MAX_VALUE;
            // f[i] = min{f[i-coins[0]]+1,...,f[i-coins[n-1]]+1}
            for (int j = 0; j < n; j++) {
                if (i >= coins[j] && f[i - coins[j]] != Integer.MAX_VALUE) {
                    f[i] = Math.min(f[i - coins[j]] + 1, f[i]);
                }
            }
        }

        if (f[amount] == Integer.MAX_VALUE) {
            f[amount] = -1;
        }

        return f[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{2, 5, 7};
        System.out.println(CoinChange.coinChange(coins, 27));
    }
}
