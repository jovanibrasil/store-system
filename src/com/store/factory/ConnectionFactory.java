package com.store.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static final String user = "";
	private static final String password = "";
	private static final String host = "";
	private static final String database = "";
	
	public static Connection connect() throws SQLException {
		
		try {
			// Load driver
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			String url = String.format("jdbc:mysql://%s/%s", host, database);
			// Set connection properties.
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("useSSL", "true");
            properties.setProperty("verifyServerCertificate", "false");
            properties.setProperty("requireSSL", "false");

            // Get connection
            Connection connection = DriverManager.getConnection(url, properties);
			
			return connection;
		} catch (SQLException e) {
			throw new SQLException("Failed to create connection to database.", e);
		}
	}
	
}
