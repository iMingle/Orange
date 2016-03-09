/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.distributed.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * 仓库的实现类
 * 
 * @since 1.0
 * @author Mingle
 */
public class WarehouseImpl extends UnicastRemoteObject implements Warehouse {
    private static final long serialVersionUID = -2465759340903040538L;
    
    private Map<String, Double> prices;

    /**
     * @throws RemoteException
     */
    protected WarehouseImpl() throws RemoteException {
        super(8888);
        prices = new HashMap<>();
        prices.put("Orange", 2.95);
        prices.put("Apple", 4.99);
    }

    /* (non-Javadoc)
     * @see org.mingle.orange.distributed.Warehouse#getPrice(java.lang.String)
     */
    @Override
    public double getPrice(String description) throws RemoteException {
        Double price = prices.get(description);
        return price == null ? 0 : price;
    }

}
