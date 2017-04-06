package com.oracle.ttc.mysqlmicroservice.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.oracle.ttc.mysqlmicroservice.model.CatalogItem;
import com.oracle.ttc.mysqlmicroservice.service.CatalogService;

public class CatalogControllerTest {

	CatalogController controller;
	CatalogService service = Mockito.mock(CatalogService.class);
	
	@Before
	public void setup(){
		controller = new CatalogController(service);
	}
	
	@Test
	public void getCatalog_CatalogExists()throws Exception{
		//setup
		List<CatalogItem> items = Arrays.asList(new CatalogItem("test","item","image",5.67));
		Mockito.when(service.getCatalog()).thenReturn(items);
		
		//execute
		Response response = controller.getCatalog();
		
		//assert
		assertEquals(response.getStatus(),Status.OK.getStatusCode());
		assertEquals(response.getEntity().toString(),"[{\"name\":\"test\",\"description\":\"item\",\"image\":\"image\",\"price\":5.67}]");
	}
	
	@Test
	public void getCatalog_CataloginvalidDBConnection()throws Exception{
		//setup
		Mockito.when(service.getCatalog()).thenThrow(new RuntimeException());
		
		//execute
		Response response = controller.getCatalog();
		
		//assert
		assertEquals(response.getStatus(),Status.INTERNAL_SERVER_ERROR.getStatusCode());
	}
}
