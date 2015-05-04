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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.MappedByteBuffer;
import java.nio.ShortBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.SortedMap;
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

/**
 * 获取通道
 */
class GetChannel {
	private static final int BSIZE = 1024;
	
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("Channel.txt");
		FileChannel fc = fos.getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes()));
		fc.close();
		fos.close();
		
		RandomAccessFile raf = new RandomAccessFile("Channel.txt", "rw");
		fc = raf.getChannel();
		fc.position(fc.size());
		fc.write(ByteBuffer.wrap("Some more".getBytes()));
		fc.close();
		raf.close();
		
		FileInputStream fis = new FileInputStream("Channel.txt");
		fc = fis.getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		fc.read(buff);
		buff.flip();
		while (buff.hasRemaining())
			System.out.print((char)buff.get());
		fis.close();
	}
}

/**
 * 简单文件复制
 */
class ChannelCopy {
	private static final int BSIZE = 1024;
	
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("arguments: sourcefile destfile");
			System.exit(1);
		}
		FileInputStream fis0 = new FileInputStream(args[0]);
		RandomAccessFile fis1 = new RandomAccessFile(args[1], "rw");
		
		FileChannel in = fis0.getChannel();
		FileChannel	out = fis1.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
		while (in.read(buffer) != -1) {
			buffer.flip();	// prepare for writing
			out.write(buffer);
			buffer.clear();	// prepare for reading
		}
		
		out.close();
		in.close();
		fis1.close();
		fis0.close();
	}
}

/**
 * 通道连接进行复制
 */
class TransferTo {
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("arguments: sourcefile destfile");
			System.exit(1);
		}
		FileInputStream fis0 = new FileInputStream(args[0]);
		RandomAccessFile fis1 = new RandomAccessFile(args[1], "rw");
		
		FileChannel in = fis0.getChannel();
		FileChannel	out = fis1.getChannel();
		
		in.transferTo(0, in.size(), out);
//		out.transferFrom(in, 0, in.size());
		
		out.close();
		in.close();
		fis1.close();
		fis0.close();
	}
}

class BufferToText {
	private static final int BSIZE = 1024;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("Channel.txt");
		FileChannel fc = fos.getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes()));
		fc.close();
		
		FileInputStream fis = new FileInputStream("Channel.txt");
		fc = fis.getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		fc.read(buff);
		buff.flip();
		// Doesn't work
		System.out.println(buff.asCharBuffer());	// 卯浥⁴數
		// 用系统默认的Charset解码
		buff.rewind();
		String encoding = System.getProperty("file.encoding");
		System.out.println(Charset.forName(encoding).decode(buff));	// Some text
		
		fc = new FileOutputStream("Channel.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
		fc.close();
		
		fc = new FileInputStream("Channel.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());	// Some text
		
		// 用CharBuffer
		fc = new FileOutputStream("Channel.txt").getChannel();
		buff = ByteBuffer.allocate(24);
		buff.asCharBuffer().put("Some text");
		fc.write(buff);
		fc.close();
		
		fc = new FileInputStream("Channel.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());	// Some text空格空格空格
	}
}

/**
 * Charset可以把数据编码成多种不同类型的字符集
 */
class AvailableCharsets {
	public static void main(String[] args) {
		SortedMap<String, Charset> charSets = Charset.availableCharsets();
		Iterator<String> it = charSets.keySet().iterator();
		while (it.hasNext()) {
			String csName = it.next();
			System.out.print(csName);
			Iterator<String> aliases = charSets.get(csName).aliases().iterator();
			if (aliases.hasNext()) {
				System.out.print(": ");
			}
			while (aliases.hasNext()) {
				System.out.print(aliases.next());
				if (aliases.hasNext())
					System.out.print(", ");
			}
			System.out.println();
		}
	}
}

/**
 * 获取基本类型
 */
class GetData {
	private static final int BSIZE = 1024;
	
	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);
		int i = 0;
		while (i++ < bb.limit())
			if (bb.get() != 0)
				System.out.println("nonzero");
		System.out.println("i = " + i);
		bb.rewind();
		bb.asCharBuffer().put("Howdy!");
		char c;
		while ((c = bb.getChar()) != 0)
			System.out.print(c + " ");
		System.out.println();
		
		bb.rewind();
		bb.asShortBuffer().put((short) 471142);
		System.out.println(bb.getShort());
		
		bb.rewind();
		bb.asIntBuffer().put(99471142);
		System.out.println(bb.getInt());
		
		bb.rewind();
		bb.asLongBuffer().put(99471142);
		System.out.println(bb.getLong());
		
		bb.rewind();
		bb.asFloatBuffer().put(99471142);
		System.out.println(bb.getFloat());
		
		bb.rewind();
		bb.asDoubleBuffer().put(99471142);
		System.out.println(bb.getDouble());
		bb.rewind();
	}
}

