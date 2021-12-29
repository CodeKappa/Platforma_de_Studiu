package databaseView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class PanelStudent extends JPanel
{	
	private JTextField input = new JTextField();
	private JTable tableAfis = new JTable();
	private JButton buttonDatePersonale = new JButton("Date Persoanle");
	private JButton buttonCautaMaterie = new JButton("Cauta Materie");
	private JButton buttonInscriereActivitate = new JButton("Incriere la Activitate");
	private JButton buttonRenutaActivitate = new JButton("Renunta la Activitate");
	private JButton buttonVizualizareNote = new JButton("Vizualizeaza Note");
	private JButton buttonVizualizeazaGrupuri = new JButton("Vizualizeaza grupuri");
	private JButton buttonMembriDinGrup = new JButton("Vizualizeaza membrii din grup");
	private JButton buttonActivitati = new JButton("Pagina activitati");
	private JButton buttonDelogare = new JButton("Delogare");
	private JScrollPane jsp = new JScrollPane(tableAfis);
	
	PanelStudent()
	{
		//setLayout(null);
		
		add(jsp);
		
		//Login Button
		JPanel inputPanel = new JPanel();
		inputPanel.add(input);
		inputPanel.add(buttonDatePersonale);
		inputPanel.add(buttonCautaMaterie);
		inputPanel.add(buttonInscriereActivitate);
		inputPanel.add(buttonRenutaActivitate);
		inputPanel.add(buttonVizualizareNote);
		inputPanel.add(buttonVizualizeazaGrupuri);
		inputPanel.add(buttonMembriDinGrup);
		inputPanel.add(buttonActivitati);
		
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		
		add(inputPanel);
		
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
	
	public void addCautaMaterieListener(ActionListener listenCautaMaterieButton)
	{
		buttonCautaMaterie.addActionListener(listenCautaMaterieButton);
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
