package com.http.socket;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class DatagramRunner {
    public static void main(String[] args) throws IOException {
        InetAddress adress = InetAddress.getByName("localhost");
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
//            ---->  [buffer]  <----
            byte[] bytes = "Hello from UDP client".getBytes();
//            System.out.println(new String(bytes, StandardCharsets.UTF_8));
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, adress,7777);
            datagramSocket.send(packet);


        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
