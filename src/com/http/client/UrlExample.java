package com.http.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlExample {
    public static void main(String[] args) throws IOException {
//        checkGoogle();
        URL url = new URL("file:E:\\Разработка\\Intellij IDEA Workspace\\http-servlets-starter\\src\\com\\http\\socket\\DatagramRunner.java");
        URLConnection urlConnection = url.openConnection();
        System.out.println(new String(urlConnection.getInputStream().readAllBytes()));


    }

    private static void checkGoogle() throws IOException {
        URL url = new URL("https://www.google.com");

        URLConnection urlConnection = url.openConnection();

        urlConnection.setDoOutput(true);
        try (OutputStream outputStream = urlConnection.getOutputStream()) {
            
        }

        System.out.println();
    }
}
