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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paul S. Hawke (paul.hawke@gmail.com) On: 3/9/13 at 12:47 AM
 */
public class TempFilesServer extends DebugServer {

    private static class ExampleManager implements TempFileManager {

        private final String tmpdir;

        private final List<TempFile> tempFiles;

        private ExampleManager() {
            this.tmpdir = System.getProperty("java.io.tmpdir");
            this.tempFiles = new ArrayList<TempFile>();
        }

        @Override
        public void clear() {
            if (!this.tempFiles.isEmpty()) {
                System.out.println("Cleaning up:");
            }
            for (TempFile file : this.tempFiles) {
                try {
                    System.out.println("   " + file.getName());
                    file.delete();
                } catch (Exception ignored) {
                }
            }
            this.tempFiles.clear();
        }

        @Override
        public TempFile createTempFile() throws Exception {
            DefaultTempFile tempFile = new DefaultTempFile(this.tmpdir);
            this.tempFiles.add(tempFile);
            System.out.println("Created tempFile: " + tempFile.getName());
            return tempFile;
        }
    }

    private static class ExampleManagerFactory implements TempFileManagerFactory {

        @Override
        public TempFileManager create() {
            return new ExampleManager();
        }
    }

    public static void main(String[] args) {
        TempFilesServer server = new TempFilesServer();
        server.setTempFileManagerFactory(new ExampleManagerFactory());
        ServerRunner.executeInstance(server);
    }
}
