package org.mingle.orange.java.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class UDPServer {

    public static void main(String[] args) {
        byte[] buf = new byte[1024];
        
        DatagramPacket dp = new DatagramPacket(buf, buf.length);
        
        try {
            DatagramSocket ds = new DatagramSocket(5678);
            
            ds.receive(dp);
            
            System.out.println(Arrays.toString(buf));
            
            ds.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}