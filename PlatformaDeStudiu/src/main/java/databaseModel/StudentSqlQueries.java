package databaseModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import databaseView.PanelFeedback;

public class StudentSqlQueries {
	public static ArrayList<ArrayList<String>> vizualizare_note(Connection con, String cnp) throws SQLException 
	{	
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Vizualizare_note(?)}");
			cs.setString(1, cnp);
			ResultSet rs = cs.executeQuery();
			con.commit();
			
			while (rs.next()) 
			{
				ArrayList<String> aux = new ArrayList<String>();
				aux.add(rs.getString("nume"));
				aux.add(rs.getString("categorie"));
				aux.add(rs.getString("nota"));
				arr.add(aux);
			}	
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
			con.rollback();
			return null;
		}
		con.rollback();
		return arr;
	}
	
	public static ArrayList<ArrayList<String>> materii_propri(Connection con, String cnp) throws SQLException 
	{	
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Materii_propri(?)}");
			cs.setString(1, cnp);
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
				aux.add(rs.getString("recurenta_c"));
				aux.add(rs.getString("recurenta_s"));
				aux.add(rs.getString("recurenta_l"));
				arr.add(aux);
			}	
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
			con.rollback();
			return null;
		}
		con.rollback();
		return arr;
	}
	
	public static ArrayList<ArrayList<String>> inscriere_materie(Connection con, String materie, String cnp) throws SQLException 
	{	
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Inscriere_materie(?,?)}");
			cs.setString(1, materie);
			cs.setString(2, cnp);
			cs.execute();
			con.commit();
			PanelFeedback.feedbackMessage = "Inscris";	
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
			con.rollback();
			return null;
		}
		con.rollback();
		return arr;
	}
	
	public static ArrayList<ArrayList<String>> renuntare_materie(Connection con, String id_mat, String cnp) throws SQLException 
	{	
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Renuntare_materie(?,?)}");
			cs.setString(1, id_mat);
			cs.setString(2, cnp);
			cs.execute();
			con.commit();
			PanelFeedback.feedbackMessage = "Ai renuntat";
		} 
		catch (SQLException e) 
		{
			TreatException.printSQLException(e);
			con.rollback();
			return null;
		}
		con.rollback();
		return arr;
	}
}
