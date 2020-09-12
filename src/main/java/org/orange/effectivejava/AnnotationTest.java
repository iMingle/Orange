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

package org.orange.effectivejava;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 测试的注解
 *
 * @author mingle
 */
public class AnnotationTest {
    static class RunTests {
        public static void main(String[] args) throws ClassNotFoundException {
            int tests = 0;
            int passed = 0;

            Class<?> testClass = Class.forName(args[0]);
            for (Method m : testClass.getDeclaredMethods()) {
                if (m.isAnnotationPresent(Test.class)) {
                    tests++;
                    try {
                        m.invoke(null);
                        passed++;
                    } catch (InvocationTargetException wrappedExc) {
                        Throwable exc = wrappedExc.getCause();
                        System.out.println(m + " failed: " + exc);
                    } catch (Exception exc) {
                        System.out.println("INVALID @Test: " + m);
                    }
                }
            }
            System.out.printf("Passed: %d, Failed: %d\n", passed, tests - passed);
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) @interface Test {
}

class Sample {
    @Test public static void m1() {
    }

    public static void m2() {
    }

    @Test public static void m3() {
        throw new RuntimeException("Boom");
    }

    public static void m4() {
    }

    @Test public void m5() {
    }

    public static void m6() {
    }

    @Test public static void m7() {
        throw new RuntimeException("Crash");
    }

    public static void m8() {
    }

    public static void main(String[] args) {
        System.out.println(Sample.class.getCanonicalName());
    }
}
