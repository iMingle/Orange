/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.lang;

import java.net.URL;

/**
 * 
 * @since 1.8
 * @author Mingle
 */
public class ClassLoaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// bootstrap classloader
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (URL url : urls) {
			System.out.println(url);
		}
		
		// extension classloader
		System.out.println(System.getProperty("java.ext.dirs"));
		ClassLoader extensionClassloader = ClassLoader.getSystemClassLoader().getParent();
		System.out.println(extensionClassloader.getParent());
		
		// system classloader
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(ClassLoader.getSystemClassLoader());
	}

}
