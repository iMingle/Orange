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

package org.orange.java.io.api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author mingle
 */
public class Outputs {
    static class TextOutput implements Output<String, IOException> {
        final File destination;
        final Writer writer;

        public TextOutput(File destination) throws IOException {
            this.destination = destination;
            writer = new FileWriter(destination);
        }

        public <SenderThrowableType extends Throwable> void receiveFrom(Sender<String, SenderThrowableType> sender)
                throws IOException, SenderThrowableType {
            final TextFileReceiver receiver = new TextFileReceiver(writer);
            sender.sendTo(receiver);
            receiver.finished();

            try {
                writer.close();
            } catch (Exception e) {
                // ignore close exception
            }
        }
    }

    static class TextFileReceiver implements
            Receiver<String, IOException> {
        final Writer writer;

        public TextFileReceiver(Writer writer) throws IOException {
            this.writer = writer;
        }

        public void receive(String item) throws IOException {
            writer.write(item);
        }

        public void finished() throws IOException {
        }
    }

    public static Output<String, IOException> text(File destination) throws IOException {
        return new TextOutput(destination);
    }

    private Outputs() {
    }
}
