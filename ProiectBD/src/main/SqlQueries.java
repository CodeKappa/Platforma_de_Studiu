package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlQueries {

	private static Connection con;
	
	public static void setCon(Connection con) {
		SqlQueries.con = con;
	}

	public static void selectDatabase() throws SQLException {
		String query = "use " + Database.getDbName();
		try (Statement stmt = con.createStatement()) {
			stmt.executeUpdate(query);
			System.out.println("database test in use");
		} catch (SQLException e) {
			TreatException.printSQLException(e);
		}
	}

	public static void viewTable() throws SQLException {
		String query = "select * from persoane";
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {

				int ID = rs.getInt("id");
				String Name = rs.getString("nume");
				String Password = rs.getString("parola");
				System.out.println(ID + ", " + Name + ", " + Password);
			}
		} catch (SQLException e) {
			TreatException.printSQLException(e);
		}
	}
	
	public static boolean isValidPassword(String id, String parola) throws SQLException {
		String query = "select parola from persoane where id = " + id;
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				if(parola.equals(rs.getString("parola")))
					return true;
				return false;
			}
		} catch (SQLException e) {
			TreatException.printSQLException(e);
		}
		return false;
	}
}
