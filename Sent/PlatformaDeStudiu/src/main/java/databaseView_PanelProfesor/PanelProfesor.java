package databaseView_PanelProfesor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import databaseView.PanelFeedback;

@SuppressWarnings("serial")
public class PanelProfesor extends JPanel
{	
	public PanelFeedback feedbackPanel = new PanelFeedback();
	public PanelButoaneProfesor inputPanel = new PanelButoaneProfesor();
	public PanelActivitati activitatiPanel = new PanelActivitati();
	public PanelCalendar calendarPanel = new PanelCalendar();
	public PanelCatalog catalogPanel = new PanelCatalog();
	public PanelPonderi ponderiPanel = new PanelPonderi();
	private JButton buttonDelogare = new JButton("Delogare");
	
	public databaseView_PanelProfesor.PanelDatePersonale datePersonale = new databaseView_PanelProfesor.PanelDatePersonale();
	private JPanel blankPanel = new JPanel();
	
	public JPanel frontPanel;
	public PanelProfesor() {
		setLayout(null);
		
		feedbackPanel.setBounds(10, 9, 1480, 100);
		add(feedbackPanel);
		PanelFeedback.reset();
		
		blankPanel.setBounds(10, 110, 1199, 450);
		datePersonale.setBounds(10, 110, 1199, 450);
		activitatiPanel.setBounds(10, 110, 1199, 450);
		calendarPanel.setBounds(10, 110, 1199, 450);
		catalogPanel.setBounds(10, 110, 1199, 450);
		ponderiPanel.setBounds(10, 110, 1199, 450);
		
		inputPanel.setBounds(1219, 110, 271 , 419);
		add(inputPanel);
		
		blankPanel.setBorder(new LineBorder(Color.BLACK, 1));
		add(blankPanel);
		add(datePersonale);
		add(activitatiPanel);
		add(calendarPanel);
		add(catalogPanel);
		add(ponderiPanel);
		
		blankPanel.setVisible(true);
		datePersonale.setVisible(false);
		activitatiPanel.setVisible(false);
		calendarPanel.setVisible(false);
		catalogPanel.setVisible(false);
		ponderiPanel.setVisible(false);
		
		frontPanel = blankPanel;
		
		buttonDelogare.setBounds(1220, 539, 270, 20);
		add(buttonDelogare);
			
		this.setBorder(new LineBorder(Color.BLUE, 5));
	}
	
	public void setBackgroungColor(Color c)
	{
		this.setBackground(c);	
	}
	
	public Dimension getPreferredSize() { return new Dimension(1500,570); }
	
	
	public void addDelogareListener(ActionListener delogareButton)
	{
		buttonDelogare.addActionListener(delogareButton);
	}
	
	public void reset()
	{
		frontPanel.setVisible(false);
		blankPanel.setVisible(true);	
		frontPanel = blankPanel;
	}
}
