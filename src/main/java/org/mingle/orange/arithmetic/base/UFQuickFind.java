package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.introcs.StdIn;

public class UFQuickFind {
	private int[] id;	//分量数量，触点数
	private int count;	//连通分量个数
	
	public UFQuickFind(int N) {
		count = N;
		id = new int[N];
		
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}
	
	public int count() {
		return count;
	}
	
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}

	public int find(int p) {
		return id[p];
	}
	
	public void union(int p, int q) {
		int pID = find(p);
		int qID = find(q);
		
		if (pID == qID) return;
		
		for (int i = 0; i < id.length; i++) {
			if (pID == id[i]) {
				id[i] = qID;
				count--;
			}
		}
	}
	
	public static void main(String[] args) {
		int N = StdIn.readInt();
		
		UFQuickFind quickFind = new UFQuickFind(N);
		
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			
			if (quickFind.connected(p, q)) continue;
			quickFind.union(p, q);
			System.out.println(p + " " + q);
		}
	}
}

