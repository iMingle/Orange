/*
 *
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 *
 */

package org.mingle.orange.java.lang;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 
 * @author mingle
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
