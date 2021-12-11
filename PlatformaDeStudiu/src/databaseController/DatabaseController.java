package databaseController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import databaseModel.DatabaseModel;
import databaseModel.PersoaneSqlQueries;
import databaseView.DatabaseView;
import main.MainClass;
import java.awt.Point;

public class DatabaseController {
	protected DatabaseView theView;
	protected DatabaseModel theModel;

	public DatabaseController(DatabaseView theView, DatabaseModel theModel) {
		super();
		this.theView = theView;
		this.theModel = theModel;
		theView.panelLogin.addLoginListener(new LoginListener());
	}

	class LoginListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String username;
			String password;
			try
			{
				username = theView.panelLogin.getUsername();
				password = theView.panelLogin.getPassword();
				if(username.length() > 0 && password.length() > 0 
				   && PersoaneSqlQueries.isValidPassword(MainClass.db.getCon(),username, password) == true
				){
					theView.panelLogin.setBackground(java.awt.Color.GREEN);
					//theView.panelLogin.setVisible(false);
				}					
				else
				{
					theView.panelLogin.setBackground(java.awt.Color.RED);
					
					Point currLocation;
					int iDisplaceXBy = 5;
					int iDisplaceYBy = -10;
					currLocation = theView.getLocationOnScreen();

				    Point position1 = new Point(currLocation.x + iDisplaceXBy, currLocation.y + iDisplaceYBy);
				    Point position2 = new Point(currLocation.x - iDisplaceXBy, currLocation.y - iDisplaceYBy);
				    for (int i = 0; i < 20; i++) 
				    {
					    theView.setLocation(position1);
					    theView.setLocation(position2);
				    }
				    theView.setLocation(currLocation);
				}				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
}
