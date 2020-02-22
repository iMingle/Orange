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

package org.orange.java.speciality;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Serializable;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.SortedMap;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.regex.Pattern;

/**
 * Java I/O测试
 *
 * @author mingle
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
            System.out.println((char) c);
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
                System.out.print((char) in.readByte());
        } catch (EOFException e) {
            System.out.println("End of stream");
        }
    }
}

class TestEOF {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(
                new FileInputStream("pom.xml")));
        while (in.available() != 0)
            System.out.print((char) in.readByte());
        in.close();
    }
}

/**
 * 基本的文件输出
 */
class BasicFileOutput {
    static String file = "BasicFileOutput.out";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new StringReader(
                BufferedInputFile.read("pom.xml")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                file)));
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
        BufferedReader in = new BufferedReader(new StringReader(
                BufferedInputFile.read("pom.xml")));
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
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream("Data.txt")));
        out.writeDouble(3.14159);
        out.writeUTF("That was pi");
        out.writeDouble(1.41413);
        out.writeUTF("Square root of 2");
        out.close();

        DataInputStream in = new DataInputStream(new BufferedInputStream(
                new FileInputStream("Data.txt")));
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
            BufferedReader results = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            String s;
            while ((s = results.readLine()) != null)
                System.out.println(s);

            BufferedReader errors = new BufferedReader(new InputStreamReader(
                    process.getErrorStream()));
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
            System.out.print((char) buff.get());
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
        FileChannel out = fis1.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        while (in.read(buffer) != -1) {
            buffer.flip(); // prepare for writing
            out.write(buffer);
            buffer.clear(); // prepare for reading
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
        FileChannel out = fis1.getChannel();

        in.transferTo(0, in.size(), out);
        // out.transferFrom(in, 0, in.size());

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
        System.out.println(buff.asCharBuffer()); // 卯浥⁴數
        // 用系统默认的Charset解码
        buff.rewind();
        String encoding = System.getProperty("file.encoding");
        System.out.println(Charset.forName(encoding).decode(buff)); // Some text

        fc = new FileOutputStream("Channel.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fc.close();

        fc = new FileInputStream("Channel.txt").getChannel();
        buff.clear();
        fc.read(buff);
        buff.flip();
        System.out.println(buff.asCharBuffer()); // Some text

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
        System.out.println(buff.asCharBuffer()); // Some text空格空格空格
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
            Iterator<String> aliases = charSets.get(csName).aliases()
                    .iterator();
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
        ib.put(new int[]{11, 42, 47, 99, 143, 811, 1016});
        System.out.println(ib.get(3)); // 99
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
        ByteBuffer bb = ByteBuffer
                .wrap(new byte[]{0, 0, 0, 0, 0, 0, 0, 'a'});
        bb.rewind();
        System.out.print("Byte Buffer ");
        while (bb.hasRemaining())
            System.out.print(bb.position() + " -> " + bb.get() + ", ");
        System.out.println();

        CharBuffer cb = ((ByteBuffer) bb.rewind()).asCharBuffer();
        System.out.print("Char Buffer ");
        while (cb.hasRemaining())
            System.out.print(cb.position() + " -> " + cb.get() + ", ");
        System.out.println();

        FloatBuffer fb = ((ByteBuffer) bb.rewind()).asFloatBuffer();
        System.out.print("Float Buffer ");
        while (fb.hasRemaining())
            System.out.print(fb.position() + " -> " + fb.get() + ", ");
        System.out.println();

        IntBuffer ib = ((ByteBuffer) bb.rewind()).asIntBuffer();
        System.out.print("Int Buffer ");
        while (ib.hasRemaining())
            System.out.print(ib.position() + " -> " + ib.get() + ", ");
        System.out.println();

        LongBuffer lb = ((ByteBuffer) bb.rewind()).asLongBuffer();
        System.out.print("Long Buffer ");
        while (lb.hasRemaining())
            System.out.print(lb.position() + " -> " + lb.get() + ", ");
        System.out.println();

        ShortBuffer sb = ((ByteBuffer) bb.rewind()).asShortBuffer();
        System.out.print("Short Buffer ");
        while (sb.hasRemaining())
            System.out.print(sb.position() + " -> " + sb.get() + ", ");
        System.out.println();

        DoubleBuffer db = ((ByteBuffer) bb.rewind()).asDoubleBuffer();
        System.out.print("Double Buffer ");
        while (db.hasRemaining())
            System.out.print(db.position() + " -> " + db.get() + ", ");
    }
}

