/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.base;

/**
 * 动态连通性问题求解
 * 
 * @since 1.0
 * @author Mingle
 */
public class UFWeightedQuickUnion {
    private int[] id;    //分量数量，触点数
    private int[] sz;    //各个根节点对应的的分量的大小
    private int count;    //连通分量个数
    
    public UFWeightedQuickUnion(int N) {
        count = N;
        id = new int[N];
        
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }
    
    public int count() {
        return count;
    }
    
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public int find(int p) {
        //找出分量的名称
        while (p != id[p]) p = id[p];
        
        return p;
    }
    
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        
        if (i == j) return;
        
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }

        count--;
    }
    
    public static void main(String[] args) {
        
    }
}

