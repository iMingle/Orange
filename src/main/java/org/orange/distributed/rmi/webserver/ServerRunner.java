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

package org.orange.distributed.rmi.webserver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerRunner {

    /**
     * logger to log to.
     */
    private static final Logger LOG = Logger.getLogger(ServerRunner.class.getName());

    public static void executeInstance(NanoHTTPD server) {
        try {
            server.start();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
            System.exit(-1);
        }

        System.out.println("Server started, Hit Enter to stop.\n");

        try {
            System.in.read();
        } catch (Throwable ignored) {
        }

        server.stop();
        System.out.println("Server stopped.\n");
    }

    public static <T extends NanoHTTPD> void run(Class<T> serverClass) {
        try {
            executeInstance(serverClass.newInstance());
        } catch (Exception e) {
            ServerRunner.LOG.log(Level.SEVERE, "Cound nor create server", e);
        }
    }
}
