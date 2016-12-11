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

package org.mingle.orange.distributed.rmi.allinone;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * 
 * 
 * @author mingle
 */
public class Server {
    /**
     * 启动 RMI 注册服务并进行对象注册
     */
    public static void main(String[] argv) {
        try {
            // 启动RMI注册服务，指定端口为1099　（1099为默认端口）
            // 也可以通过命令 ＄java_home/bin/rmiregistry 1099启动
            // 这里用这种方式避免了再打开一个DOS窗口
            // 而且用命令rmiregistry启动注册服务还必须事先用RMIC生成一个stub类为它所用
            LocateRegistry.createRegistry(1099);

            // 创建远程对象的一个或多个实例，下面是hello对象
            // 可以用不同名字注册不同的实例
            Interface hello = new Impletation("Hello, world!");

            // 把hello注册到RMI注册服务器上，命名为Hello
            Naming.rebind("Hello", hello);

            // 如果要把hello实例注册到另一台启动了RMI注册服务的机器上
            // Naming.rebind("//192.168.1.105:1099/Hello",hello);

            System.out.println("Hello Server is ready.");
            System.in.read();
        } catch (Exception e) {
            System.out.println("Hello Server failed: " + e);
        }
    }
}
