package databaseModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import databaseController.DatabaseController;

public class PersoaneSqlQueries {
	
	public static int determina_tip_utilizator(Connection con, String cnp) throws SQLException 
	{	
		int tip = 0;
		try 
		{
			String query = "{ ? = call find_user_type (?)}";
			CallableStatement cs = con.prepareCall(query);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2,cnp);
			cs.execute();
			
			tip = cs.getInt(1);
			
			con.commit();	
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
		return tip;
	}
	
	public static String get_cnp(Connection con, String email) throws SQLException 
	{	
		String cnp = "-";
		try 
		{
			String query = "{ ? = call get_cnp (?)}";
			CallableStatement cs = con.prepareCall(query);
			cs.registerOutParameter(1, Types.CHAR);
			cs.setString(2,email);
			cs.execute();
			
			cnp = cs.getString(1);
			
			con.commit();	
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
		return cnp;
	}
	
	public static ArrayList<ArrayList<String>> cautare_materie(Connection con) throws SQLException 
	{	
		ArrayList <ArrayList<String>> arr = new ArrayList <ArrayList<String>>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Cautare_materie(?)}");
			cs.setString(1, "BD");
			ResultSet rs = cs.executeQuery();
			con.commit();
			
			while (rs.next()) 
			{
				ArrayList<String> aux = new ArrayList<String>();
				aux.add(rs.getString("id"));
				aux.add(rs.getString("nume"));
				aux.add(rs.getString("descriere"));
				aux.add(rs.getString("procent_curs"));
				aux.add(rs.getString("procent_seminar"));
				aux.add(rs.getString("procent_laborator"));
				aux.add(rs.getString("nr_max_studenti"));
				aux.add(rs.getString("recurenta"));
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
	
	public static ArrayList<String> date_personale(Connection con) throws SQLException 
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
	
	public static ArrayList<ArrayList<String>> vizualizare_calendar(Connection con, boolean type) throws SQLException 
	{	
		ArrayList <ArrayList<String>> arr = new ArrayList <ArrayList<String>>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Vizualizare_activitati(?,?)}");
			cs.setString(1, DatabaseController.user);
			cs.setBoolean(2,  type);
			ResultSet rs = cs.executeQuery();
			con.commit();
			
			while (rs.next()) 
			{
				ArrayList<String> aux = new ArrayList<String>();
				aux.add(rs.getString("nume"));
				aux.add(rs.getString("data_programarii"));
				aux.add(rs.getString("durata"));
				aux.add(rs.getString("numar_minim"));
				aux.add(rs.getString("Extra"));
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
	
	/*
	 * public static boolean insertIntoPersoane(Connection con, ArrayList<String>
	 * data) throws SQLException { String query =
	 * "insert into persoane (nume, prenume, cnp, adresa, nr_telefon, parola, email, iban) values (?, ?, ?, ?, ?, ?, ?, ?)"
	 * ; try { PreparedStatement preStmt = con.prepareStatement(query); int i = 1;
	 * for(String d : data) preStmt.setString(i++, d); preStmt.execute();
	 * con.commit(); } catch (SQLException e) { con.rollback();
	 * TreatException.printSQLException(e); } return false; }
	 */
}
