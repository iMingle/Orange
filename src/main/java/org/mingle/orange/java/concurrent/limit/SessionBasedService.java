/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.limit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 会话
 * 
 * @since 1.0
 * @author Mingle
 */
public class SessionBasedService {
    public void service() {
        OutputStream output = null;
        try {
            output = new FileOutputStream("...");
            doService(output);
        } catch (IOException e) {
            handleIOFailure();
        } finally {
            try {
                if (output != null)
                    output.close();
            } catch (IOException ignore) {
            } // ignore exception in close
        }
    }

    void handleIOFailure() {}

    void doService(OutputStream s) throws IOException {
        s.write(0);
        // ... possibly more handoffs ...
    }
}
