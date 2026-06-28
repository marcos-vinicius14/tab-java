package com.marcosvinicius.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Index implements HttpHandler {
    public Logger logger = LoggerFactory.getLogger(Index.class);
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        switch (method) {
            case "GET" -> handleGet(exchange);
            case "POST" -> handlePost(exchange);
            default -> sendResponse(exchange, 405, "{\"error\":\"Method Not Allowed\"}");
        }
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        logger.debug("GET request received");
        String response =  "\"{\\\"users\\\":[{\\\"id\\\":1,\\\"name\\\":\\\"Marcos\\\"}]}\"";
        sendResponse(exchange, 200, response);
    }

    private void handlePost(HttpExchange exchange) throws IOException {}

    private void sendResponse(HttpExchange exchange, int status, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(status, bytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }


}
