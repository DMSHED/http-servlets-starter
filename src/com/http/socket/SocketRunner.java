package com.http.socket;

import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketRunner {
    public static void main(String[] args) throws UnknownHostException {
//        http - 80
//        https - 443
//        DataOutputStream\DataInputStream - просто более удобная обертка
       var inetAddress = Inet4Address.getByName("localhost");
        try (Socket socket = new Socket(inetAddress, 7777);
             var outputStream  = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            while (scanner.hasNextLine()) {
                String request = scanner.nextLine();
                outputStream.writeUTF(request);
                System.out.println("Response from server: " + inputStream.readUTF());
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
