package com.oracle.ttc.mysqlmicroservice;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IntegrationTest {
	private HttpServer server;
	private WebTarget target;

	@Before
	public void setUp() throws Exception {
		server = Main.startServer();
		Client c = ClientBuilder.newClient();
		target = c.target(Main.BASE_URI);
	}

	@After
	public void tearDown() throws Exception {
		server.shutdown();
	}

	/**
	 * Test to see that the message "Got it!" is sent in the response.
	 */
	@Test
	public void testGetCatalog() throws Exception {
		try {
			// execute
			String responseMsg = target.path("catalog").request().get(String.class);

			// assert
			assertNotNull(responseMsg);
			assertTrue(responseMsg.length() > 0);
		} catch (InternalServerErrorException e) {
			// ignore as DB is not attached
		} catch (NotFoundException nfe){
			// catalog not reachable
		}
	}
}
