package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private String dbName;
	private String url;
	private String user;
	private String password;
	private Connection con;

	DatabaseConnection(String url, String user, String password) {

		this.url = url;
		this.user = user;
		this.password = password;
				
		// register Oracle thin driver with DriverManager service
		// It is optional for JDBC4.x version
		try {Class.forName("com.mysql.cj.jdbc.Driver");}
		catch (ClassNotFoundException e) { e.printStackTrace(); System.exit(1); }

		// establish the connection
		try { this.con = DriverManager.getConnection(this.url, this.user, this.password);}
		catch (SQLException e) { e.printStackTrace(); System.exit(2); }

		// display status message
		if (this.con == null) 
			System.out.println("JDBC connection is not established");
		else
			System.out.println("Congratulations," + " JDBC connection is established successfully.\n");
		if(this.con == null) System.exit(3);
	}

	public void closeConnection() {
		// close JDBC connection	
		try { this.con.close(); } 
		catch (SQLException e) { e.printStackTrace();}
		System.out.println("JDBC connection has been closed");
	}

	public String getDbName() {
		return this.dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public Connection getCon() {
		return con;
	}
}