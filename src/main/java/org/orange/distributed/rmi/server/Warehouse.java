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

package org.orange.distributed.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 仓库
 * 
 * @author mingle
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
