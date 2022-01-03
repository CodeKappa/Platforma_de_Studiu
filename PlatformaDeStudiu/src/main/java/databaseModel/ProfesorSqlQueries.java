package databaseModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import databaseController.DatabaseController;

public class ProfesorSqlQueries {
	public static ArrayList<String> date_personale(Connection con) throws SQLException 
	{	
		ArrayList <String> arr = new ArrayList <String>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Vizualizare_date_personale_profesor(?)}");
			cs.setString(1, DatabaseController.user);
			ResultSet rs = cs.executeQuery();
			con.commit();
			
			while (rs.next()) 
			{
				arr.add(rs.getString("cnp"));
				arr.add(rs.getString("nume"));
				arr.add(rs.getString("prenume"));
				arr.add(rs.getString("adresa"));
				arr.add(rs.getString("nr_telefon"));
				arr.add(rs.getString("email"));
				arr.add(rs.getString("iban"));
				arr.add(rs.getString("nr_contract"));
				arr.add(rs.getString("nr_ore_min"));
				arr.add(rs.getString("nr_ore_max"));
				arr.add(rs.getString("departament"));
			}	
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
		return arr;
	}
}
