/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.distributed.rmi.allinone;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public interface Interface extends Remote {
    public String say() throws RemoteException;
}
