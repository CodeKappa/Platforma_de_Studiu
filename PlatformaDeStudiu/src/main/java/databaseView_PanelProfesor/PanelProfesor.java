package databaseView_PanelProfesor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import databaseView.PanelFeedback;
import databaseView_PanelAdmin.PanelButoaneAdmin;
import databaseView_PanelAdmin.PanelDatePersonale;

@SuppressWarnings("serial")
public class PanelProfesor extends JPanel
{	
	public PanelFeedback feedbackPanel = new PanelFeedback();
	public PanelButoaneProfesor inputPanel = new PanelButoaneProfesor();
	private JButton buttonDelogare = new JButton("Delogare");
	
	public databaseView_PanelProfesor.PanelDatePersonale datePersonale = new databaseView_PanelProfesor.PanelDatePersonale();
	private JPanel blankPanel = new JPanel();
	
	private JPanel frontPanel;
	public PanelProfesor() {
		setLayout(null);
		
		feedbackPanel.setBounds(10, 9, 1480, 100);
		add(feedbackPanel);
		feedbackPanel.reset();
		
		blankPanel.setBounds(10, 110, 1199, 450);
		datePersonale.setBounds(10, 110, 1199, 450);
		
		inputPanel.setBounds(1219, 110, 271 , 419);
		add(inputPanel);
		
		blankPanel.setBorder(new LineBorder(Color.BLACK, 1));
		add(blankPanel);
		add(datePersonale);
		
		blankPanel.setVisible(true);
		datePersonale.setVisible(false);
		
		frontPanel = blankPanel;
		
		setActionListeners();
		
		buttonDelogare.setBounds(1220, 539, 270, 20);
		add(buttonDelogare);
			
		this.setBorder(new LineBorder(Color.BLUE, 5));
	}
	
	public void setActionListeners()
    {
    	inputPanel.buttonCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				frontPanel.setVisible(false);
				datePersonale.setVisible(true);	
				frontPanel = datePersonale;
				*/
			}
		});
    	inputPanel.buttonDatePersonale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frontPanel.setVisible(false);
				datePersonale.setVisible(true);	
				frontPanel = datePersonale;
			}
		});
    	inputPanel.buttonOrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				frontPanel.setVisible(false);
				datePersonale.setVisible(true);	
				frontPanel = datePersonale;
				*/
			}
		});
    	inputPanel.buttonPonderiNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				frontPanel.setVisible(false);
				datePersonale.setVisible(true);	
				frontPanel = datePersonale;
				*/
			}
		});
    	inputPanel.buttonProgramareCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				frontPanel.setVisible(false);
				datePersonale.setVisible(true);	
				frontPanel = datePersonale;
				*/
			}
		});
    	inputPanel.buttonVizualizareActivitatiStudiu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				frontPanel.setVisible(false);
				datePersonale.setVisible(true);	
				frontPanel = datePersonale;
				*/
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
