package databaseView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class PanelAfis extends JPanel
{	
	//private JTextField usernameField = new JTextField();
	//private JTextField passwordField = new JTextField();
	private JTable tableAfis = new JTable();
	private JButton buttonAfis = new JButton("Afis");
	private JButton buttonDelogare = new JButton("Delogare");
	private JScrollPane jsp = new JScrollPane(tableAfis);
	
	PanelAfis()
	{
		//setLayout(null);
		
		add(jsp);
		
		//Login Button
		add(buttonAfis);
		//buttonAfis.setBounds(50, 110, 100, 25);
		
		//Button to go to Sign Up
		add(buttonDelogare);
		//buttonDelogare.setBounds(150, 110, 100, 25);
		
		this.setBorder(new LineBorder(Color.BLUE, 5));
	}
	
	public void setBackgroungColor(Color c)
	{
		this.setBackground(c);	
	}
	
	public Dimension getPreferredSize() { return new Dimension(700,422); }
	
	public void addAfisListener(ActionListener listenAfisButton)
	{
		buttonAfis.addActionListener(listenAfisButton);
	}
	
	public void addDelogareListener(ActionListener delogareButton)
	{
		buttonDelogare.addActionListener(delogareButton);
	}
	
	public void setTable(ArrayList<ArrayList<String>> a)
	{
		DefaultTableModel dtm = new DefaultTableModel();
		
		dtm.setColumnCount(8);
		int i = 0, j = 0;
		for(ArrayList<String> arow : a)
		{
			dtm.setRowCount(dtm.getRowCount() + 1);
			for(String s : arow)
			{
				
				dtm.setValueAt(s, i, j);
				j++;
			}
			i++;
		}	
		tableAfis.setModel(dtm);
		repaint();
	}
}
