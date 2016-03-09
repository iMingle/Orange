/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.servicesinthread.callback;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 完成回调
 * 
 * @since 1.0
 * @author Mingle
 */
public class FileReaderApp implements FileReaderClient {
    protected FileReader reader = new AFileReader();

    @Override
    public void readCompleted(String filename, byte[] data) {

    }

    @Override
    public void readFailed(String filename, IOException ex) {

    }

    public void actionRequiringFile() {
        reader.read("AppFile", this);
    }

    public void actionNotRequiringFile() {
    }
}

interface FileReader {
    void read(String filename, FileReaderClient client);
}

class AFileReader implements FileReader {

    @Override
    public void read(String filename, FileReaderClient client) {
        new Thread(new Runnable() {
            public void run() {
                doRead(filename, client);
            }
        }).start();
    }

    protected void doRead(String fn, FileReaderClient client) {
        byte[] buffer = new byte[1024]; // just for illustration
        FileInputStream s = null;
        try {
            s = new FileInputStream(fn);
            s.read(buffer);
            if (client != null)
                client.readCompleted(fn, buffer);
        } catch (IOException ex) {
            if (client != null)
                client.readFailed(fn, ex);
        } finally {
            try {
                if (s != null)
                    s.close();
            } catch (IOException ignore) {}
        }
    }
}

interface FileReaderClient {
    void readCompleted(String filename, byte[] data);
    void readFailed(String filename, IOException ex);
}

class FileApplication implements FileReaderClient {
    private String[] filenames;
    private int currentCompletion; // index of ready file

    @Override
    public synchronized void readCompleted(String filename, byte[] data) {
        // wait until ready to process this callback
        while (!filename.equals(filenames[currentCompletion])) {
            try {
                wait();
            } catch (InterruptedException ex) {
                return;
            }
        }
        // ... process data...
        // wake up any other thread waiting on this condition:
        ++currentCompletion;
        notifyAll();
    }

    @Override
    public synchronized void readFailed(String filename, IOException ex) {
        
    }

    public synchronized void readfiles() {
        AFileReader reader = new AFileReader();
        currentCompletion = 0;
        for (int i = 0; i < filenames.length; ++i)
            reader.read(filenames[i], this);
    }
}
