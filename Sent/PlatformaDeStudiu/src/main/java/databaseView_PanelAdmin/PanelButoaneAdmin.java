package databaseView_PanelAdmin;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class PanelButoaneAdmin extends JPanel
{
	public final JButton buttonDatePersonale = new JButton("Date Persoanle");
	public final JButton buttonCautaMaterie = new JButton("Cauta Materie");
	public final JButton buttonCRUDuser = new JButton("CRUD users");
	public final JButton buttonCRUDmaterii = new JButton("CRUD materii");
	public final JButton buttonCRUDgrupuri = new JButton("CRUD grupuri");
	public final JButton buttonCautaUtilizatori = new JButton("Cauta Utilizator");
	public final JButton buttonAsignareProfesori = new JButton("Asignare Profesori");
	public final JButton buttonAdaugaActivitatiGrup = new JButton("Adauga activitati grup");
	
	PanelButoaneAdmin()
	{
		setLayout(null);	
		
		buttonDatePersonale.setBounds(51, 65, 181, 20);
		add(buttonDatePersonale);
		
		buttonCRUDuser.setBounds(51, 95, 181, 20);
		add(buttonCRUDuser);
		
		buttonCRUDmaterii.setBounds(51, 126, 181, 20);	
		add(buttonCRUDmaterii);
		
		buttonCRUDgrupuri.setBounds(51, 156, 181, 20);
		add(buttonCRUDgrupuri);
		
		buttonCautaUtilizatori.setBounds(51, 186, 181, 20);
		add(buttonCautaUtilizatori);
		
		buttonCautaMaterie.setBounds(51, 216, 181, 20);
		add(buttonCautaMaterie);	
				
		buttonAsignareProfesori.setBounds(51, 246, 181, 20);
		add(buttonAsignareProfesori);	
		
		buttonAdaugaActivitatiGrup.setBounds(51, 276, 181, 20);
		add(buttonAdaugaActivitatiGrup);
		
		this.setBorder(new LineBorder(Color.BLACK, 1));
	}
}
