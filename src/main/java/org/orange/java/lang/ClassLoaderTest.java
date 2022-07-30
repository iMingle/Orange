/*
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
 * limitations under the License.
 */

package org.orange.java.lang;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author mingle
 */
public class ClassLoaderTest {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // bootstrap classloader
        ClassLoader bootstrapClassloader = ClassLoader.getPlatformClassLoader().getParent();
        System.out.println("bootstrapClassloader: " + bootstrapClassloader);

        // platform classloader
        ClassLoader platformClassloader = ClassLoader.getSystemClassLoader().getParent();
        System.out.println("platformClassloader: " + platformClassloader);
        System.out.println(platformClassloader.getParent());

        // app classloader
        System.out.println(System.getProperty("java.class.path"));
        System.out.println("appClassloader: " + ClassLoader.getSystemClassLoader());

        ClassLoader loader = new ClassLoader() {
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

        Object obj = loader.loadClass("org.orange.java.lang.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoaderTest);    // false
        System.out.println(loader.getParent());
    }

}
