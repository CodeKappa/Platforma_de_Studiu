package databaseView;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DatabaseView extends JFrame{
	
	public PanelLogin panelLogin = new PanelLogin();	
	//public PanelSignUp panelSignUp = new PanelSignUp();
	public PanelStudent panelStudentView = new PanelStudent();
	
	public DatabaseView()
	{
		this.addWindowListener(new CustomWindowListener());
		this.setTitle("Baza de Date Scoala");	
		//this.removeAll();		
		this.add(panelLogin);
		//switchPanels(panelSignUp);
		
		pack();
		//setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setLocation(new Point(500, 200));
		setMinimumSize(new Dimension(100, 100));
		//setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void switchPanels(JPanel panel)
	{
		getContentPane().removeAll();
		this.add(panel);
		this.repaint();
		this.revalidate();
		pack();
	}
}
