package main;

import java.sql.Connection;
import java.sql.SQLException;

import DatabaseController.DatabaseController;
import DatabaseModel.DatabaseModel;
import DatabaseView.DatabaseView;

public class MainClass {

	public static void main(String[] args){
		Connection con = null;
		
		//establish connection with JDBC
		try { con = Database.establishConnection(); } 
		catch (Exception e) { e.printStackTrace();}

		SqlQueries.setCon(con);
		
		Database.setDbName("test");
		try { SqlQueries.selectDatabase(); }
		catch (SQLException e) { e.printStackTrace();}
		
		try { SqlQueries.viewTable(); }
		catch (SQLException e) { e.printStackTrace();}
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DatabaseView theView = new DatabaseView();
				DatabaseModel theModel = new DatabaseModel();
				DatabaseController theController = new DatabaseController(theView, theModel);
				theView.setVisible(true);
			}
		});
		
		//try { Database.closeConnection(); } 
		//catch (Exception e) { e.printStackTrace();}
	}

}
