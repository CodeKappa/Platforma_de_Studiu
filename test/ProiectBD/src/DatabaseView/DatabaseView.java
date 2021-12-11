package DatabaseView;

import java.awt.Dimension;
//import java.awt.Point;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class DatabaseView extends JFrame{
	
	public PanelLogin panelLogin = new PanelLogin();	
	
	public DatabaseView()
	{
		this.addWindowListener(new CustomWindowListener());
		this.setTitle("Baza de Date Scoala");	
		add(panelLogin);
		pack();
		
		//setLocation(new Point(500, 200));
		setMinimumSize(new Dimension(100, 100));
		//setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	

}
