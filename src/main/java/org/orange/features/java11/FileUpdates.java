package org.orange.features.java11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author mingle
 */
public class FileUpdates {
    public static void main(String[] args) throws IOException {
        Path path = Files.writeString(Files.createTempFile("test", ".txt"), "Java 11 features");

        System.out.println(path);

        String str = Files.readString(path);
        System.out.println(str);

        Files.delete(path);
    }
}
