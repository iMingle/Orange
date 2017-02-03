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

package org.mingle.orange.java.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author mingle
 */
public class ChannelTest {
    private static final String DEMOGRAPHIC = "blahblah.txt";
    // "Leverage frictionless methodologies"

    public static void main(String[] args) throws Exception {
        int reps = 10;

        if (args.length > 0)
            reps = Integer.parseInt(args[0]);

        FileOutputStream fos = new FileOutputStream(new File(ChannelTest.class.getResource("/documents/nio/" + DEMOGRAPHIC).toURI()));
        FileChannel fileChannel = fos.getChannel();
        fileChannel.position(4);

        ByteBuffer[] bs = utterBS(reps);

        // Deliver the message to the waiting market
        while (fileChannel.write(bs) > 0) {
            // Empty body
            // Loop until write( ) returns zero
        }

        System.out.println("write to " + DEMOGRAPHIC);
        fileChannel.truncate(1024);

        fos.close();
    }

    private static String[] col1 = {
            "Aggregate", "Enable", "Leverage", "Facilitate", "Synergize", "Repurpose", "Strategize",
            "Reinvent", "Harness"
    };

    private static String[] col2 = {
            "cross-platform", "best-of-breed", "frictionless", "ubiquitous", "extensible", "compelling",
            "mission-critical", "collaborative", "integrated"
    };

    private static String[] col3 = {
            "methodologies", "infomediaries", "platforms", "schemas", "mindshare", "paradigms",
            "functionalities", "web services", "infrastructures"
    };

    private static String newline = System.getProperty("line.separator");

    private static ByteBuffer[] utterBS(int howMany) throws Exception {
        List<ByteBuffer> list = new LinkedList<>();

        for (int i = 0; i < howMany; i++) {
            list.add(pickRandom(col1, " "));
            list.add(pickRandom(col2, " "));
            list.add(pickRandom(col3, newline));
        }

        ByteBuffer[] bufs = new ByteBuffer[list.size()];

        list.toArray(bufs);

        return bufs;
    }

    // The communications director
    private static Random rand = new Random();

    // Pick one, make a buffer to hold it and the suffix, load it with
    // the byte equivalent of the strings (will not work properly for
    // non-Latin characters), then flip the loaded buffer so it's ready
    // to be drained
    private static ByteBuffer pickRandom(String[] strings, String suffix) throws Exception {
        String string = strings[rand.nextInt(strings.length)];
        int total = string.length() + suffix.length();
        ByteBuffer buf = ByteBuffer.allocate(total);
        buf.put(string.getBytes("US-ASCII"));
        buf.put(suffix.getBytes("US-ASCII"));
        buf.flip();

        return buf;
    }
}
