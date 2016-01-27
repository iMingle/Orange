/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.distributed.rmi.client;

import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;

/**
 * 客户端
 * 
 * @since 1.8
 * @author Mingle
 */
public class WarehouseClient {

    public static void main(String[] args) throws NamingException, RemoteException {
        Context namingContext = new InitialContext();
        
        System.out.println("RMI registry bindings:");
        Enumeration<NameClassPair> e = namingContext.list("rmi://localhost/");
        while (e.hasMoreElements())
            System.out.println(e.nextElement().getName());
        String url = "rmi://localhost/central_warehouse";
        Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
        System.out.println(centralWarehouse.getPrice("Apple"));
    }

}
