/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.java;

import java.net.URL;

/**
 * @version 1.0 2014��6��28��
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
		// extension classloader��system classloader��parent����bootstrap classloader��extension classloader��parent����������һ��ʵ�ʵ�classloader������Ϊnull��
		System.out.println(System.getProperty("java.ext.dirs"));
		ClassLoader extensionClassloader = ClassLoader.getSystemClassLoader().getParent();
		System.out.println(extensionClassloader.getParent());
		
		// system classloader
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(ClassLoader.getSystemClassLoader());
	}

}