class IntBufferDemo {
	private static final int BSIZE = 1024;
	
	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);
		IntBuffer ib = bb.asIntBuffer();
		ib.put(new int[] { 11, 42, 47, 99, 143, 811, 1016 });
		System.out.println(ib.get(3));	// 99
		ib.put(3, 1811);
		ib.flip();
		while (ib.hasRemaining()) {
			int i = ib.get();
			System.out.println(i);
		}
	}
}

/**
 * 视图缓冲器
 */
class ViewBuffers {
	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.wrap(new byte[] { 0, 0, 0, 0, 0, 0, 0, 'a'});
		bb.rewind();
		System.out.print("Byte Buffer ");
		while (bb.hasRemaining())
			System.out.print(bb.position() + " -> " + bb.get() + ", ");
		System.out.println();
		
		CharBuffer cb = ((ByteBuffer)bb.rewind()).asCharBuffer();
		System.out.print("Char Buffer ");
		while (cb.hasRemaining())
			System.out.print(cb.position() + " -> " + cb.get() + ", ");
		System.out.println();
		
		FloatBuffer fb = ((ByteBuffer)bb.rewind()).asFloatBuffer();
		System.out.print("Float Buffer ");
		while (fb.hasRemaining())
			System.out.print(fb.position() + " -> " + fb.get() + ", ");
		System.out.println();
		
		IntBuffer ib = ((ByteBuffer)bb.rewind()).asIntBuffer();
		System.out.print("Int Buffer ");
		while (ib.hasRemaining())
			System.out.print(ib.position() + " -> " + ib.get() + ", ");
		System.out.println();
		
		LongBuffer lb = ((ByteBuffer)bb.rewind()).asLongBuffer();
		System.out.print("Long Buffer ");
		while (lb.hasRemaining())
			System.out.print(lb.position() + " -> " + lb.get() + ", ");
		System.out.println();
		
		ShortBuffer sb = ((ByteBuffer)bb.rewind()).asShortBuffer();
		System.out.print("Short Buffer ");
		while (sb.hasRemaining())
			System.out.print(sb.position() + " -> " + sb.get() + ", ");
		System.out.println();
		
		DoubleBuffer db = ((ByteBuffer)bb.rewind()).asDoubleBuffer();
		System.out.print("Double Buffer ");
		while (db.hasRemaining())
			System.out.print(db.position() + " -> " + db.get() + ", ");
	}
}

/**
 * 内存映射文件
 */
class LargeMappedFiles {
	static int length = 0x8FFFFFF;	// 128MB
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException, IOException {
		MappedByteBuffer out = new RandomAccessFile("large.dat", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
		for (int i = 0; i < length; i++) {
			out.put((byte)'x');
		}
		System.out.println("Finished Writing");
		for (int i = length / 2; i < length / 2 + 6; i++)
			System.out.print((char)out.getChar(i));
	}
}

/**
 * 读取写入性能比较
 */
class MappedIO {
	private static int numOfInts = 4000000;
	private static int numOfUbuffInts = 200000;

	private abstract static class Tester {
		private String name;

		public Tester(String name) {
			this.name = name;
		}

