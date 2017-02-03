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

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;

/**
 * @author mingle
 */
public class TimeServer {
    private static final int DEFAULT_TIME_PORT = 3700;
    private static final long DIFF_1900 = 2208988800L;
    protected DatagramChannel channel;

    public TimeServer(int port) throws Exception {
        this.channel = DatagramChannel.open();
        this.channel.socket().bind(new InetSocketAddress(port));
        System.out.println("Listening on port " + port + " for time requests");
    }

    public void listen() throws Exception {
        // Allocate a buffer to hold a long value
        ByteBuffer longBuffer = ByteBuffer.allocate(8);
        // Assure big-endian (network) byte order
        longBuffer.order(ByteOrder.BIG_ENDIAN);
        // Zero the whole buffer to be sure
        longBuffer.putLong(0, 0);
        // Position to first byte of the low-order 32 bits
        longBuffer.position(4);
        // Slice the buffer; gives view of the low-order 32 bits
        ByteBuffer buffer = longBuffer.slice();
        while (true) {
            buffer.clear();
            SocketAddress sa = this.channel.receive(buffer);
            if (sa == null) {
                continue; // defensive programming
            }
            // Ignore content of received datagram per RFC 868
            System.out.println("Time request from " + sa);
            buffer.clear(); // sets pos/limit correctly
            // Set 64-bit value; slice buffer sees low 32 bits
            longBuffer.putLong(0, (System.currentTimeMillis() / 1000) + DIFF_1900);
            this.channel.send(buffer, sa);
        }
    }

    public static void main(String[] args) throws Exception {
        int port = DEFAULT_TIME_PORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        try {
            TimeServer server = new TimeServer(port);
            server.listen();
        } catch (SocketException e) {
            System.out.println("Can't bind to port " + port + ", try a different one");
        }
    }
}
