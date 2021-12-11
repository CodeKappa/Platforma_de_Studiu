package DatabaseController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DatabaseModel.DatabaseModel;
import DatabaseModel.SqlQueries;
import DatabaseView.DatabaseView;
import main.MainClass;

public class DatabaseController {
	DatabaseView theView;
	DatabaseModel theModel;
	
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
				username = theView.panelLogin.getUsernameField();
				password = theView.panelLogin.getPasswordField();
				if(SqlQueries.isValidPassword(MainClass.db.getCon(),username, password) == true)
					theView.panelLogin.setBackground(java.awt.Color.GREEN);
				else
					theView.panelLogin.setBackground(java.awt.Color.RED);		
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}		
	}
}