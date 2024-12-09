package com.http.server;

import javax.sound.midi.Soundbank;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private final int port;
    private final ExecutorService pool;
    private boolean stopped;

    public HttpServer(int port, int poolSize) {
        this.port = port;
        this.pool = Executors.newFixedThreadPool(poolSize);
    }

    public void run(){
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (!stopped) {
                Socket socket = serverSocket.accept();
                System.out.println("Socket accepted");
                pool.submit(() -> processSocket(socket));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void processSocket(Socket socket) {

        try (socket;
             var inputStream = new DataInputStream(socket.getInputStream());
             var outputStream = new DataOutputStream(socket.getOutputStream())) {

//            step 1 handle request
            System.out.println("Requets: " + new String(inputStream.readNBytes(400)));
//            step 2 handle response
            var body = Files.readAllBytes(Path.of("resources", "example.html"));
            var headers = """
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: %s
                    """.formatted(body.length).getBytes();

            outputStream.write(headers);
//            пробел нужен чтобы отделить header от body
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);

            Thread.sleep(10000);
        } catch (IOException e) {
//          TODO: log error message
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}
