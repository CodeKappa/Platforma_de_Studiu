package databaseView;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import databaseView_PanelAdmin.PanelAdmin;
import databaseView_PanelAdmin.PanelSuperAdmin;
import databaseView_PanelProfesor.PanelProfesor;
import databaseView_PanelStudent.PanelStudent;

@SuppressWarnings("serial")
public class DatabaseView extends JFrame{
	
	public PanelLogin panelLogin = new PanelLogin();	
	//public PanelSignUp panelSignUp = new PanelSignUp();
	public PanelSuperAdmin panelSuperAdminView = new PanelSuperAdmin();
	public PanelAdmin panelAdminView = new PanelAdmin();
	public PanelStudent panelStudentView = new PanelStudent();
	public PanelProfesor panelProfesorView = new PanelProfesor();
	
	public DatabaseView()
	{
		this.addWindowListener(new CustomWindowListener());
		this.setTitle("Baza de Date Scoala");		
		this.add(panelLogin);
		
		pack();
		setLocation(new Point(600, 250));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void switchPanels(JPanel panel)
	{
		getContentPane().removeAll();
		add(panel);
		if(panel == panelLogin)setLocation(new Point(600, 250));
		else setLocation(new Point(10, 150));
		repaint();
		revalidate();
		pack();
	}
}
