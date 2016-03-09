/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.util;

import java.io.File;
import java.io.IOException;

/**
 * 根据Strategy对象处理目录中的文件
 *
 * @since 1.0
 * @author Mingle
 */
public class ProcessFiles {
    public interface Strategy {
        void process(File file);
    }
    private Strategy strategy;
    private String ext;

    /**
     * @param strategy
     * @param ext        后缀
     */
    public ProcessFiles(Strategy strategy, String ext) {
        this.strategy = strategy;
        this.ext = ext;
    }
    
    public void start(String[] args) {
        try {
            if (args.length == 0)
                processDirectoryTree(new File("."));
            else
                for (String arg : args) {
                    File fileArg = new File(arg);
                    if (fileArg.isDirectory())
                        processDirectoryTree(fileArg);
                    else {
                        if (!arg.endsWith("." + ext))
                            arg += "." + ext;
                        strategy.process(new File(arg).getCanonicalFile());
                    }
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param file
     * @throws IOException 
     */
    private void processDirectoryTree(File root) throws IOException {
        for (File file : Directory.walk(root.getAbsolutePath(), ".*\\." + ext)) {
            strategy.process(file.getCanonicalFile());
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new ProcessFiles(new ProcessFiles.Strategy() {
            
            @Override
            public void process(File file) {
                System.out.println(file);
            }
        }, "java").start(args);
    }

}
