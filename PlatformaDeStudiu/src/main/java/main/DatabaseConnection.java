package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import databaseModel.TreatException;

public class DatabaseConnection {

	private String dbName;
	private String url;
	private String user;
	private String password;
	private Connection con;

	public DatabaseConnection(String url, String user, String password, String dbName) {

		this.url = url;
		this.user = user;
		this.password = password;
		this.dbName = dbName;
				
		// register Oracle thin driver with DriverManager service
		// It is optional for JDBC4.x version
		try {Class.forName("com.mysql.cj.jdbc.Driver");}
		catch (ClassNotFoundException e) { e.printStackTrace(); }

		// establish the connection
		try { this.con = DriverManager.getConnection(this.url + this.dbName, this.user, this.password);}
		catch (SQLException e) { TreatException.printSQLException(e); return;}

		// display status message
		if (this.con == null) 
			System.out.println("JDBC connection did not establish");
		else
			System.out.println("Congratulations," + " JDBC connection is established successfully.\n");
		
		//disable auto-commit
		try {con.setAutoCommit(false);
		} catch (SQLException e) { TreatException.printSQLException(e); }
	}

	public void closeConnection() 
	{
		// close JDBC connection	
		try { this.con.close(); } 
		catch (SQLException e) { TreatException.printSQLException(e); }
		System.out.println("JDBC connection has been closed");
	}

	public Connection getCon() {
		return con;
	}
}