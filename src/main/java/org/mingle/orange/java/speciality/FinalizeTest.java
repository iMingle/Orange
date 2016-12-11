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

package org.mingle.orange.java.speciality;

/**
 * 应避免使用finalize方法
 * 1. 作为安全网
 * 2. 为了终止非关键的本地资源
 *
 * @author mingle
 */
public class FinalizeTest {
    @SuppressWarnings("unused")
    private Tank tank;
    
    /**
     * @param tank
     */
    public FinalizeTest() {
        System.out.println("FinalizeTest");
        tank = new Tank();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#finalize()
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize function invoke");
        super.finalize();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new FinalizeTest();
        new FinalizeTest();
        System.gc();
        /*Tank tank1 = new Tank();
        Tank tank2 = new Tank(3);
        Tank tank3 = new Tank(5);
        // Proper cleanup: empty tank before going home
        tank2.empty();
        // Drop the reference, forget to cleanup:
        new Tank(6);
        System.out.println("Check tanks:");
        System.out.println("tank1: ");
        tank1.sayHowFull();
        System.out.println("tank2: ");
        tank2.sayHowFull();
        System.out.println("tank3: ");
        tank3.sayHowFull();
        System.out.println("first forced gc():");
        System.gc();
        // Force finalization on exit but using method
        // deprecated since JDK 1.1:
        System.out.println("try deprecated runFinalizersOnExit(true):");
        System.runFinalizersOnExit(true);
        System.out.println("last forced gc():");
        System.gc();
        */
    }

}

class Tank {
    int howFull = 0;

    Tank() {
        this(0);
        System.out.println("Tank");
    }

    Tank(int fullness) {
        howFull = fullness;
    }

    void sayHowFull() {
        if (howFull == 0)
            System.out.println("Tank is empty");
        else
            System.out.println("Tank filling status = " + howFull);
    }

    void empty() {
        howFull = 0;
    }

    protected void finalize() {
        if (howFull != 0)
            System.out.println("Error: Tank not empty");
        // Normally, you'll also do this:
        // super.finalize(); // Call the base-class version
    }
}
