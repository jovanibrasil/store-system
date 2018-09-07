package com.store.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ConnectionFactory {

	private static final String user = "";
	private static final String password = "";
	private static final String host = "";
	private static final String database = "";
	
	public static SessionFactory getSessionFactory() {
		
		SessionFactory sessionFactory = null;
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");
		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
		// Creating Hibernate SessionFactory Instance
		sessionFactory = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactory;

	}
	
	public static Connection getConnection(){
		
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
			throw new RuntimeException("Failed to create connection to database.", e);
		}
	}
	
}
