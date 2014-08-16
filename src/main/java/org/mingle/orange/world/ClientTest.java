package org.mingle.orange.world;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 8888);
			
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			PrintWriter printWriter = new PrintWriter(out);
			printWriter.println("Hello, Server!");
			printWriter.flush();
			
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(in));
			
			String serverInfo = bufferReader.readLine();
			System.out.println(serverInfo);
			printWriter.println("Good Bye, Server!");
			printWriter.flush();
			
			serverInfo = bufferReader.readLine();
			System.out.println(serverInfo);
			
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
