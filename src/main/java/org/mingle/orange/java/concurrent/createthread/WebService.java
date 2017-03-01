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

package org.mingle.orange.java.concurrent.createthread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 处理web请求服务
 *
 * @author mingle
 */
public class WebService implements Runnable {
    static final int PORT = 8080;
    Handler handler = new Handler();

    public void run() {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(PORT);
            for (; ; ) {
                final Socket connection = socket.accept();
                new Thread(() -> {
                    handler.process(connection);
                }).start();
            }
        } catch (Exception e) {
        } finally {
            if (socket != null)
                try {
                    socket.close();
                } catch (IOException ignore) {
                }
        }
    }

    public static void main(String[] args) {
        new Thread(new WebService()).start();
    }

}

class Handler {
    void process(Socket s) {
        DataInputStream in = null;
        DataOutputStream out = null;
        try {
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            int request = in.readInt();
            int result = -request; // return negation to client
            out.writeInt(result);
        } catch (IOException ex) {
        } finally { // clean up
            try {
                if (in != null)
                    in.close();
            } catch (IOException ignore) {
            }
            try {
                if (out != null)
                    out.close();
            } catch (IOException ignore) {
            }
            try {
                s.close();
            } catch (IOException ignore) {
            }
        }
    }
}
