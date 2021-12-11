package databaseController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import databaseModel.DatabaseModel;
import databaseModel.PersoaneSqlQueries;
import databaseView.DatabaseView;
import main.MainClass;

public class DatabaseController {
	protected DatabaseView theView;
	protected DatabaseModel theModel;
	
	public DatabaseController(DatabaseView theView, DatabaseModel theModel) {
		super();
		this.theView = theView;
		this.theModel = theModel;
		theView.panelLogin.addSubmitListener(new SubmitListener());
	}
	
	class SubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			String username;
			String password;
			try
			{
				username = theView.panelLogin.getUsername();
				password = theView.panelLogin.getPassword();
				if(PersoaneSqlQueries.isValidPassword(MainClass.db.getCon(),username, password) == true)
				{
					theView.panelLogin.setBackground(java.awt.Color.GREEN);
					//theView.panelLogin.setVisible(false);
				}					
				else
				{
					theView.panelLogin.setBackground(java.awt.Color.RED);	
				}				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}		
	}
}
