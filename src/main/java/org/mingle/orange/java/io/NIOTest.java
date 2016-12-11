/*
 *
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
 * imitations under the License.
 *
 */

package org.mingle.orange.java.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.CRC32;

/**
 * 内存文件映射<br>
 * This program computes the CRC checksum of a file. <br>
 * Usage: java NIOTest filename
 * 
 * @author Mingle
 */
public class NIOTest {

    /**
     * @param args
     * @throws IOException 
     * @throws URISyntaxException 
     */
    public static void main(String[] args) throws IOException, URISyntaxException {
        String filename = new File(NIOTest.class.getResource("/documents/io/rt.jar").toURI()).getAbsolutePath();
        System.out.println(filename);
        System.out.println("Input Stream:");
        long start = System.currentTimeMillis();
        long crcValue = checksumInputStream(filename);
        long end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds");

        System.out.println("Buffered Input Stream:");
        start = System.currentTimeMillis();
        crcValue = checksumBufferedInputStream(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds");

        System.out.println("Random Access File:");
        start = System.currentTimeMillis();
        crcValue = checksumRandomAccessFile(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds");

        System.out.println("Mapped File:");
        start = System.currentTimeMillis();
        crcValue = checksumMappedFile(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds");
    }

    public static long checksumInputStream(String filename) throws IOException {
        InputStream in = new FileInputStream(filename);
        CRC32 crc = new CRC32();

        int c;
        while ((c = in.read()) != -1)
            crc.update(c);
        
        in.close();
        
        return crc.getValue();
    }

    public static long checksumBufferedInputStream(String filename)
            throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(filename));
        CRC32 crc = new CRC32();

        int c;
        while ((c = in.read()) != -1)
            crc.update(c);
        
        in.close();
        
        return crc.getValue();
    }

    public static long checksumRandomAccessFile(String filename)
            throws IOException {
        RandomAccessFile file = new RandomAccessFile(filename, "r");
        long length = file.length();
        CRC32 crc = new CRC32();

        for (long p = 0; p < length; p++) {
            file.seek(p);
            int c = file.readByte();
            crc.update(c);
        }
        file.close();
        
        return crc.getValue();
    }

    public static long checksumMappedFile(String filename) throws IOException {
        FileInputStream in = new FileInputStream(filename);
        FileChannel channel = in.getChannel();

        CRC32 crc = new CRC32();
        int length = (int) channel.size();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0,
                length);

        for (int p = 0; p < length; p++) {
            int c = buffer.get(p);
            crc.update(c);
        }
        in.close();
        
        return crc.getValue();
    }
}
