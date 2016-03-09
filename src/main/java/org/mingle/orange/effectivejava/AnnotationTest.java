/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 测试的注解
 * 
 * @since 1.0
 * @author Mingle
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
@Target(ElementType.METHOD)
@interface Test {}

class Sample {
    @Test public static void m1() {}
    public static void m2() {}
    @Test public static void m3() {
        throw new RuntimeException("Boom");
    }
    public static void m4() {}
    @Test public void m5() {}
    public static void m6() {}
    @Test public static void m7() {
        throw new RuntimeException("Crash");
    }
    public static void m8() {}
    
    public static void main(String[] args) {
        System.out.println(Sample.class.getCanonicalName());
    }
}