package org.mingle.orange.world;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

	public static void main(String[] args) {
		byte[] buf = new byte[1024];
		
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		
		try {
			DatagramSocket ds = new DatagramSocket(5678);
			
			ds.receive(dp);
			
			System.out.println(buf.toString());
			
			ds.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
