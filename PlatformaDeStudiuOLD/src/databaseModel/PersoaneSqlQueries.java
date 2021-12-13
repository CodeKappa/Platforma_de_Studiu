//for testing
//TODO delete it
package databaseModel;

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
			System.out.println("cnp   nume                 parola  adresa          tel        email                 iban            nr_contract");
			while (rs.next()) 
			{		
				String cnp = rs.getString("cnp");
				String name = rs.getString("nume");
				String password = rs.getString("parola");
				String adress = rs.getString("adresa");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				String iban = rs.getString("iban");
				int contract_nr = rs.getInt("nr_contract");
				
				System.out.format("%-5s %-20s %-7s %-15s %-10s %-21s %-15s %-2d\n", cnp, name, password, adress, tel, email, iban, contract_nr);
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
		String query = "insert into persoane (nume, cnp, adresa, tel, parola, email, iban) values (?, ?, ?, ?, ?, ?, ?)";
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
}
