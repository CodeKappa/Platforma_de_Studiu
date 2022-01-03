package databaseView_PanelAdmin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import databaseView.PanelFeedback;

import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelAdmin extends JPanel
{
    public PanelFeedback feedbackPanel = new PanelFeedback();
	public PanelButoaneAdmin inputPanel = new PanelButoaneAdmin();
	private JButton buttonDelogare = new JButton("Delogare");
	
	public PanelDatePersonale datePersonale = new PanelDatePersonale();
	public PanelCRUDuser crudUser = new PanelCRUDuser();
	public PanelCRUDmaterii crudMaterii = new PanelCRUDmaterii();
	public PanelCRUDgrupuri crudGrupuri = new PanelCRUDgrupuri();
	public PanelCautaUtilizatori cautaUtilizatori = new PanelCautaUtilizatori();
	public PanelCautaMaterii cautaMaterii = new PanelCautaMaterii();
	public PanelAsignareProfesori asignareProfesori = new PanelAsignareProfesori();
	public PanelAdaugaActivitati adaugaActivitati = new PanelAdaugaActivitati();
	private JPanel blankPanel = new JPanel();
	
	private JPanel frontPanel;
	
    public PanelAdmin()
	{
		setLayout(null);
		
		feedbackPanel.setBounds(10, 9, 1480, 100);
		add(feedbackPanel);
		feedbackPanel.reset();
		
		blankPanel.setBounds(10, 110, 1199, 450);
		datePersonale.setBounds(10, 110, 1199, 450);
		crudUser.setBounds(10, 110, 1199, 450);
		crudMaterii.setBounds(10, 110, 1199, 450);
		crudGrupuri.setBounds(10, 110, 1199, 450);
		cautaUtilizatori.setBounds(10, 110, 1199, 450);
		cautaMaterii.setBounds(10, 110, 1199, 450);
		asignareProfesori.setBounds(10, 110, 1199, 450);
		adaugaActivitati.setBounds(10, 110, 1199, 450);
		
		inputPanel.setBounds(1219, 110, 271 , 419);
		add(inputPanel);

		blankPanel.setBorder(new LineBorder(Color.BLACK, 1));
		add(blankPanel);
		add(datePersonale);
		add(crudUser);
		add(crudMaterii);
		add(crudGrupuri);
		add(cautaUtilizatori);
		add(cautaMaterii);
		add(asignareProfesori);
		add(adaugaActivitati);
		
		blankPanel.setVisible(true);
		datePersonale.setVisible(false);
		crudUser.setVisible(false);
		crudMaterii.setVisible(false);
		crudGrupuri.setVisible(false);
		cautaUtilizatori.setVisible(false);
		cautaMaterii.setVisible(false);
		asignareProfesori.setVisible(false);
		adaugaActivitati.setVisible(false);
		
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
	
	public void reset()
	{
		frontPanel.setVisible(false);
		blankPanel.setVisible(true);	
		frontPanel = blankPanel;
	}
}
