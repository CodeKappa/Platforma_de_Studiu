package databaseModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import databaseController.DatabaseController;

public class AdminSqlQueries {
	
	public static ArrayList<String> create_user(Connection con, ArrayList <String> arr) throws SQLException 
	{	
		try 
		{
			CallableStatement cs = con.prepareCall("{call create_user(?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, arr.get(0));
			cs.setString(2, arr.get(1));
			cs.setString(3, arr.get(2));
			cs.setString(4, arr.get(3));
			cs.setString(5, arr.get(4));
			cs.setString(6, arr.get(5));
			cs.setString(7, arr.get(6));
			cs.setString(8, arr.get(7));
			cs.setString(9, arr.get(8));
			cs.setString(10, arr.get(9));
			cs.setString(11, arr.get(10));
			cs.setString(12, arr.get(11));
			cs.execute();
			con.commit();	
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
		return arr;
	}
	
	public static ArrayList<String> update_user(Connection con) throws SQLException 
	{	
		ArrayList <String> arr = new ArrayList <String>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Vizualizare_date_personale(?)}");
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
			}	
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
		return arr;
	}
	
	public static ArrayList<String> read_user(Connection con) throws SQLException 
	{	
		ArrayList <String> arr = new ArrayList <String>();
		try 
		{
			int tip = PersoaneSqlQueries.determina_tip_utilizator(con, DatabaseController.user);
			CallableStatement cs = con.prepareCall("{call read_user(?)}");
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
				if(tip == 2)
				{
					arr.add(rs.getString("an_studiu"));
					arr.add(rs.getString("nr_ore"));
				}
				else if(tip == 3)
				{
					arr.add(rs.getString("nr_ore_min"));
					arr.add(rs.getString("nr_ore_max"));
					arr.add(rs.getString("departament"));
				}
				arr.add(Integer.toString(tip));
			}	
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
		return arr;
	}
	
	public static ArrayList<String> delete_user(Connection con) throws SQLException 
	{	
		ArrayList <String> arr = new ArrayList <String>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Vizualizare_date_personale(?)}");
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
