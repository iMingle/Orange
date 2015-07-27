/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.distributed.rmi.server;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * 服务端
 * 
 * @since 1.8
 * @author Mingle
 */
public class WarehouseServer {

	public static void main(String[] args) throws NamingException, RemoteException {
		System.out.println("Constructing server implementing...");
		WarehouseImpl centralWarehouse = new WarehouseImpl();
		
		System.out.println("Binding server implementating to registry...");
		Context namingContext = new InitialContext();
		namingContext.bind("rmi:central_warehouse", centralWarehouse);
		
		System.out.println("Waiting for invocations from clients...");
	}

}
