//for testing
//TODO delete it
package databaseModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
				String password = rs.getString("parola");
				String adress = rs.getString("adresa");
				String tel = rs.getString("nr_telefon");
				String email = rs.getString("email");
				String iban = rs.getString("iban");
				int contract_nr = rs.getInt("nr_contract");
				
				System.out.format("%-5s %-20s %-20s %-7s %-15s %-10s %-21s %-15s %-2d\n", cnp, name, prenume, password, adress, tel, email, iban, contract_nr);
			}
			stmt.close();
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
	}
	
	public static boolean isValidPassword(Connection con, int nr_contract, String parola) throws SQLException 
	{
		
		String query = "select parola from persoane where nr_contract = ?";
		try 
		{
			PreparedStatement preStmt = con.prepareStatement(query);
		    preStmt.setInt(1, nr_contract);
			ResultSet rs = preStmt.executeQuery();
			while (rs.next()) 
			{
				if(parola.equals(rs.getString("parola")))
				{
					con.rollback();
					preStmt.close();
					return true;
				}
			}	
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
		}
		con.rollback();
		return false;
	}
	
	public static boolean insertIntoPersoane(Connection con, ArrayList<String> data) throws SQLException 
	{			
		String query = "insert into persoane (nume, prenume, cnp, adresa, nr_telefon, parola, email, iban, is_admin) values (?, ?, ?, ?, ?, ?, ?, ?, 0)";
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
