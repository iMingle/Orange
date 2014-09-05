package org.mingle.orange.world;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UDPClient {

	public static void main(String[] args) {
		byte[] buf = (new String("hello")).getBytes();
		try {
			DatagramPacket dp = new DatagramPacket(buf, buf.length, new InetSocketAddress("localhost", 5678));
			DatagramSocket ds = new DatagramSocket(9999);
			
			ds.send(dp);
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
