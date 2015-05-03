/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Java I/O测试
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class IOTest {

	public static void main(String[] args) throws IOException {
		File file = new File(".");
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getCanonicalPath());
		System.out.println(file.getPath());
	}

}

/**
 * 目录列表器
 */
class DirList {
	
	public static void main(String[] args) {
		File path = new File(".");
		String[] list;
		if (args.length == 0)
			list = path.list();
		else
			list = path.list(new FilenameFilter() {
				private Pattern pattern = Pattern.compile(args[0]);
				
				@Override
				public boolean accept(File dir, String name) {
					return pattern.matcher(name).matches();
				}
			});
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		for (String dirItem : list) {
			System.out.println(dirItem);
		}
	}
}

/**
 * 目录的检查及创建
 */
class MakeDirectories {
	private static void usage() {
		System.err.println("Usage:MakeDirectories path1 ...\n"
				+ "Creates each path\n"
				+ "Usage:MakeDirectories -d path1 ...\n"
				+ "Deletes each path\n"
				+ "Usage:MakeDirectories -r path1 path2\n"
				+ "Renames from path1 to path2");
		System.exit(1);
	}

	private static void fileData(File f) {
		System.out.println("Absolute path: " + f.getAbsolutePath()
				+ "\n Can read: " + f.canRead() + "\n Can write: "
				+ f.canWrite() + "\n getName: " + f.getName()
				+ "\n getParent: " + f.getParent() + "\n getPath: "
				+ f.getPath() + "\n length: " + f.length()
				+ "\n lastModified: " + f.lastModified());
		if (f.isFile())
			System.out.println("It's a file");
		else if (f.isDirectory())
			System.out.println("It's a directory");
	}

	public static void main(String[] args) {
		if (args.length < 1)
			usage();
		if (args[0].equals("-r")) {
			if (args.length != 3)
				usage();
			File old = new File(args[1]), rname = new File(args[2]);
			old.renameTo(rname);
			fileData(old);
			fileData(rname);
			return; // Exit main
		}
		int count = 0;
		boolean del = false;
		if (args[0].equals("-d")) {
			count++;
			del = true;
		}
		count--;
		while (++count < args.length) {
			File f = new File(args[count]);
			if (f.exists()) {
				System.out.println(f + " exists");
				if (del) {
					System.out.println("deleting..." + f);
					f.delete();
				}
			} else { // Doesn't exist
				if (!del) {
					f.mkdirs();
					System.out.println("created " + f);
				}
			}
			fileData(f);
		}
	}
}

/**
 * 缓冲输入文件
 */
class BufferedInputFile {
	public static String read(String filename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String s;
		StringBuilder sb = new StringBuilder();
		while ((s = in.readLine()) != null) {
			sb.append(s + "\n");
		}
		in.close();
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		System.out.print(read("pom.xml"));
	}
}

/**
 * 从内存读入
 */
class MemoryInput {
	public static void main(String[] args) throws IOException {
		StringReader in = new StringReader(BufferedInputFile.read("pom.xml"));
		int c;
		while ((c = in.read()) != -1)
			System.out.println((char)c);
	}
}

/**
 * 格式化的内存输入
 */
class FormattedMemoryInput {
	public static void main(String[] args) throws IOException {
		try {
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(
					BufferedInputFile.read("pom.xml").getBytes()));
			while (true)
				System.out.print((char)in.readByte());
		}
		catch (EOFException e) {
			System.out.println("End of stream");
		}
	}
}

class TestEOF {
	public static void main(String[] args) throws IOException {
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("pom.xml")));
		while (in.available() != 0)
			System.out.print((char)in.readByte());
		in.close();
	}
}

/**
 * 基本的文件输出
 */
class BasicFileOutput {
	static String file = "BasicFileOutput.out";
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read("pom.xml")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		int lineCount = 1;
		String s;
		while ((s = in.readLine()) != null)
			out.println(lineCount++ + ":" + s);
		out.close();
		System.out.println(BufferedInputFile.read(file));
	}
}

/**
 * 文本文件输出的快捷方式
 */
class FileOutputShortcut {
	static String file = "FileOutputShortcut.out";
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read("pom.xml")));
		PrintWriter out = new PrintWriter(file);
		int lineCount = 1;
		String s;
		while ((s = in.readLine()) != null)
			out.println(lineCount++ + ":" + s);
		out.close();
		System.out.println(BufferedInputFile.read(file));
	}
}

/**
 * 存储和恢复数据
 */
class StoringAndRecoverData {
	public static void main(String[] args) throws IOException {
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Data.txt")));
		out.writeDouble(3.14159);
		out.writeUTF("That was pi");
		out.writeDouble(1.41413);
		out.writeUTF("Square root of 2");
		out.close();
		
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("Data.txt")));
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
		in.close();
	}
}

/**
 * 读写随机访问文件
 */
class UsingRandomAccessFile {
	static String file = "UsingRandomAccessFile.out";
	
	static void display() throws IOException {
		RandomAccessFile rf = new RandomAccessFile(file, "r");
		for (int i = 0; i < 7; i++) {
			System.out.println("Value " + i + ": " + rf.readDouble());
		}
		System.out.println(rf.readUTF());
		rf.close();
	}
	
	public static void main(String[] args) throws IOException {
		RandomAccessFile rf = new RandomAccessFile(file, "rw");
		for (int i = 0; i < 7; i++) {
			rf.writeDouble(i * 1.414);
		}
		rf.writeUTF("The end of the file");
		rf.close();
		display();
		rf = new RandomAccessFile(file, "rw");
		rf.seek(5 * 8);
		rf.writeDouble(47.0001);
		rf.close();
		display();
	}
}

/**
 * 进程控制
 */
class OSExecute {
	public static void command(String command) {
		boolean err = false;
		try {
			Process process = new ProcessBuilder(command.split(" ")).start();
			BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String s;
			while ((s = results.readLine()) != null)
				System.out.println(s);
			
			BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((s = errors.readLine()) != null)
				System.err.println(s);
			err = true;
		} catch (Exception e) {
			if (!command.startsWith("CMD /C"))
				command("CMD /C" + command);
			else
				throw new RuntimeException(e);
		}
		
		if (err)
			throw new OSExecuteException("Errors executing " + command);
	}
	
	public static void main(String[] args) {
		command("mvn clean package");
	}
}

class OSExecuteException extends RuntimeException {

	private static final long serialVersionUID = -5490675660424170302L;
	
	public OSExecuteException(String why) {
		super(why);
	}
}
