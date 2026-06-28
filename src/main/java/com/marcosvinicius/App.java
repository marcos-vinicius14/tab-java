package com.marcosvinicius;

import com.marcosvinicius.controllers.Index;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * mvn exec:java -Dexec.mainClass="com.marcosvinicius.App"
 *
 * # Ou gerar fat jar e executar
 * mvn package
 * java -jar target/minha-api-1.0.0.jar
 *
 */
public class App 
{
     static void main( String[] args ) throws Exception
    {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/users", new Index());
        server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        server.start();
        System.out.println( "Server running on port 8080" );
    }
}
