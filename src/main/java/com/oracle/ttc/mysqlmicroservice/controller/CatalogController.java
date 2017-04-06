package com.oracle.ttc.mysqlmicroservice.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.oracle.ttc.mysqlmicroservice.model.CatalogItem;
import com.oracle.ttc.mysqlmicroservice.service.CatalogService;

public class CatalogController {

	private CatalogService service;

	public CatalogController(CatalogService service) {
		super();
		this.service = service;
	}

	public CatalogController() {
		super();
		this.service = new CatalogService();
	}
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCatalog() {
		try{
    	List<CatalogItem> catalog = service.getCatalog();
    	ObjectWriter ow = new ObjectMapper().writer();
    	String json = ow.writeValueAsString(catalog);
    	return Response.ok()
    			.entity(json)
    			.header("Access-Control-Allow-Origin", "*")
    			.header("Access-Control-Allow-Methods", "GET")
    			.build();
		} catch(Exception e){
			return Response.status(Status.INTERNAL_SERVER_ERROR)
	    			.header("Access-Control-Allow-Origin", "*")
	    			.header("Access-Control-Allow-Methods", "GET")
	    			.build();
		}
    	
    	
    }
	
}
