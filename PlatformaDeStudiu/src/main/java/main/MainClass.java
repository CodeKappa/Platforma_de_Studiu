package main;

import java.sql.SQLException;

import databaseController.DatabaseController;
import databaseModel.DatabaseModel;
import databaseModel.PersoaneSqlQueries;
import databaseView.DatabaseView;

/**
 * Main class of the Java application. 
 * It contains the main method that starts the Java application.
 */
public class MainClass {
	
	public static DatabaseConnection db;
	
	/**
	 * The main method.
	 */
	public static void main(String[] args){
		
		//details for the MySQL JDBC driver to develop the program
		String url = "jdbc:mysql://192.168.58.155:3306/";
		String user = "user";
	    String password = "ce_parola2021";	
	    
	    db = new DatabaseConnection(url,user,password);
		
		//set the name of database we work with
		db.setDbName("gestiune_studenti");
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DatabaseView theView = new DatabaseView();
				DatabaseModel theModel = new DatabaseModel();
				@SuppressWarnings("unused")
				DatabaseController theController = new DatabaseController(theView, theModel);
				theView.setVisible(true);
			}
		});
		
		//select database we work with "use test"
		try { db.selectDatabase(db.getCon()); }
		catch (SQLException e) { e.printStackTrace();}
		
		//print in console database in use "select * from persoane"
		try { PersoaneSqlQueries.viewPersoane(db.getCon()); }
		catch (SQLException e) { e.printStackTrace();}	
	}

}
