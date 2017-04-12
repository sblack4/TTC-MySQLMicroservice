package com.oracle.ttc.mysqlmicroservice;

import java.io.IOException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Optional;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
	public static String BASE_URI;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() throws UnknownHostException {
    	// Base URI the Grizzly HTTP server will listen on
    	final Optional<String> port = Optional.ofNullable(System.getenv("PORT"));
    	final Optional<String> hostName = Optional.ofNullable(System.getenv("HOSTNAME"));
		BASE_URI = "http://" + hostName.orElse("localhost") + ":" + port.orElse("8085") + "/";

        // create a resource config that scans for JAX-RS resources and providers
        // in com.example package
        final ResourceConfig rc = new ResourceConfig().packages("com.oracle.ttc.mysqlmicroservice.controller");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        
        System.out.println("In order to test the server please try the following urls:");
        System.out.println(String.format("%scatalog/v1 to see a linked cataog items in database", BASE_URI));
        System.out.println();
        System.out.println("Press enter to stop the server...");
        
        System.in.read();
        server.shutdown();
    }
}

