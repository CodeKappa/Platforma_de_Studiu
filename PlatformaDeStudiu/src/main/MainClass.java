package main;

import java.sql.SQLException;

import DatabaseController.DatabaseController;
import DatabaseModel.DatabaseModel;
import DatabaseModel.SqlQueries;
import DatabaseView.DatabaseView;

public class MainClass {

	public static DatabaseConnection db;
	public static DatabaseView theView;
	public static DatabaseModel theModel;
	public static DatabaseController theController;
	
	public static void main(String[] args){
		
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
	    String password = "12344321";	
	    
	    db = new DatabaseConnection(url,user,password);
		
		//select database you want to work with
		db.setDbName("test");
		try { SqlQueries.selectDatabase(db.getCon()); }
		catch (SQLException e) { e.printStackTrace();}
		
		//print in console
		try { SqlQueries.viewTable(db.getCon()); }
		catch (SQLException e) { e.printStackTrace();}
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DatabaseView theView = new DatabaseView();
				DatabaseModel theModel = new DatabaseModel();
				@SuppressWarnings("unused")
				DatabaseController theController = new DatabaseController(theView, theModel);
				theView.setVisible(true);
			}
		});
	}

}
