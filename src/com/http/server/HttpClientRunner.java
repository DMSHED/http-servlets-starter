package com.http.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;

public class HttpClientRunner {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        var httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        var httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create("http://localhost:9000"))
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Path.of("resources", "first.json")))
                .build();

        var response = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
        var response2 = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
        var response3 = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println(response3.get().body());
    }
}
