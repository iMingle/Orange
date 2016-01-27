/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java7;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class NIO {
    public static void main(String[] args) throws URISyntaxException {
        Path path = Paths.get(new URI("file:///foo/bar"));
        path = FileSystems.getDefault().getPath("logs", "access.log");

        System.out.println(path.toUri());
    }
}
