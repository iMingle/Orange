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

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author mingle
 */
public class AbcOfGraph {
    private final int vertex;
    private int edge;
    private final LinkedList<Integer>[] adjacency;

    @SuppressWarnings("unchecked")
    public AbcOfGraph(int vertex) {
        if (vertex < 0) throw new IllegalArgumentException();
        this.vertex = vertex;
        this.edge = 0;
        this.adjacency = (LinkedList<Integer>[]) new LinkedList[vertex];
        for (int v = 0; v < vertex; v++) {
            adjacency[v] = new LinkedList<>();
        }
    }

    public void bfs(int s, int t) {
        if (s == t) return;
        boolean[] visited = new boolean[vertex];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[vertex];
        for (int i = 0; i < vertex; i++) {
            prev[i] = -1;
        }

        while (!queue.isEmpty()) {
            int w = queue.poll();
            for (int q : adjacency[w]) {
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    public void dfs(int s, int t) {
        if (s == t) return;
        boolean[] visited = new boolean[vertex];
        int[] prev = new int[vertex];
        for (int i = 0; i < vertex; i++) {
            prev[i] = -1;
        }

        recursiveDfs(s, t, visited, prev);

        print(prev, s, t);
    }

    private void recursiveDfs(int s, int t, boolean[] visited, int[] prev) {
        visited[s] = true;
        if (s == t) return;

        for (int q : adjacency[s]) {
            if (!visited[q]) {
                prev[q] = s;
                recursiveDfs(q, t, visited, prev);
            }
        }
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        edge++;
        adjacency[v].add(w);
        adjacency[w].add(v);
    }

    // 递归打印 s->t 的路径
    private void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= vertex)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (vertex - 1));
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertex).append(" vertices, ").append(edge).append(" edges ").append("\n");
        for (int v = 0; v < vertex; v++) {
            s.append(v).append(": ");
            for (int w : adjacency[v]) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        AbcOfGraph graph = new AbcOfGraph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        System.out.println(graph);

        graph.bfs(0, 6);
        System.out.println();
        graph.dfs(0, 6);
    }
}