/**
 * 内存映射文件
 */
class LargeMappedFiles {
    static int length = 0x8FFFFFF; // 128MB

    @SuppressWarnings("resource")
    public static void main(String[] args) throws FileNotFoundException,
            IOException {
        MappedByteBuffer out = new RandomAccessFile("large.dat", "rw")
                .getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
        for (int i = 0; i < length; i++) {
            out.put((byte) 'x');
        }
        System.out.println("Finished Writing");
        for (int i = length / 2; i < length / 2 + 6; i++)
            System.out.print((char) out.getChar(i));
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

    private static Tester[] tests = {new Tester("Stream Write") {
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
    }};

    public static void main(String[] args) {
        for (Tester test : tests)
            test.runTest();
        // Stream Write: 0.89
        // Mapped Write: 0.06
        // Stream Read: 0.86
        // Mapped Read: 0.08
        // Stream Read/Write: 6.64
        // Mapped Read/Write: 0.01

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
        MappedByteBuffer out = fc
                .map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            out.put((byte) 'x');
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

/**
 * 对象的序列化
 */
class Data implements Serializable {
    private static final long serialVersionUID = -468587976413264274L;

    private int n;

    /**
     * @param n
     */
    public Data(int n) {
        super();
        this.n = n;
    }

    public String toString() {
        return Integer.toString(n);
    }
}

class Worm implements Serializable {
    private static final long serialVersionUID = -3798002066339821114L;

    private Random rand = new Random(47);
    private Data[] d = {new Data(rand.nextInt(10)),
            new Data(rand.nextInt(10)), new Data(rand.nextInt(10))};
    private Worm next;
    private char c;

    public Worm() {
        System.out.println("Default constractor");
    }

    public Worm(int i, char x) {
        System.out.println("Worm constructor: " + i);
        c = x;
        if (--i > 0)
            next = new Worm(i, (char) (x + 1));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append('(');
        for (Data dat : d) {
            result.append(dat);
        }
        result.append(')');
        if (next != null)
            result.append(next);
        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException,
            IOException, ClassNotFoundException {
        Worm w = new Worm(6, 'a');
        System.out.println("w = " + w);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                "worm.out"));
        out.writeObject("Worm storage\n");
        out.writeObject(w);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                "worm.out"));
        String s = (String) in.readObject();
        Worm w2 = (Worm) in.readObject();
        in.close();
        System.out.println(s + "w2 = " + w2);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(bout);
        out2.writeObject("Worm storage\n");
        out2.writeObject(w);
        out2.flush();

        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(
                bout.toByteArray()));
        s = (String) in2.readObject();
        Worm w3 = (Worm) in2.readObject();
        System.out.println(s + "w3 = " + w3);
    }

}

/**
 * 序列化的控制,例如不将子对象序列化
 */
class Blip1 implements Externalizable {
    public Blip1() {
        System.out.println("Blip1 Constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip1.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        System.out.println("Blip1.readExternal");
    }

}

class Blip2 implements Externalizable {
    Blip2() {
        System.out.println("Blip2 Constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip2.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        System.out.println("Blip2.readExternal");
    }

}

class Blips {
    public static void main(String[] args) throws FileNotFoundException,
            IOException, ClassNotFoundException {
        System.out.println("Constructing objects");
        Blip1 b1 = new Blip1();
        Blip2 b2 = new Blip2();
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(
                "Blips.out"));
        System.out.println("Saving objects:");
        o.writeObject(b1);
        o.writeObject(b2);
        o.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                "Blips.out"));
        System.out.println("Recovering b1:");
        b1 = (Blip1) in.readObject();
        System.out.println("Recovering b2:");
        b2 = (Blip2) in.readObject(); // no valid constructor 必须有默认构造函数
        in.close();
    }
}

class Blip3 implements Externalizable {
    private int i;
    private String s; // 未初始化

    public Blip3() {
        System.out.println("Blip3 Constructor");
    }

    /**
     * @param i
     * @param s
     */
    public Blip3(int i, String s) {
        System.out.println("Blip3(int i, String s)");
        this.i = i;
        this.s = s;
    }

    @Override
    public String toString() {
        return s + i;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip3.writeExternal");
        out.writeObject(s);
        out.writeInt(i);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        System.out.println("Blip3.readExternal");
        s = (String) in.readObject();
        i = in.readInt();
    }

