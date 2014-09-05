package org.mingle.orange.world;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8888);
System.out.println("server start");
			Socket s = server.accept();
System.out.println("server accept client");			
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String info = reader.readLine();
			
			System.out.println("server " + info);
			
			PrintWriter printWriter = new PrintWriter(out);
			printWriter.println("Hello,Client!");
			printWriter.flush();
			
			info = reader.readLine();
			System.out.println(info);
			
			printWriter.println("Good Bye, Client!");
			printWriter.flush();
			
			s.close();
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
