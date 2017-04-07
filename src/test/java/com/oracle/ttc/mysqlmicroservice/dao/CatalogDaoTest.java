package com.oracle.ttc.mysqlmicroservice.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import com.oracle.ttc.mysqlmicroservice.model.CatalogItem;

public class CatalogDaoTest {

	private CatalogDao dao = new CatalogDao();

	@Test
	public void getCatalog_withDB() throws Exception {
		// setup
		int expectedSize = 8;

		try {
			// execute
			List<CatalogItem> catalog = dao.getCatalog();

			// assert
			assertEquals(catalog.size(), expectedSize);
		} catch (CommunicationsException ce) {
			// Ignore error as this just means the DB is not connected
		}

	}
}
