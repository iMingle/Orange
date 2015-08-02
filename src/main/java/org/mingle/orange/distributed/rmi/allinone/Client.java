/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.distributed.rmi.allinone;

import java.rmi.Naming;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class Client {
	/**
	 * 查找远程对象并调用远程方法
	 */
	public static void main(String[] argv) {
		try {
			Interface hello = (Interface) Naming.lookup("Hello");

			// 如果要从另一台启动了RMI注册服务的机器上查找hello实例
			// HelloInterface hello =
			// (HelloInterface)Naming.lookup("//192.168.1.105:1099/Hello");

			// 调用远程方法
			System.out.println(hello.say());
		} catch (Exception e) {
			System.out.println("HelloClient exception: " + e);
		}
	}
}
