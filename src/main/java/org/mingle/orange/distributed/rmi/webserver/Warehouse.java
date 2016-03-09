/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.distributed.rmi.webserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 仓库
 * 
 * @since 1.0
 * @author Mingle
 */
public interface Warehouse extends Remote {
    /**
     * 询问某个商品的价格
     * 
     * @param description
     * @return
     * @throws RemoteException
     */
    double getPrice(String description) throws RemoteException;
}
