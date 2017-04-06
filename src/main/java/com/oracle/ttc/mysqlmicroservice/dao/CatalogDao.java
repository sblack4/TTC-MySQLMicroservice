package com.oracle.ttc.mysqlmicroservice.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.oracle.ttc.mysqlmicroservice.model.CatalogItem;

public class CatalogDao {

	private String username;
	private String password;
	private String server;

	public List<CatalogItem> getCatalog() throws IOException, SQLException, ClassNotFoundException {

		java.io.InputStream is = this.getClass().getResourceAsStream("db.properties");
		java.util.Properties p = new Properties();
		p.load(is);
		this.username = p.getProperty("db.username");
		this.password = p.getProperty("db.password");
		this.server = p.getProperty("db.server");

		Class.forName("com.mysql.jdbc.Driver");
		String connection = "jdbc:mysql://" + this.server + ":3306/";
		Connection con = DriverManager.getConnection(connection,this.username, this.password);
		// here sonoo is database name, root is username and password
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from catalog.catalog_items");
		List<CatalogItem> items = new ArrayList<CatalogItem>();
		while (rs.next()) {
			items.add(new CatalogItem(rs.getString("NAME"), rs.getString("DESCRIPTION"), rs.getString("IMAGE"),
					rs.getDouble("PRICE")));
		}
		con.close();
		return items;
	}

}
