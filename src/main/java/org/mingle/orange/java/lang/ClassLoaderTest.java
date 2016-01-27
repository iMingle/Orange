/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.lang;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 
 * @since 1.8
 * @author Mingle
 */
public class ClassLoaderTest {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // bootstrap classloader
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url);
        }
        
        // extension classloader
        System.out.println(System.getProperty("java.ext.dirs"));
        ClassLoader extensionClassloader = ClassLoader.getSystemClassLoader().getParent();
        System.out.println(extensionClassloader);
        System.out.println(extensionClassloader.getParent());
        
        // system classloader
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(ClassLoader.getSystemClassLoader());
        
        ClassLoader loader = new ClassLoader() {
            /* (non-Javadoc)
             * @see java.lang.ClassLoader#loadClass(java.lang.String)
             */
            @Override
            public Class<?> loadClass(String name)
                    throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf('.') + 1) + ".class";
                    InputStream in = getClass().getResourceAsStream(fileName);
                    if (in == null)
                        return super.loadClass(name);
                    byte[] b = new byte[in.available()];
                    in.read(b);
                    
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        
        Object obj = loader.loadClass("org.mingle.orange.java.lang.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof org.mingle.orange.java.lang.ClassLoaderTest);    // false
        System.out.println(loader.getParent());
    }

}
