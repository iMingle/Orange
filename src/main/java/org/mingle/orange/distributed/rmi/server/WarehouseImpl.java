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

package org.mingle.orange.distributed.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * 仓库的实现类
 * 
 * @author mingle
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
