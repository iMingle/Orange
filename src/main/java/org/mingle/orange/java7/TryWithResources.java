/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java7;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * The try-with-resources Statement
 * 
 * @since 1.8
 * @author Mingle
 */
public class TryWithResources {
	public static void writeToFileZipFileContents(String zipFileName,
			String outputFileName) throws java.io.IOException {
		Charset charset = StandardCharsets.US_ASCII;
		Path outputFilePath = Paths.get(outputFileName);

		// Open zip file and create output file with
		// try-with-resources statement
		try (ZipFile zf = new ZipFile(zipFileName);
				BufferedWriter writer = Files.newBufferedWriter(outputFilePath, charset)) {
			// Enumerate each entry
			for (Enumeration<? extends ZipEntry> entries = zf.entries(); entries.hasMoreElements();) {
				// Get the entry name and write it to the output file
				String newLine = System.getProperty("line.separator");
				String zipEntryName = ((ZipEntry) entries.nextElement()).getName() + newLine;
				writer.write(zipEntryName, 0, zipEntryName.length());
			}
		}
	}
}