    public static void main(String[] args) throws FileNotFoundException,
            IOException, ClassNotFoundException {
        System.out.println("Constructing objects");
        Blip3 b3 = new Blip3(47, "A String ");
        System.out.println(b3);
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(
                "Blip3.out"));
        System.out.println("Saving objects:");
        o.writeObject(b3);
        o.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                "Blip3.out"));
        System.out.println("Recovering b3:");
        b3 = (Blip3) in.readObject();
        System.out.println(b3);
        in.close();
    }
}

/**
 * 使用持久性
 */
class HousePersist implements Serializable {
    private static final long serialVersionUID = -7740563484425871817L;
}

class AnimalPersist implements Serializable {
    private static final long serialVersionUID = 2492308569619687228L;
    private String name;
    private HousePersist preferredHouse;

    AnimalPersist(String nm, HousePersist h) {
        name = nm;
        preferredHouse = h;
    }

    public String toString() {
        return name + "[" + super.toString() + "], " + preferredHouse + "\n";
    }
}

class MyWorld {
    public static void main(String[] args) throws IOException,
            ClassNotFoundException {
        HousePersist house = new HousePersist();
        List<AnimalPersist> animals = new ArrayList<AnimalPersist>();
        animals.add(new AnimalPersist("Bosco the dog", house));
        animals.add(new AnimalPersist("Ralph the hamster", house));
        animals.add(new AnimalPersist("Molly the cat", house));
        System.out.println("animals: " + animals);
        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
        ObjectOutputStream o1 = new ObjectOutputStream(buf1);
        o1.writeObject(animals);
        o1.writeObject(animals); // Write a 2nd set
        // Write to a different stream:
        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream o2 = new ObjectOutputStream(buf2);
        o2.writeObject(animals);
        // Now get them back:
        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(
                buf1.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(
                buf2.toByteArray()));
        List<?> animals1 = (List<?>) in1.readObject(), animals2 = (List<?>) in1
                .readObject(), animals3 = (List<?>) in2.readObject();
        System.out.println("animals1: " + animals1);
        System.out.println("animals2: " + animals2);
        System.out.println("animals3: " + animals3);
    }
}

/**
 * Class对象序列化
 */
abstract class Shape implements Serializable {
    private static final long serialVersionUID = -5879348407256143737L;

    public static final int RED = 1, BLUE = 2, GREEN = 3;
    private int xPos, yPos, dimension;
    private static Random rand = new Random(47);
    private static int counter = 0;

    public abstract void setColor(int newColor);

    public abstract int getColor();

    public Shape(int xVal, int yVal, int dim) {
        xPos = xVal;
        yPos = yVal;
        dimension = dim;
    }

    public String toString() {
        return getClass() + "color[" + getColor() + "] xPos[" + xPos
                + "] yPos[" + yPos + "] dim[" + dimension + "]\n";
    }

    public static Shape randomFactory() {
        int xVal = rand.nextInt(100);
        int yVal = rand.nextInt(100);
        int dim = rand.nextInt(100);
        switch (counter++ % 3) {
            default:
            case 0:
                return new Circle(xVal, yVal, dim);
            case 1:
                return new Square(xVal, yVal, dim);
            case 2:
                return new Line(xVal, yVal, dim);
        }
    }
}

class Circle extends Shape {
    private static final long serialVersionUID = -3913546106184163486L;

    private static int color = RED;

    public Circle(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }

    public void setColor(int newColor) {
        color = newColor;
    }

    public int getColor() {
        return color;
    }
}

class Square extends Shape {
    private static final long serialVersionUID = 4383767259377843887L;

    private static int color;

    public Square(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
        color = RED;
    }

    public void setColor(int newColor) {
        color = newColor;
    }

    public int getColor() {
        return color;
    }
}

class Line extends Shape {
    private static final long serialVersionUID = -4809225470276727356L;

    private static int color = RED;

    /**
     * 必须手动序列化static变量
     *
     * @param os
     * @throws IOException
     */
    public static void serializeStaticState(ObjectOutputStream os)
            throws IOException {
        os.writeInt(color);
    }

    public static void deserializeStaticState(ObjectInputStream os)
            throws IOException {
        color = os.readInt();
    }

    public Line(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }

    public void setColor(int newColor) {
        color = newColor;
    }

    public int getColor() {
        return color;
    }
}

