package databaseController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import databaseModel.DatabaseModel;
import databaseModel.PersoaneSqlQueries;
import databaseModel.TreatException;
import databaseView.DatabaseView;
import databaseView.PanelFeedback;
import main.DatabaseConnection;
import main.MainClass;
import java.awt.Point;

public class DatabaseController {
	private DatabaseView theView;
	private DatabaseModel theModel;
	public static String user = new String();
	
	public DatabaseController(DatabaseView theView, DatabaseModel theModel) {
		super();
		this.theView = theView;
		this.theModel = theModel;
		AdminListeners al = new AdminListeners(theView, theModel);
		ProfesorListeners pl = new ProfesorListeners(theView, theModel);
		
		theView.panelLogin.addLoginListener(new LoginListener());
		//theView.panelSignUp.addSignUpListener(new SignUpListener());
		//theView.panelLogin.addComuteListener(new LoginComuteListener());
		//theView.panelSignUp.addComuteListener(new SignUpComuteListener());
		
		theView.panelAdminView.addDelogareListener(new DelogareListener());
		theView.panelStudentView.addDelogareListener(new DelogareListener());
		theView.panelProfesorView.addDelogareListener(new DelogareListener());
		
		//theView.panelStudentView.addCautaMaterieListener(new CautaMaterieListener());
	}

	class LoginListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//details for the MySQL JDBC driver to develop the program
			String username;
			String password;
		    String url = "jdbc:mysql://192.168.58.155:3306/";
		    String dbName = "gestiune_studenti";		
		    
		    try
			{
				username = theView.panelLogin.getUsername();
				password = theView.panelLogin.getPassword();
				
				if(username.length() > 0 && password.length() > 0)
				{
				       
					if(MainClass.WORK_FROM_HOME == true)
					{
					    url = "jdbc:mysql://localhost:3306/";
					}
				    
					MainClass.db = new DatabaseConnection(url, username, password, dbName);			
						
				    if(MainClass.db.getCon() != null)
				    {
				    	user = PersoaneSqlQueries.get_cnp(MainClass.db.getCon(), username);
				    	int tipUser = 0;
				    	tipUser = PersoaneSqlQueries.determina_tip_utilizator(MainClass.db.getCon(), user);
				    	if(tipUser == 1 && username.equals("superadmin"))
				    		theView.switchPanels(theView.panelSuperAdminView);
				    	else if(tipUser == 1)
				    		theView.switchPanels(theView.panelAdminView);
				    	else if(tipUser == 2)
				    		theView.switchPanels(theView.panelStudentView);
				    	else if (tipUser == 3)
				    		theView.switchPanels(theView.panelProfesorView);
				    	
				    	PanelFeedback.reset();
				    	//theView.panelLogin.setBackground(java.awt.Color.GREEN);
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
			}
			catch(SQLException ex)
			{
				TreatException.printSQLException(ex);
			}
		}
	}
	/*
	class CautaMaterieListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{	
			try 
			{
				theView.panelStudentView.feedbackPanel.feedbackMessage = "Ati cautat materia BD";
				theView.panelStudentView.setTable(PersoaneSqlQueries.cautare_materie (MainClass.db.getCon()));
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}	
	}
	*/
	class DelogareListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			user = null;
			if(MainClass.db != null && MainClass.db.getCon() != null)
				MainClass.db.closeConnection();
			MainClass.db = null;
			
			theView.panelAdminView.reset();
			
			theView.panelLogin.reset();
			theView.switchPanels(theView.panelLogin);
		}		
	}
	
	/*
	 * class SignUpListener implements ActionListener {
	 * 
	 * boolean checkData(ArrayList<String> data) { //check all are != NULL
	 * for(String i : data) { if(i.length() == 0) return false; }
	 * 
	 * return true; }
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { ArrayList<String> data
	 * = new ArrayList<String>(); try {
	 * data.add(theView.panelSignUp.getTextFrom(SIGNUP_LABEL.NAME)); //0
	 * data.add(theView.panelSignUp.getTextFrom(SIGNUP_LABEL.PRENUME)); //1
	 * data.add(theView.panelSignUp.getTextFrom(SIGNUP_LABEL.CNP)); //2
	 * data.add(theView.panelSignUp.getTextFrom(SIGNUP_LABEL.ADRESS)); //3
	 * data.add(theView.panelSignUp.getTextFrom(SIGNUP_LABEL.PHONE)); //4
	 * data.add(theView.panelSignUp.getTextFrom(SIGNUP_LABEL.EMAIL)); //5
	 * data.add(theView.panelSignUp.getTextFrom(SIGNUP_LABEL.IBAN)); //6
	 * data.add(theView.panelSignUp.getTextFrom(SIGNUP_LABEL.PASSWORD)); //7
	 * 
	 * //data.add(PanelSignUp.getTextFrom(SIGNUP_LABEL.PASSWORDCONFIRM));//7
	 * 
	 * boolean ok = checkData(data); if(ok == true){
	 * theView.panelSignUp.setBackground(java.awt.Color.GREEN); //TODO delete after
	 * testing theView.switchPanels(theView.panelLogin);
	 * PersoaneSqlQueries.insertIntoPersoane(MainClass.db.getCon(), data); } else {
	 * theView.panelSignUp.setBackground(java.awt.Color.RED);
	 * 
	 * Point currLocation; int iDisplaceXBy = 5; int iDisplaceYBy = -10;
	 * currLocation = theView.getLocationOnScreen();
	 * 
	 * Point position1 = new Point(currLocation.x + iDisplaceXBy, currLocation.y +
	 * iDisplaceYBy); Point position2 = new Point(currLocation.x - iDisplaceXBy,
	 * currLocation.y - iDisplaceYBy); for (int i = 0; i < 30; i++) {
	 * theView.setLocation(position1); theView.setLocation(position2); }
	 * theView.setLocation(currLocation); } } catch(Exception ex) {
	 * ex.printStackTrace(); } } }
	 */
	
	/*
	 * class SignUpComuteListener implements ActionListener {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * theView.switchPanels(theView.panelLogin); } }
	 */
	
	/*
	 * class LoginComuteListener implements ActionListener {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * theView.switchPanels(theView.panelSignUp); } }
	 */
}
