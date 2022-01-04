package databaseView_PanelStudent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import databaseView.PanelFeedback;

import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelStudent extends JPanel
{
    public PanelFeedback feedbackPanel = new PanelFeedback();
	public PanelButoaneStudent inputPanel = new PanelButoaneStudent();
	private JButton buttonDelogare = new JButton("Delogare");
	
	public PanelDatePersonale datePersonale = new PanelDatePersonale();
	public PanelStudentMaterii materii = new PanelStudentMaterii();
	public PanelStudentGrup grupuri = new PanelStudentGrup();
	public PanelStudentMesajeGrup mesaje = new PanelStudentMesajeGrup();
	public PanelStudentCalendar calendar = new PanelStudentCalendar();
	private JPanel blankPanel = new JPanel();
	
	private JPanel frontPanel;
	
    public PanelStudent()
	{
		setLayout(null);
		
		feedbackPanel.setBounds(10, 9, 1480, 100);
		add(feedbackPanel);
		PanelFeedback.reset();
		
		blankPanel.setBounds(10, 110, 1199, 450);
		datePersonale.setBounds(10, 110, 1199, 450);
		//materii.setBounds(10, 110, 1199, 450);
		//grupuri.setBounds(10, 110, 1199, 450);
		//mesaje.setBounds(10, 110, 1199, 450);
		//calendar.setBounds(10, 110, 1199, 450);
		
		inputPanel.setBounds(1219, 110, 271 , 419);
		add(inputPanel);

		blankPanel.setBorder(new LineBorder(Color.BLACK, 1));
		add(blankPanel);
		add(datePersonale);
		//add(materii);
		//add(grupuri);
		//add(mesaje);
		//add(calendar);
		
		blankPanel.setVisible(true);
		datePersonale.setVisible(false);
		//materii.setVisible(false);
		//grupuri.setVisible(false);
		//mesaje.setVisible(false);
		//calendar.setVisible(false);
		
		frontPanel = blankPanel;
		
		setActionListeners();
		
		buttonDelogare.setBounds(1220, 539, 270, 20);
		add(buttonDelogare);
			
		this.setBorder(new LineBorder(Color.BLUE, 5));
	}
   
    public void setActionListeners()
    {
    	inputPanel.buttonDatePersonale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frontPanel.setVisible(false);
				datePersonale.setVisible(true);	
				frontPanel = datePersonale;
			}
		});
		inputPanel.buttonMaterii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frontPanel.setVisible(false);
				materii.setVisible(true);
				frontPanel = materii;
			}
		});
		inputPanel.buttonGrupuri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frontPanel.setVisible(false);
				grupuri.setVisible(true);	
				frontPanel = grupuri;
			}
		});
		inputPanel.buttonMesajeGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frontPanel.setVisible(false);
				mesaje.setVisible(true);
				frontPanel = mesaje;
			}
		});
		inputPanel.buttonCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frontPanel.setVisible(false);
				calendar.setVisible(true);
				frontPanel = calendar;
			}
		});
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
