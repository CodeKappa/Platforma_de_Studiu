package databaseView_PanelProfesor;

import java.awt.Color;
import java.awt.Dimension;
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

@SuppressWarnings("serial")
public class PanelProfesor extends JPanel
{	
	private JTextField input = new JTextField();
	private JTable tableAfis = new JTable();
	private JButton buttonDatePersonale = new JButton("Date Persoanle");
	private JButton buttonCautaMaterie = new JButton("Cauta Materie");
	private JButton buttonInscriereActivitate = new JButton("Incriere la Activitate");
	private JButton buttonRenutaActivitate = new JButton("Renunta la Activitate");
	private JButton buttonVizualizareNote = new JButton("Vizualizeaza Note");
	private JButton buttonVizualizeazaGrupuri = new JButton("Vizualizeaza grupuri");
	private JButton buttonMembriDinGrup = new JButton("Vizualizeaza membrii grup");
	private JButton buttonActivitati = new JButton("Pagina activitati");
	private JButton buttonDelogare = new JButton("Delogare");
	private JScrollPane jsp = new JScrollPane(tableAfis);
	
    public PanelFeedback feedbackPanel = new PanelFeedback();
	
	public PanelProfesor()
	{
		setLayout(null);
		
		add(feedbackPanel);
		feedbackPanel.setBounds(10,10, 1480, 100);
		feedbackPanel.setBorder(new LineBorder(Color.BLACK, 1));
		feedbackPanel.reset();
		
		//JScrollPane to print data
		add(jsp);
		jsp.setBounds(10, 110, 1250, 450);
		///remove(jsp);
		///add(jsp);
		///revalidate();
		///repaint();
		
		//user input panel
		JPanel inputPanel = new JPanel();
		
		inputPanel.setLayout(null);
		
		inputPanel.add(buttonDatePersonale);
		inputPanel.add(buttonVizualizareNote);
		inputPanel.add(buttonVizualizeazaGrupuri);
		inputPanel.add(buttonActivitati);
		
		inputPanel.add(input);
		
		//inputPanel.add(buttonCautaMaterie);
		inputPanel.add(buttonInscriereActivitate);
		inputPanel.add(buttonRenutaActivitate);
		inputPanel.add(buttonMembriDinGrup);	
		
		int width = 181;
		buttonDatePersonale.setBounds(0, 0, width, 20);
		buttonVizualizareNote.setBounds(0, 20, width, 20);
		buttonVizualizeazaGrupuri.setBounds(0, 40, width, 20);
		buttonActivitati.setBounds(0, 60, width, 20);
		
		input.setBounds(0, 120, width, 20);
		buttonCautaMaterie.setBounds(0, 140, width, 20);
		buttonInscriereActivitate.setBounds(0, 160, width, 20);
		buttonRenutaActivitate.setBounds(0, 180, width, 20);
		buttonMembriDinGrup.setBounds(0, 200, width, 20);
		
		input.setText("profesor");
		
		add(inputPanel);
		inputPanel.setBounds(1285, 110, width, 300);
		//buttonAfis.setBounds(50, 110, 100, 25);
		
		//Button to go to Log in
		add(buttonDelogare);
		buttonDelogare.setBounds(1285, 539, width, 20);
		
		this.setBorder(new LineBorder(Color.BLUE, 5));
	}
	
	public void setBackgroungColor(Color c)
	{
		this.setBackground(c);	
	}
	
	public Dimension getPreferredSize() { return new Dimension(1500,570); }
	
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
		if(a.isEmpty() == false)
			dtm.setColumnCount(a.get(0).size());
		int i = 0, j = 0;
		for(ArrayList<String> arow : a)
		{
			dtm.setRowCount(dtm.getRowCount() + 1);
			j = 0;
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
