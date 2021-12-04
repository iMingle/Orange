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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 加起来和为目标值的组合
 *
 * @author mingle
 */
public class CombinationSum {
    private static List<List<Integer>> result = new ArrayList<>();
    private static List<Integer> line = new ArrayList<>();
    private static boolean[] visited;

    public static void main(String[] args) {
        System.out.println(CombinationSum.combinationSum(new int[]{100, 10, 20, 70, 60, 10, 50}, 80));
    }

    public static List<List<Integer>> combinationSum(int[] num, int target) {
        visited = new boolean[num.length];
        Arrays.sort(num);
        backtrack(num, target, 0);
        return result;
    }

    private static void backtrack(int[] num, int target, int index) {
        int length = num.length;
        if (target == 0) {
            result.add(new ArrayList<>(line));
            return;
        }

        if (index >= length) return;

        for (int i = index; i < length; i++) {
            if (i > 0 && num[i] == num[i - 1] && !visited[i - 1]) continue;
            if (!visited[i] && num[i] <= target) {
                if (!line.isEmpty() && line.get(line.size() - 1) > num[i]) continue;
                line.add(num[i]);
                visited[i] = true;
                backtrack(num, target - num[i], index + 1);
                visited[i] = false;
                line.remove(index);
            }
        }
    }
}
