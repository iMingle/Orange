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
import java.nio.charset.Charset;

/**
 * @author mingle
 */
public class EncodeTest {
    public static void main(String[] argv) throws Exception {
        // This is the character sequence to encode
        String input = "\u00bfMa\u00f1ana?";
        // the list of charsets to encode with
        String[] charsetNames = {
                "US-ASCII", "ISO-8859-1", "UTF-8", "UTF-16BE", "UTF-16LE", "UTF-16" // , "X-ROT13"
        };
        for (int i = 0; i < charsetNames.length; i++) {
            doEncode(Charset.forName(charsetNames[i]), input);
        }
    }

    /**
     * For a given Charset and input string, encode the chars
     * and print out the resulting byte encoding in a readable form.
     * 191
     */
    private static void doEncode(Charset cs, String input) {
        ByteBuffer bb = cs.encode(input);
        System.out.println("Charset: " + cs.name());
        System.out.println(" Input: " + input);
        System.out.println("Encoded: ");
        for (int i = 0; bb.hasRemaining(); i++) {
            int b = bb.get();
            int ival = ((int) b) & 0xff;
            char c = (char) ival;
            // Keep tabular alignment pretty
            if (i < 10) System.out.print(" ");
            // Print index number
            System.out.print(" " + i + ": ");
            // Better formatted output is coming someday... if (ival < 16) System.out.print ("0");
            // Print the hex value of the byte
            System.out.print(Integer.toHexString(ival));
            // If the byte seems to be the value of a printable character, print it. No guarantee it will be.
            if (Character.isWhitespace(c) || Character.isISOControl(c)) {
                System.out.println("");
            } else {
                System.out.println(" (" + c + ")");
            }
        }
        System.out.println("");
    }

}
