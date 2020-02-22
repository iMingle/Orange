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

package org.orange.java.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author mingle
 */
public class CharsetTest {

    public static void main(String[] args) {
        Charset cset = Charset.forName("UTF-8");
        System.out.println(cset);
        Set<String> aliases = cset.aliases();
        for (String alias : aliases)
            System.out.println(alias);
        
        Map<String, Charset> charsets = Charset.availableCharsets();
        for (String name : charsets.keySet()) {
            System.out.println(name);
        }
        
        // encode and decode
        String str = "重构";
        ByteBuffer buffer = cset.encode(str);
        System.out.println("after encode : " + buffer.toString());
        byte[] bytes = buffer.array();
        for (byte b : bytes) {
            System.out.println(b);
        }
        
        ByteBuffer bbuf = ByteBuffer.wrap(bytes, 0, bytes.length);
        CharBuffer cbuf = cset.decode(bbuf);
        System.out.println("after decode : " + cbuf.toString());

        System.out.println((int) ((char) '重'));
    }

}
