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
 * 全排列
 *
 * @author mingle
 */
public class Permutations {
    private static List<ArrayList<Integer>> result = new ArrayList<>();
    private static List<Integer> line = new ArrayList<>();
    private static boolean[] visited;

    public static void main(String[] args) {
        System.out.println(Permutations.permuteUnique(new int[]{1, 1, 2}));
    }

    public static List<ArrayList<Integer>> permuteUnique(int[] num) {
        visited = new boolean[num.length];
        Arrays.sort(num);
        backtrack(num, 0);
        return result;
    }

    private static void backtrack(int[] num, int index) {
        int length = num.length;
        if (index == length) {
            result.add(new ArrayList<>(line));
            return;
        }

        for (int i = 0; i < length; i++) {
            if (visited[i] || (i > 0 && num[i] == num[i - 1] && !visited[i - 1])) {
                continue;
            }
            line.add(num[i]);
            visited[i] = true;
            backtrack(num, index + 1);
            visited[i] = false;
            line.remove(index);
        }
    }
}
