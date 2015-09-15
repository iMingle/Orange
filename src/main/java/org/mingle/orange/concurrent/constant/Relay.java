/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.constant;

/**
 * 共享
 * 
 * @since 1.8
 * @author Mingle
 */
public class Relay {
	protected final Server server;

	public Relay(Server server) {
		this.server = server;
	}
	
	public void doIt() {
		server.doIt();
	}
}
