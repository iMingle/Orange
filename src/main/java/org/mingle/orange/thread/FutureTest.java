/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class FutureTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter base directory (e.g. /usr/local/jdk1.6.0/src): ");
		String directory = in.nextLine();
		System.out.print("Enter keyword (e.g. volatile): ");
		String keyword = in.nextLine();
		
		MatchCounter counter = new MatchCounter(new File(directory), keyword);
		FutureTask<Integer> task = new FutureTask<Integer>(counter);
		Thread t = new Thread(task);
		t.start();
		
		try {
			System.out.println(task.get() + " matching files.");
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in.close();
	}

}

/**
 * this task counts the files in a directory and its subdirectories that contain a given keyword.
 */
class MatchCounter implements Callable<Integer> {
	private File directory;
	private String keyword;
	private int count;

	/**
	 * @param directory
	 * @param keyword
	 */
	public MatchCounter(File directory, String keyword) {
		this.directory = directory;
		this.keyword = keyword;
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Integer call() {
		count = 0;
		
		try {
			File[] files = this.directory.listFiles();
			ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
			
			// if has too many files, the system will die.
			for (File file : files) {
				if (file.isDirectory()) {
					MatchCounter counter = new MatchCounter(file, keyword);
					FutureTask<Integer> task = new FutureTask<Integer>(counter);
					results.add(task);
					Thread t = new Thread(task);
					t.start();
				} else {
					if (search(file)) count++;
				}
			}
			
			for (Future<Integer> result : results) {
				try {
					count += result.get();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

	/**
	 * searches a file for a given keyword.
	 * @param file
	 * @return
	 */
	private boolean search(File file) {
		try {
			Scanner in = new Scanner(new FileInputStream(file));
			boolean found = false;
			while (!found && in.hasNextLine()) {
				String line = in.nextLine();
				if (line.contains(keyword))
					found = true;
			}
			in.close();
			
			return found;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
}