package databaseController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import databaseModel.DatabaseModel;
import databaseModel.PersoaneSqlQueries;
import databaseView.DatabaseView;
import databaseView.PanelLogin;
import databaseView_SignUpPanel.PanelSignUp;
import databaseView_SignUpPanel.SIGNUP_LABEL;
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
		theView.panelSignUp.addSignUpListener(new SignUpListener());
		theView.panelLogin.addComuteListener(new LoginComuteListener());
		theView.panelSignUp.addComuteListener(new SignUpComuteListener());
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
				username = PanelLogin.getUsername();
				password = PanelLogin.getPassword();
				if(username.length() > 0 && password.length() > 0 
				   && PersoaneSqlQueries.isValidPassword(MainClass.db.getCon(), Integer.parseInt(username), password) == true
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
				    for (int i = 0; i < 30; i++) 
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
	
	class SignUpListener implements ActionListener 
	{
		
		boolean checkData(ArrayList<String> data)
		{
			//check all are != NULL
			for(String i : data) 
			{
				if(i.length() == 0) return false;
			}
			
			return true;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			ArrayList<String> data = new ArrayList<String>();
			try
			{
				data.add(PanelSignUp.getTextFrom(SIGNUP_LABEL.NAME));		    //0
				data.add(PanelSignUp.getTextFrom(SIGNUP_LABEL.CNP));		    //1
				data.add(PanelSignUp.getTextFrom(SIGNUP_LABEL.ADRESS));		    //2
				data.add(PanelSignUp.getTextFrom(SIGNUP_LABEL.PHONE));  	    //3
				data.add(PanelSignUp.getTextFrom(SIGNUP_LABEL.EMAIL));		    //4
				data.add(PanelSignUp.getTextFrom(SIGNUP_LABEL.IBAN));		    //5
				data.add(PanelSignUp.getTextFrom(SIGNUP_LABEL.PASSWORD));	    //6
				
				//data.add(PanelSignUp.getTextFrom(SIGNUP_LABEL.PASSWORDCONFIRM));//7
				
				boolean ok = checkData(data);
				if(ok == true){
					theView.panelSignUp.setBackground(java.awt.Color.GREEN); //TODO delete after testing
					theView.switchPanels(theView.panelLogin);
					PersoaneSqlQueries.insertIntoPersoane(MainClass.db.getCon(), data);
				}					
				else
				{
					theView.panelSignUp.setBackground(java.awt.Color.RED);
					
					Point currLocation;
					int iDisplaceXBy = 5;
					int iDisplaceYBy = -10;
					currLocation = theView.getLocationOnScreen();

				    Point position1 = new Point(currLocation.x + iDisplaceXBy, currLocation.y + iDisplaceYBy);
				    Point position2 = new Point(currLocation.x - iDisplaceXBy, currLocation.y - iDisplaceYBy);
				    for (int i = 0; i < 30; i++) 
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
	
	class SignUpComuteListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			theView.switchPanels(theView.panelLogin);
		}
	}
	
	class LoginComuteListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			theView.switchPanels(theView.panelSignUp);
		}
	}
}