class StoreCADState {
    public static void main(String[] args) throws Exception {
        List<Class<? extends Shape>> shapeTypes = new ArrayList<Class<? extends Shape>>();
        // Add references to the class objects:
        shapeTypes.add(Circle.class);
        shapeTypes.add(Square.class);
        shapeTypes.add(Line.class);
        List<Shape> shapes = new ArrayList<Shape>();
        // Make some shapes:
        for (int i = 0; i < 10; i++)
            shapes.add(Shape.randomFactory());
        // Set all the static colors to GREEN:
        for (int i = 0; i < 10; i++)
            ((Shape) shapes.get(i)).setColor(Shape.GREEN);
        // Save the state vector:
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                "CADState.out"));
        out.writeObject(shapeTypes);
        Line.serializeStaticState(out);
        out.writeObject(shapes);
        // Display the shapes:
        System.out.println(shapes);
        // [class org.orange.java.speciality.Circlecolor[3] xPos[58]
        // yPos[55] dim[93]
        // , class org.orange.java.speciality.Squarecolor[3] xPos[61]
        // yPos[61] dim[29]
        // , class org.orange.java.speciality.Linecolor[3] xPos[68]
        // yPos[0] dim[22]
        // , class org.orange.java.speciality.Circlecolor[3] xPos[7]
        // yPos[88] dim[28]
        // , class org.orange.java.speciality.Squarecolor[3] xPos[51]
        // yPos[89] dim[9]
        // , class org.orange.java.speciality.Linecolor[3] xPos[78]
        // yPos[98] dim[61]
        // , class org.orange.java.speciality.Circlecolor[3] xPos[20]
        // yPos[58] dim[16]
        // , class org.orange.java.speciality.Squarecolor[3] xPos[40]
        // yPos[11] dim[22]
        // , class org.orange.java.speciality.Linecolor[3] xPos[4]
        // yPos[83] dim[6]
        // , class org.orange.java.speciality.Circlecolor[3] xPos[75]
        // yPos[10] dim[42]
        // ]

    }
}

class RecoverCADState {
    @SuppressWarnings({"unchecked", "unused"})
    public static void main(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                "CADState.out"));
        // Read in the same order they were written:
        List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>) in
                .readObject();
        Line.deserializeStaticState(in);
        List<Shape> shapes = (List<Shape>) in.readObject();
        System.out.println(shapes);
        // [class org.orange.java.speciality.Circlecolor[1] xPos[58]
        // yPos[55] dim[93]
        // , class org.orange.java.speciality.Squarecolor[0] xPos[61]
        // yPos[61] dim[29]
        // , class org.orange.java.speciality.Linecolor[3] xPos[68]
        // yPos[0] dim[22]
        // , class org.orange.java.speciality.Circlecolor[1] xPos[7]
        // yPos[88] dim[28]
        // , class org.orange.java.speciality.Squarecolor[0] xPos[51]
        // yPos[89] dim[9]
        // , class org.orange.java.speciality.Linecolor[3] xPos[78]
        // yPos[98] dim[61]
        // , class org.orange.java.speciality.Circlecolor[1] xPos[20]
        // yPos[58] dim[16]
        // , class org.orange.java.speciality.Squarecolor[0] xPos[40]
        // yPos[11] dim[22]
        // , class org.orange.java.speciality.Linecolor[3] xPos[4]
        // yPos[83] dim[6]
        // , class org.orange.java.speciality.Circlecolor[1] xPos[75]
        // yPos[10] dim[42]
        // ]

    }
}

/**
 * Preferences,主要用于读取用户的偏好和程序配置项信息
 * 在Windows平台中，用户参数项在注册表中的根节点是: HKEY_CURRENT_USER\Software\JavaSoft\Prefs
 * 系统参数项在注册表中的根节点是: HKEY_LOCAL_MACHINE\SOFTWARE\JavaSoft\Prefs
 */
class PreferencesDemo {
    public static void main(String[] args) throws BackingStoreException {
        Preferences prefs = Preferences.userNodeForPackage(PreferencesDemo.class);
        prefs.put("Location", "Oz");
        prefs.put("Footwear", "Ruby Slippers");
        prefs.putBoolean("isMan", false);
        prefs.putInt("age", 28);

        for (String key : prefs.keys()) {
            System.out.println(key + ": " + prefs.get(key, null));
        }

        System.out.println(prefs.getInt("age", 18));
    }
}
