/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.servicesinthread.scheduling;

import org.mingle.orange.java.concurrent.state.transaction.Failure;

/**
 * 磁盘抽象
 * 
 * @since 1.8
 * @author Mingle
 */
public interface Disk {
    void read(int cylinderNumber, byte[] buffer) throws Failure;
    void write(int cylinderNumber, byte[] buffer) throws Failure;
}
