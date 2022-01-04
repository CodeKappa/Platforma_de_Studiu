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
	
	public static void inscriere_activitati_studiu(Connection con, int id_gsa) throws SQLException 
	{	
		try 
		{
			CallableStatement cs = con.prepareCall("{call Inscriere_activitati_studiu(?,?)}");
			cs.setString(1, DatabaseController.user);
			cs.setInt(2, id_gsa);
			cs.execute();
			con.commit();
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
	}
	
	public static ArrayList <ArrayList<String>> vizualizare_activitati_studiu(Connection con) throws SQLException 
	{	
		ArrayList <ArrayList<String>> arr = new ArrayList <ArrayList<String>>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Vizualizare_activitati_studiu(?)}");
			cs.setString(1, DatabaseController.user);
			ResultSet rs = cs.executeQuery();
			con.commit();
			
			while (rs.next()) 
			{
				ArrayList<String> aux = new ArrayList<>();
				aux.add(rs.getString("id"));
				aux.add(rs.getString("id_grup"));
				aux.add(rs.getString("cnp_profesor"));
				aux.add(rs.getString("nume"));
				aux.add(rs.getString("descriere"));
				aux.add(rs.getString("data_programarii"));
				aux.add(rs.getString("durata"));
				aux.add(rs.getString("data_expirarii"));
				aux.add(rs.getString("numar_minim"));
				arr.add(aux);
			}	
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
		return arr;
	}
	
	public static void descarcare_calendar(Connection con, boolean type) throws SQLException 
	{	
		try 
		{
			CallableStatement cs = con.prepareCall("{call Descarcare_activitati(?,?)}");
			cs.setString(1, DatabaseController.user);
			cs.setBoolean(2, type);
			cs.execute();
			con.commit();
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
	}
	
	public static void programare_calendar(Connection con, ArrayList<String> data) throws SQLException 
	{	
		try 
		{
			CallableStatement cs = con.prepareCall("{call Programare_calendar(?,?,?,?,?,?,?)}");
			cs.setString(1, DatabaseController.user);
			cs.setString(2, data.get(0));
			cs.setString(3, data.get(1));
			cs.setString(4, data.get(2));
			cs.setInt(5, Integer.parseInt(data.get(3)));
			cs.setString(6, data.get(4));
			cs.setInt(7, Integer.parseInt(data.get(5)));
			cs.execute();
			con.commit();
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
	}
	
	public static ArrayList <ArrayList<String>> vizualizare_studenti(Connection con) throws SQLException 
	{	
		ArrayList <ArrayList<String>> arr = new ArrayList <ArrayList<String>>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Vizualizare_studenti_materii(?)}");
			cs.setString(1, DatabaseController.user);
			ResultSet rs = cs.executeQuery();
			con.commit();
			
			while (rs.next()) 
			{
				ArrayList<String> aux = new ArrayList<>();
				aux.add(rs.getString("nume"));
				aux.add(rs.getString("prenume"));
				aux.add(rs.getString("cnp"));
				aux.add(rs.getString("an_studiu"));
				aux.add(rs.getString("nr_telefon"));
				aux.add(rs.getString("email"));
				aux.add(rs.getString("id_materie"));
				aux.add(rs.getString("categorie"));
				aux.add(rs.getString("nota"));
				arr.add(aux);
			}	
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
		return arr;
	}
	
	public static void notare_studenti(Connection con, ArrayList<String> data) throws SQLException 
	{	
		try 
		{
			CallableStatement cs = con.prepareCall("{call Notare_studenti(?,?,?,?)}");
			cs.setInt(1, Integer.parseInt(data.get(0)));
			cs.setString(2, data.get(1));
			cs.setString(3,  data.get(2));
			cs.setDouble(4, Double.parseDouble(data.get(3)));
			cs.execute();
			con.commit();
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
	}
	
	public static void descarcare_studenti(Connection con) throws SQLException 
	{	
		try 
		{
			CallableStatement cs = con.prepareCall("{call Descarcare_studenti_materii(?)}");
			cs.setString(1, DatabaseController.user);
			cs.execute();
			con.commit();
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
	}
}
