/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.createthread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 处理web请求服务
 * 
 * @since 1.8
 * @author Mingle
 */
public class WebService implements Runnable {
	static final int PORT = 8080; // just for demo
	Handler handler = new Handler();

	public void run() {
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(PORT);
			for (;;) {
				final Socket connection = socket.accept();
				new Thread(new Runnable() {
					public void run() {
						handler.process(connection);
					}
				}).start();
			}
		} catch (Exception e) {
		} finally {
			if (socket != null)
				try {
					socket.close();
				} catch (IOException ignore) {}
		}
	}

	public static void main(String[] args) {
		new Thread(new WebService()).start();
	}

}

class Handler {

	void process(Socket s) {
		DataInputStream in = null;
		DataOutputStream out = null;
		try {
			in = new DataInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
			int request = in.readInt();
			int result = -request; // return negation to client
			out.writeInt(result);
		} catch (IOException ex) {
		} finally { // clean up
			try {
				if (in != null)
					in.close();
			} catch (IOException ignore) {}
			try {
				if (out != null)
					out.close();
			} catch (IOException ignore) {}
			try {
				s.close();
			} catch (IOException ignore) {}
		}
	}
}