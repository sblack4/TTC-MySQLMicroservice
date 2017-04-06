package com.oracle.ttc.mysqlmicroservice.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.oracle.ttc.mysqlmicroservice.dao.CatalogDao;
import com.oracle.ttc.mysqlmicroservice.model.CatalogItem;

public class CatalogServiceTest {

	private CatalogDao dao = Mockito.mock(CatalogDao.class);
	private CatalogService service;
	
	@Before
	public void setup(){
		service = new CatalogService(dao);
	}
	
	@Test
	public void getCatalogHappyCase()throws Exception {
		//setup
		List<CatalogItem> items = Arrays.asList(new CatalogItem());
		Mockito.when(dao.getCatalog()).thenReturn(items);
		
		//execute
		List<CatalogItem> catalog = service.getCatalog();
		
		//assert
		assertEquals(catalog.size(),items.size());
		
	}
	
	@Test(expected=RuntimeException.class)
	public void getCatalogNoDB()throws Exception {
		//setup
		Mockito.when(dao.getCatalog()).thenThrow(new RuntimeException());
		
		//execute
		service.getCatalog();
		
		//assert thrown error
	}
}
