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

package org.orange.java.net;

import java.net.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class ChatServer extends Frame {
    private static final long serialVersionUID = 5061685189796299593L;

    TextArea ta = new TextArea();

    public void launchFrame() {
        add(ta, BorderLayout.CENTER);
        setBounds(0, 0, 200, 300);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }

    ServerSocket server = null;
    @SuppressWarnings("rawtypes")
    Collection cClient = new ArrayList();

    public ChatServer(int port) throws Exception {
        server = new ServerSocket(port);
        launchFrame();
    }

    @SuppressWarnings("unchecked")
    public void startServer() throws Exception {
        while (true) {
            Socket s = server.accept();
            cClient.add(new ClientConn(s));
            ta.append("NEW-CLIENT " + s.getInetAddress() + ":" + s.getPort());
            ta.append("\n" + "CLIENTS-COUNT: " + cClient.size() + "\n\n");
        }
    }

    class ClientConn implements Runnable {
        Socket s = null;

        public ClientConn(Socket s) {
            this.s = s;
            (new Thread(this)).start();
        }

        public void send(String str) throws IOException {
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF(str);
        }

        public void dispose() {
            try {
                if (s != null)
                    s.close();
                cClient.remove(this);
                ta.append("A client out! \n");
                ta.append("CLIENT-COUNT: " + cClient.size() + "\n\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @SuppressWarnings("rawtypes")
        public void run() {
            try {
                DataInputStream dis = new DataInputStream(s.getInputStream());
                String str = dis.readUTF();
                while (str != null && str.length() != 0) {
                    System.out.println(str);
                    for (Iterator it = cClient.iterator(); it.hasNext(); ) {
                        ClientConn cc = (ClientConn) it.next();
                        if (this != cc) {
                            cc.send(str);
                        }
                    }
                    str = dis.readUTF();
                    // send(str);
                }
                this.dispose();
            } catch (Exception e) {
                System.out.println("client quit");
                this.dispose();
            }

        }
    }

    public static void main(String[] args) throws Exception {
        ChatServer cs = new ChatServer(8888);
        cs.startServer();
    }
}
