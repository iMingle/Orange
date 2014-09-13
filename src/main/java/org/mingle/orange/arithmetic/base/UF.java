package org.mingle.orange.arithmetic.base;

public class UF {
	private int[] id;	//分量数量，触点数
	private int count;	//连通分量个数
	
	public UF(int N) {
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
		return find(p) == find(q);
	}

	public int find(int p) {
		int result = 0;
		
		return result;
	}
	
	public void union(int p, int q) {
		
	}
	
	public static void main(String[] args) {
		
	}
}

