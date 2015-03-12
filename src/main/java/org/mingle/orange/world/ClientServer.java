package org.mingle.orange.world;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientServer {
	
	private static class Handle implements Runnable {
		private Socket socket;
		
		public Handle(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				
				PrintWriter printWriter = new PrintWriter(out);
				printWriter.println("Hello, Client!");
				printWriter.flush();
				
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(in));
				
				String serverInfo = bufferReader.readLine();
				System.out.println(serverInfo);
				printWriter.println("Good Bye, Client!");
				printWriter.flush();
				
				serverInfo = bufferReader.readLine();
				System.out.println(serverInfo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void start(Socket socket) {
		Thread client = new Thread(new Handle(socket));
		
		client.start();
	}

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8888);
			
			Socket socket = server.accept();
			
			ClientServer.start(socket);
			
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
