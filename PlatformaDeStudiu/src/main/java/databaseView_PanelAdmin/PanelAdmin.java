package databaseView_PanelAdmin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import databaseView.PanelFeedback;

import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class PanelAdmin extends JPanel
{
    public PanelFeedback feedbackPanel = new PanelFeedback();
	private PanelButoaneAdmin inputPanel = new PanelButoaneAdmin();
	private JButton buttonDelogare = new JButton("Delogare");
	
	//private JLayeredPane layeredPane = new JLayeredPane();
	
	private JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);
	
	private PanelDatePersonale datePersonale = new PanelDatePersonale();
	private PanelCRUDuser crudUser = new PanelCRUDuser();
	private PanelCRUDmaterii crudMaterii = new PanelCRUDmaterii();
	private PanelCRUDgrupuri crudGrupuri = new PanelCRUDgrupuri();
	private PanelCautaUtilizatori cautaUtilizatori = new PanelCautaUtilizatori();
	private PanelCautaMaterii cautaMaterii = new PanelCautaMaterii();
	private PanelAsignareProfesori asignareProfesori = new PanelAsignareProfesori();
	private PanelAdaugaActivitati adaugaActivitati = new PanelAdaugaActivitati();
	
	private JPanel frontPanel;
	
    public PanelAdmin()
	{
		setLayout(null);
		
		feedbackPanel.setBounds(10,10, 1480, 100);
		add(feedbackPanel);
		feedbackPanel.reset();
			
		datePersonale.setBounds(10, 110, 1199, 450);
		crudUser.setBounds(10, 110, 1199, 450);
		crudMaterii.setBounds(10, 110, 1199, 450);
		crudGrupuri.setBounds(10, 110, 1199, 450);
		cautaUtilizatori.setBounds(10, 110, 1199, 450);
		cautaMaterii.setBounds(10, 110, 1199, 450);
		asignareProfesori.setBounds(10, 110, 1199, 450);
		adaugaActivitati.setBounds(10, 110, 1199, 450);
		
		//jsp.setBounds(0, 0, 1199, 450);
		
		inputPanel.setBounds(1219, 110, 271 , 419);
		add(inputPanel);
		
		
		add(datePersonale);
		add(crudUser);
		add(crudMaterii);
		add(crudGrupuri);
		add(cautaUtilizatori);
		add(cautaMaterii);
		add(asignareProfesori);
		add(adaugaActivitati);
		datePersonale.setVisible(true);
		crudUser.setVisible(false);
		crudMaterii.setVisible(false);
		crudGrupuri.setVisible(false);
		cautaUtilizatori.setVisible(false);
		cautaMaterii.setVisible(false);
		asignareProfesori.setVisible(false);
		adaugaActivitati.setVisible(false);
		
		frontPanel = datePersonale;
		
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
		inputPanel.buttonCRUDuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frontPanel.setVisible(false);
				crudUser.setVisible(true);
				frontPanel = crudUser;
			}
		});
		inputPanel.buttonCRUDmaterii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frontPanel.setVisible(false);
				crudMaterii.setVisible(true);	
				frontPanel = crudMaterii;
			}
		});
		inputPanel.buttonCRUDgrupuri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frontPanel.setVisible(false);
				crudGrupuri.setVisible(true);
				frontPanel = crudGrupuri;
			}
		});
		inputPanel.buttonCautaUtilizatori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frontPanel.setVisible(false);
				cautaUtilizatori.setVisible(true);
				frontPanel = cautaUtilizatori;
			}
		});
		inputPanel.buttonCRUDmaterii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frontPanel.setVisible(false);
				cautaMaterii.setVisible(true);
				frontPanel = cautaMaterii;
			}
		});
		inputPanel.buttonAsignareProfesori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frontPanel.setVisible(false);
				asignareProfesori.setVisible(true);	
				frontPanel = asignareProfesori;
			}
		});
		inputPanel.buttonAdaugaActivitatiGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frontPanel.setVisible(false);
				adaugaActivitati.setVisible(true);	
				frontPanel = adaugaActivitati;
			}
		});
    }
	
	public void setBackgroungColor(Color c)
	{
		this.setBackground(c);	
	}
	
	public Dimension getPreferredSize() { return new Dimension(1500,570); }
	
	/*
	 * public void addCautaMaterieListener(ActionListener listenCautaMaterieButton)
	 * { buttonCautaMaterie.addActionListener(listenCautaMaterieButton); }
	 */
	
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
