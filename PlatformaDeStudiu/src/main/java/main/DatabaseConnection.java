package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import databaseModel.TreatException;

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
		
		//disable auto-commit
		try {con.setAutoCommit(false);
		} catch (SQLException e) {e.printStackTrace();}
	}

	public void closeConnection() 
	{
		// close JDBC connection	
		try { this.con.close(); } 
		catch (SQLException e) { e.printStackTrace();}
		System.out.println("JDBC connection has been closed");
	}

	public void setDbName(String dbName) 
	{
		this.dbName = dbName;
	}

	public Connection getCon() {
		return con;
	}
	
	public void selectDatabase(Connection con) throws SQLException {
		String query = "use " + this.dbName;
		try (Statement stmt = con.createStatement()) 
		{
			stmt.executeUpdate(query);
			System.out.println("database " + this.dbName + " in use");
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
	}
}