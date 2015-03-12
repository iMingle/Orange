package org.mingle.orange.test;

import java.net.*;
import java.io.*;

public class TCPServer {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(6666);
		while(true) {
			Socket s = ss.accept();
System.out.println("a client connect!");
			DataInputStream dis = new DataInputStream(s.getInputStream());
			System.out.println(dis.readUTF());
			dis.close();
			s.close();
		}
		
	}
}