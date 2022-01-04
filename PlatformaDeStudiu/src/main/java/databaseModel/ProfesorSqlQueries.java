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
	
	public static ArrayList<String> inscriere_activitati_studiu(Connection con, String id_gsa) throws SQLException 
	{	
		ArrayList <String> arr = new ArrayList <String>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Inscriere_activitati_studiu(?)}"); //TODO
			cs.setString(1, DatabaseController.user);
			cs.setString(2, id_gsa);
			ResultSet rs = cs.executeQuery();
			con.commit();
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
		return arr;
	}
	
	public static ArrayList<String> vizualizare_activitati_studiu(Connection con) throws SQLException 
	{	
		ArrayList <String> arr = new ArrayList <String>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Vizualizare_activitati_studiu(?)}");
			cs.setString(1, DatabaseController.user);
			ResultSet rs = cs.executeQuery();
			con.commit();
			
			while (rs.next()) 
			{
				arr.add(rs.getString("id"));
				arr.add(rs.getString("id_grup"));
				arr.add(rs.getString("cnp_profesor"));
				arr.add(rs.getString("nume"));
				arr.add(rs.getString("descriere"));
				arr.add(rs.getString("data_programarii"));
				arr.add(rs.getString("durata"));
				arr.add(rs.getString("data_expirarii"));
				arr.add(rs.getString("numar_minim"));
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
