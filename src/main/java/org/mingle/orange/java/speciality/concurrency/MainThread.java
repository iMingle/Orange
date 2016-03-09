/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

/**
 * 运行main线程
 *
 * @since 1.0
 * @author Mingle
 */
public class MainThread {

    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
    }

}
