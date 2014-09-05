/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.java;

import java.net.URL;

/**
 * @version 1.0 2014锟斤拷6锟斤拷28锟斤拷
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 *
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
		// extension classloader锟斤拷system classloader锟斤拷parent锟斤拷锟斤拷bootstrap classloader锟斤拷extension classloader锟斤拷parent锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷实锟绞碉拷classloader锟斤拷锟斤拷锟斤拷为null锟斤拷
		System.out.println(System.getProperty("java.ext.dirs"));
		ClassLoader extensionClassloader = ClassLoader.getSystemClassLoader().getParent();
		System.out.println(extensionClassloader.getParent());
		
		// system classloader
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(ClassLoader.getSystemClassLoader());
	}

}
