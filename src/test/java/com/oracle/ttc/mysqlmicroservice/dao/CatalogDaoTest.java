package com.oracle.ttc.mysqlmicroservice.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.oracle.ttc.mysqlmicroservice.model.CatalogItem;

public class CatalogDaoTest {

	private CatalogDao dao = new CatalogDao();
	
	@Test
	public void getCatalog() throws Exception{
		//setup
		int expectedSize = 8;
		
		//execute
		List<CatalogItem> catalog = dao.getCatalog();
		
		//assert
		assertEquals(catalog.size(),expectedSize);
	}
}
