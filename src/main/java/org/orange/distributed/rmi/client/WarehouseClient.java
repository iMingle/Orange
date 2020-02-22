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

package org.orange.distributed.rmi.client;

import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;

/**
 * 客户端
 * 
 * @author mingle
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
