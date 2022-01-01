package main;

import databaseController.DatabaseController;
import databaseModel.DatabaseModel;
import databaseView.DatabaseView;

/**
 * Main class of the Java application. 
 * It contains the main method that starts the Java application.
 */
public class MainClass {
	
	public static DatabaseConnection db;
	public static final boolean WORK_FROM_HOME = true;
	/**
	 * The main method.
	 */
	public static void main(String[] args){
		
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
