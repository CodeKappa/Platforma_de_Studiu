package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private static String dbName;
	private final static String url = "jdbc:mysql://localhost:3306/";
	private final static String user = "root";
	private final static String password = "12344321";
	private static Connection con;
	
	public static Connection establishConnection() throws Exception {
		// register Oracle thin driver with DriverManager service
		// It is optional for JDBC4.x version
		Class.forName("com.mysql.cj.jdbc.Driver");

		// establish the connection
		con = DriverManager.getConnection(url, user, password);

		// display status message
		if (con == null) {
			System.out.println("JDBC connection is not established");
		} else
			System.out.println("Congratulations," + " JDBC connection is established successfully.\n");
		return con;
	}

	public static void closeConnection() throws SQLException {
		// close JDBC connection
		con.close();
		System.out.println("JDBC connection has been closed");
	}

	public static String getDbName() {
		return dbName;
	}

	public static void setDbName(String dbName) {
		Database.dbName = dbName;
	}
}