package com.oracle.ttc.mysqlmicroservice;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
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
        
        server.getServerConfiguration().addHttpHandler(new HttpHandler() {
            @Override
            public void service(Request request, Response response) throws Exception {
                response.setContentType("text/plain");
                response.getWriter().write("The time using the new java.time API in Java 8 is: " + LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
            }
        }, "/time");
        
        server.getServerConfiguration().addHttpHandler(
                new CLStaticHttpHandler(new URLClassLoader(new URL[] {
                    new File("target/repo/com/example/1.0-SNAPSHOT/catalog-microservice-1.0-SNAPSHOT.jar").toURI().toURL()}), "catalog/"), "/jarstatic");
        System.out.println("In order to test the server please try the following urls:");
        System.out.println(String.format("%s to see the default resource", BASE_URI));
        System.out.println(String.format("%sapplication.wadl to see the WADL resource", BASE_URI));
        System.out.println(String.format("%stime to see the time", BASE_URI));
        System.out.println(String.format("%scatalog to see a linked cataog items in database", BASE_URI));
        System.out.println(String.format("%sjarstatic/index.html to see the jar static resource", BASE_URI));
        System.out.println();
        System.out.println("Press enter to stop the server...");
        
        System.in.read();
        server.shutdown();
    }
}

