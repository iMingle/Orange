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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8888);
            System.out.println("server start");
            Socket s = server.accept();
            System.out.println("server accept client");
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String info = reader.readLine();

            System.out.println("server " + info);

            PrintWriter printWriter = new PrintWriter(out);
            printWriter.println("Hello,Client!");
            printWriter.flush();

            info = reader.readLine();
            System.out.println(info);

            printWriter.println("Good Bye, Client!");
            printWriter.flush();

            s.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
