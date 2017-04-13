package com.oracle.ttc.mysqlmicroservice.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.oracle.ttc.mysqlmicroservice.model.CatalogItem;

public class CatalogDao {

	private Optional<String> username;
	private Optional<String> password;
	private Optional<String> connectString;

	public List<CatalogItem> getCatalog() throws IOException, SQLException, ClassNotFoundException {

		username = Optional.ofNullable(System.getenv("MYSQLCS_USER_NAME"));
		password = Optional.ofNullable(System.getenv("MYSQLCS_USER_PASSWORD"));
		connectString = Optional.ofNullable(System.getenv("MYSQLCS_CONNECT_STRING"));

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(connectString.orElse("jdbc:mysql://localhost:3306/?useSSL=false"),
				this.username.orElse("root"), this.password.orElse("oracle"));
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
