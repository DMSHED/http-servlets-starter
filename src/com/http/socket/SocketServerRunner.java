package com.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServerRunner {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7777);
//             возвращает клиент, который подключился к нашему серверу
//             блокирует и ждет пока появится клиент
             Socket socket = serverSocket.accept();
//             для отправки ответа
             var outputStream = new DataOutputStream(socket.getOutputStream());
//             для считывания запроса
             var inputStream = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in);
            ) {

            String request = inputStream.readUTF();
            while (!"stop".equals(request)) {
                System.out.println("Client request: " + request);
                String response = scanner.nextLine();
                outputStream.writeUTF(response);
                request = inputStream.readUTF();
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
