package org.mingle.orange.java.net;

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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
