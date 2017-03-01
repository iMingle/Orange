package org.mingle.orange.java.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

    public static void main(String[] args) {
        byte[] buf = new byte[6];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);

        try {
            DatagramSocket ds = new DatagramSocket(5678);
            ds.receive(dp);
            System.out.println(new String(buf));
            ds.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
