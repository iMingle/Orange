package org.mingle.orange.arithmetic.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;

public class Test {

	@SuppressWarnings("unused")
	public static int[] readInts(String name) {
		In in = new In(name);
		String input = StdIn.readAll();
		
		System.out.println(input);
		
		String[] words = input.split("\\s+");
		int[] ints = new int[words.length];
		for (int i = 0; i < words.length; i++)
			ints[i] = Integer.parseInt(words[i]);
		return ints;
	}

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws IOException {
		String s = "C:\\Users\\mingle\\Desktop\\test.txt";
		int[] ints = new int[20];
		String[] lists = new String[100];

//		System.out.println(Test.readInts(s));
		
		File file = new File(s);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String t = br.readLine();
		lists = t.split(" ");
		
		for (int i = 0; i < lists.length; i++) {
			System.out.println(lists[i]);
		}
		System.out.println(t);
		
	}
}
