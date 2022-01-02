//for testing
//TODO delete it
package databaseModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class PersoaneSqlQueries {

	public static void viewPersoane(Connection con) throws SQLException 
	{
		String query = "select * from persoane order by nr_contract";
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			//System.out.println("cnp   nume                 parola  adresa          tel        email                 iban            nr_contract");
			while (rs.next()) 
			{		
				String cnp = rs.getString("cnp");
				String name = rs.getString("nume");
				String prenume = rs.getString("prenume");
				String adress = rs.getString("adresa");
				String tel = rs.getString("nr_telefon");
				String email = rs.getString("email");
				String iban = rs.getString("iban");
				int contract_nr = rs.getInt("nr_contract");
				
				System.out.format("%-5s %-20s %-20s %-15s %-10s %-21s %-15s %-2d\n", cnp, name, prenume, adress, tel, email, iban, contract_nr);
			}
			stmt.close();
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
	}
	
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
	
	public static boolean insertIntoPersoane(Connection con, ArrayList<String> data) throws SQLException 
	{			
		String query = "insert into persoane (nume, prenume, cnp, adresa, nr_telefon, parola, email, iban) values (?, ?, ?, ?, ?, ?, ?, ?)";
		try 
		{
			PreparedStatement preStmt = con.prepareStatement(query);
			int i = 1;
			for(String d : data)
				preStmt.setString(i++, d);
			preStmt.execute();
			con.commit();
		} 
		catch (SQLException e) 
		{
			con.rollback();
			TreatException.printSQLException(e);
		}
		return false;
	}
	
	public static ArrayList<ArrayList<String>> Cautare_materie(Connection con) throws SQLException 
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
}
