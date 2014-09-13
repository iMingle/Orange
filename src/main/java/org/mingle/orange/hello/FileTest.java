package org.mingle.orange.hello;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileTest {

	public static void main(String[] args) {
//		String directory = "." + File.separator + "resource";
		String directory = "." + File.separator + "resource" + File.separator + "aa";
		
		
		File dir = new File(directory);
		
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		File source = new File(dir, "Mingle.java");
		File file = new File(dir, "data.txt");
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			RandomAccessFile sourceRraf = new RandomAccessFile(source, "r");
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			
			byte[] buffer = new byte[1024];
			int sum = 0;
			
			while ((sum = sourceRraf.read(buffer)) > 0) {
				raf.write(buffer, 0, sum);
			}
			
			raf.close();
			sourceRraf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
/*
			FileFilter filter = new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.getPath().endsWith(".java");
			}
		};
		
		File[] files = dir.listFiles(filter);
		
		for (File f : files) {
			if (f.isDirectory()) {
				System.out.println("Directory : " + f);
			} else {
				System.out.println("File : " + f);
			}
		}
*/
	}
}
