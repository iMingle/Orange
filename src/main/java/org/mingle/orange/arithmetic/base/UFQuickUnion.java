package org.mingle.orange.arithmetic.base;

public class UFQuickUnion {
	private int[] id;	//����������������
	private int count;	//��ͨ��������
	
	public UFQuickUnion(int N) {
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
		//�ҳ�����������
		while (p != id[p]) p = id[p];
		
		return p;
	}
	
	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		
		if (pRoot == qRoot) return;
		
		id[pRoot] = qRoot;
		count--;
	}
	
	public static void main(String[] args) {
		
	}
}

