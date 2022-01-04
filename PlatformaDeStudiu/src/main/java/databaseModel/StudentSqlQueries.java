package databaseModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import databaseView.PanelFeedback;

public class StudentSqlQueries {
	/**
	 * @wbp.parser.entryPoint
	 */
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
	
	public static ArrayList<ArrayList<String>> vezi_grupuri(Connection con) throws SQLException 
	{	
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Vizualizare_grupuri()}");
			ResultSet rs = cs.executeQuery();
			con.commit();
			
			while (rs.next()) 
			{
				ArrayList<String> aux = new ArrayList<String>();
				aux.add(rs.getString("id"));
				aux.add(rs.getString("nume"));
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
	
	public static ArrayList<ArrayList<String>> membri_grup(Connection con, String id, String cnp) throws SQLException 
	{	
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Vizualizare_membrii_grup(?,?)}");
			cs.setString(1, id);
			cs.setString(2, cnp);
			ResultSet rs = cs.executeQuery();
			con.commit();
			
			while (rs.next()) 
			{
				ArrayList<String> aux = new ArrayList<String>();
				aux.add(rs.getString("nume"));
				aux.add(rs.getString("prenume"));
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
	
	public static ArrayList<ArrayList<String>> inscriere_grup(Connection con, String id, String cnp) throws SQLException 
	{	
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Inscriere_grup(?,?)}");
			cs.setString(1, id);
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
	
	public static ArrayList<ArrayList<String>> renuntare_grup(Connection con, String id, String cnp) throws SQLException 
	{	
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Renuntare_materie(?,?)}");
			cs.setString(1, id);
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
	
	public static ArrayList<ArrayList<String>> trimite_mesaj(Connection con, String msj, String grup, String cnp) throws SQLException 
	{	
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Mesaje_scriere(?,?,?)}");
			cs.setString(1, msj);
			cs.setString(2, grup);
			cs.setString(3, cnp);
			cs.execute();
			con.commit();
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
	
	public static ArrayList<ArrayList<String>> preia_mesaje(Connection con, String id) throws SQLException 
	{	
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Mesaje_vizualizare(?)}");
			cs.setString(1, id);
			ResultSet rs = cs.executeQuery();
			con.commit();
			
			while (rs.next()) 
			{
				ArrayList<String> aux = new ArrayList<String>();
				aux.add(rs.getString("nume"));
				aux.add(rs.getString("prenume"));
				aux.add(rs.getString("mesaj"));
				aux.add(rs.getString("data_ora_trimiterii"));
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
	
	public static ArrayList<ArrayList<String>> sugestii_grup(Connection con, String cnp) throws SQLException 
	{	
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		try 
		{
			CallableStatement cs = con.prepareCall("{call Sugestii_grupuri(?)}");
			cs.setString(1, cnp);
			ResultSet rs = cs.executeQuery();
			con.commit();
			
			while (rs.next()) 
			{
				ArrayList<String> aux = new ArrayList<String>();
				aux.add(rs.getString("nume"));
				aux.add(rs.getString("id"));
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
	
}
