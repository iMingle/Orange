/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.distributed.rmi.webserver;

import java.io.IOException;
import java.util.Map;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class App extends NanoHTTPD {

	/**
	 * @param port
	 * @throws IOException
	 */
	public App(int port) throws IOException {
		super(port);
		start();
		System.out.println("Running! Point your browers to http://localhost:8080/\n");
		System.in.read();
	}

	public static void main(String[] args) {
		try {
			new App(8080);
		} catch (IOException e) {
			System.err.println("Couldn't start server:\n" + e);
		}
	}

	@Override
	public Response serve(IHTTPSession session) {
		String msg = "<html><body><h1>Hello server</h1>\n";
		Map<String, String> parms = session.getParms();
		if (parms.get("username") == null) {
			msg += "<form action='?' method='get'>\n  <p>Your name: <input type='text' name='username'></p>\n"
					+ "</form>\n";
		} else {
			msg += "<p>Hello, " + parms.get("username") + "!</p>";
		}
		return newFixedLengthResponse(msg + "</body></html>\n");
	}
}
