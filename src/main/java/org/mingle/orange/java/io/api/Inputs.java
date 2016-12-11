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

package org.mingle.orange.java.io.api;

import java.io.*;

/**
 * @author mingle
 */
public class Inputs {

    static class TextInput implements Input<String, IOException> {
        final File source;
        final Reader reader;

        public TextInput(File source) throws IOException {
            this.source = source;
            reader = new FileReader(source);
        }

        public <ReceiverThrowableType extends Throwable> void transferTo(Output<String, ReceiverThrowableType> output)
                throws IOException, ReceiverThrowableType {
            final TextSender sender = new TextSender(reader);
            output.receiveFrom(sender);

            try {
                reader.close();
            } catch (Exception e) {
                // ignore close exception
            }
        }

    }

    static class TextSender implements Sender<String, IOException> {
        final Reader reader;
        BufferedReader bufferReader;

        public TextSender(Reader reader) throws FileNotFoundException {
            this.reader = reader;
            this.bufferReader = new BufferedReader(reader);
        }

        public <ReceiverThrowableType extends Throwable> void sendTo(Receiver<String, ReceiverThrowableType> receiver)
                throws ReceiverThrowableType, IOException {
            String readLine;
            while ((readLine = bufferReader.readLine()) != null) {
                receiver.receive(readLine + "\n");
            }
        }
    }

    public static Input<String, IOException> text(File source) throws IOException {
        return new TextInput(source);
    }

    private Inputs() {
    }
}
