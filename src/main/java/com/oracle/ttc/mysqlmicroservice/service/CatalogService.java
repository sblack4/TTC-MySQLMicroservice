package com.oracle.ttc.mysqlmicroservice.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.oracle.ttc.mysqlmicroservice.dao.CatalogDao;
import com.oracle.ttc.mysqlmicroservice.model.CatalogItem;

public class CatalogService {

	private CatalogDao dao;

	public CatalogService(CatalogDao dao) {
		super();
		this.dao = dao;
	}

	public CatalogService() {
		super();
		dao = new CatalogDao();
	}
	
	public List<CatalogItem> getCatalog() throws IOException, ClassNotFoundException, SQLException{
		return dao.getCatalog();
	}
	
	
}
