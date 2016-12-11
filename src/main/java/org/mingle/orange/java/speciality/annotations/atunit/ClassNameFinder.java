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

package org.mingle.orange.java.speciality.annotations.atunit;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.mingle.orange.java.util.BinaryFile;
import org.mingle.orange.java.util.Directory;

/**
 * 从class文件中读取文件名称
 *
 * @author mingle
 */
public class ClassNameFinder {
    @SuppressWarnings("unused")
    public static String thisClass(byte[] classBytes) {
        Map<Integer, Integer> offsetTable = new HashMap<>();
        Map<Integer, String> classNameTable = new HashMap<>();
        
        try {
            DataInputStream data = new DataInputStream(new ByteArrayInputStream(classBytes));
            int magic = data.readInt();    // 0xcafebabe
            int minorVersion = data.readShort();
            int majorVersion = data.readShort();
            int constant_pool_count = data.readShort();
            int[] constant_pool = new int[constant_pool_count];
            for (int i = 1; i < constant_pool.length; i++) {
                int tag = data.read();
                int tableSize;
                switch (tag) {
                case 1:    // UTF
                    int length = data.readShort();
                    char[] bytes = new char[length];
                    for (int j = 0; j < bytes.length; j++) {
                        bytes[j] = (char) data.read();
                    }
                    String className = new String(bytes);
                    classNameTable.put(i, className);
                    break;
                case 5:    // LONG
                case 6:    // DOUBLE
                    data.readLong();    // discard 8 bytes
                    i++;
                    break;
                case 7:    // CLASS
                    int offset = data.readShort();
                    offsetTable.put(i, offset);
                    break;
                case 8:    // STRING
                    data.readShort();    // discard 2 bytes
                    break;
                case 3:    // INTEGER
                case 4:    // FLOAT
                case 9:    // FIELD_REF
                case 10:    // METHOD_REF
                case 11:    // INTERFACE_METHOD_REF
                case 12:    // NAME_AND_TYPE
                    data.readInt();    // discard 4 bytes
                    break;
                default:
                    throw new RuntimeException("Bad tag " + tag);
                }
            }
            
            short access_flags = data.readShort();
            int this_class = data.readShort();
            int super_class = data.readShort();
            return classNameTable.get(offsetTable.get(this_class)).replace('/', '.');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            for (String arg : args) {
                System.out.println(thisClass(BinaryFile.read(new File(arg))));
            }
        } else {
            for (File klass : Directory.walk(".", ".*\\.class")) {
                System.out.println(thisClass(BinaryFile.read(klass)));
            }
        }
    }

}
