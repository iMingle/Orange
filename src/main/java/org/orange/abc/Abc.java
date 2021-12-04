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

import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author mingle
 */
public class Abc {
    private static Abc abc = new Abc();

    public static void main(String[] args) {
        /**
         * 12345的浮点数标识
         * 11000000111001  1.1000000111001*(2**13)
         */
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-1 >> 2));
        System.out.println(-1 >> 20);
        System.out.println(Integer.toBinaryString(-1 >>> 1));
        System.out.println(-1 >>> 1);
        System.out.println(Integer.MAX_VALUE);

        System.out.println(1 ^ 3);

        System.out.println(Float.floatToIntBits(1.2f));
        System.out.println(Integer.toBinaryString((-1) >> 2));
        System.out.println(Integer.toBinaryString(12345)); // 1+8+16+32+2**12+2**13

        String s2 = new String("a") +"b"; //s2 指向 堆 里面ab 的地址,并且常量池不存在 ab
        String s3 = s2.intern();// 由于常量池并没有ab 因此会把 s2 的堆地址引用 放到常量池
        System.out.println(s2 == s3);// 同时指向 ab 堆里面的地址 固为true

        System.out.println(abc.miniSpanningTree(9, 15, new int[][]{
                {4, 5, 18},
                {3, 6, 14},
                {2, 3, 12},
                {3, 8, 11},
                {3, 4, 10},
                {6, 7, 9},
                {1, 2, 8},
                {5, 6, 7},
                {1, 6, 6},
                {3, 7, 6},
                {1, 8, 5},
                {0, 5, 4},
                {0, 1, 3},
                {8, 2, 2},
                {7, 4, 1},
        }));
    }

    public int lastRemaining(int n, int m) {
        return f(n, m);
    }

    public int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = f(n - 1, m);
        System.out.println("f" + (n - 1) + ": " + x);
        return (m + x) % n;
    }

    private static class TreeNode {
        int val = 0;
        TreeNode left;
        TreeNode right;
    }

    public class Interval {
        int start;
        int end;
    }

    public int miniSpanningTree(int n, int m, int[][] cost) {
        Queue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x.weight));
        for (int[] v : cost) {
            queue.offer(new Edge(v[0], v[1], v[2]));
        }

        int[] parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }

        int result = 0;
        while (!queue.isEmpty()) {
            Edge e = queue.poll();
            int p = find(parent, e.start);
            int q = find(parent, e.end);
            if (p != q) {
                parent[p] = q;
                System.out.println("p: " + p + ", q: " + q);
                System.out.println(Arrays.toString(parent));
                result += e.weight;
            }
        }

        return result;
    }

    private int find(int[] parent, int v) {
        while (v != parent[v]) {
            v = parent[v];
        }

        return v;
    }

    @Data
    private static class Edge {
        private int start;
        private int end;
        private int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