		public void runTest() {
			System.out.print(name + ": ");
			try {
				long start = System.nanoTime();
				test();
				double duration = System.nanoTime() - start;
				System.out.format("%.2f\n", duration / 1.0e9);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public abstract void test() throws IOException;
	}

	private static Tester[] tests = { new Tester("Stream Write") {
		public void test() throws IOException {
			DataOutputStream dos = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(new File(
							"temp.tmp"))));
			for (int i = 0; i < numOfInts; i++)
				dos.writeInt(i);
			dos.close();
		}
	}, new Tester("Mapped Write") {
		@SuppressWarnings("resource")
		public void test() throws IOException {
			FileChannel fc = new RandomAccessFile("temp.tmp", "rw")
					.getChannel();
			IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size())
					.asIntBuffer();
			for (int i = 0; i < numOfInts; i++)
				ib.put(i);
			fc.close();
		}
	}, new Tester("Stream Read") {
		public void test() throws IOException {
			DataInputStream dis = new DataInputStream(new BufferedInputStream(
					new FileInputStream("temp.tmp")));
			for (int i = 0; i < numOfInts; i++)
				dis.readInt();
			dis.close();
		}
	}, new Tester("Mapped Read") {
		@SuppressWarnings("resource")
		public void test() throws IOException {
			FileChannel fc = new FileInputStream(new File("temp.tmp"))
					.getChannel();
			IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size())
					.asIntBuffer();
			while (ib.hasRemaining())
				ib.get();
			fc.close();
		}
	}, new Tester("Stream Read/Write") {
		public void test() throws IOException {
			RandomAccessFile raf = new RandomAccessFile(new File("temp.tmp"),
					"rw");
			raf.writeInt(1);
			for (int i = 0; i < numOfUbuffInts; i++) {
				raf.seek(raf.length() - 4);
				raf.writeInt(raf.readInt());
			}
			raf.close();
		}
	}, new Tester("Mapped Read/Write") {
		@SuppressWarnings("resource")
		public void test() throws IOException {
			FileChannel fc = new RandomAccessFile(new File("temp.tmp"), "rw")
					.getChannel();
			IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size())
					.asIntBuffer();
			ib.put(0);
			for (int i = 1; i < numOfUbuffInts; i++)
				ib.put(ib.get(i - 1));
			fc.close();
		}
	} };

	public static void main(String[] args) {
		for (Tester test : tests)
			test.runTest();
//		Stream Write: 0.89
//		Mapped Write: 0.06
//		Stream Read: 0.86
//		Mapped Read: 0.08
//		Stream Read/Write: 6.64
//		Mapped Read/Write: 0.01

	}
}

/**
 * 文件加锁
 */
class FileLocking {
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("fileLock.txt");
		FileLock fl = fos.getChannel().tryLock();
		if (fl != null) {
			System.out.println("Locked File");
			fl.release();
			System.out.println("Released Lock");
		}
		fos.close();
	}
}

/**
 * 对映射文件的部分加锁
 */
class LockingMappedFiles {
	static final int LENGTH = 0x8FFFFFF; // 128 MB
	static FileChannel fc;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		fc = new RandomAccessFile("large.dat", "rw").getChannel();
		MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
		for (int i = 0; i < LENGTH; i++) {
			out.put((byte)'x');
		}
		new LockAndModify(out, 0, 0 + LENGTH / 3);
		new LockAndModify(out, LENGTH / 2, LENGTH / 2 + LENGTH / 4);
	}
	
	private static class LockAndModify extends Thread {
		private ByteBuffer buff;
		private int start, end;
		/**
		 * @param buff
		 * @param start
		 * @param end
		 */
		public LockAndModify(ByteBuffer buff, int start, int end) {
			this.start = start;
			this.end = end;
			buff.limit(end);
			buff.position(start);
			this.buff = buff.slice();
			start();
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			try {
				FileLock fl = fc.lock(start, end, false);
				System.out.println("Locked: " + start + " to " + end);
				while (buff.position() < buff.limit() - 1)
					buff.put((byte) (buff.get() + 1));
				fl.release();
				System.out.println("Released: " + start + " to " + end);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
