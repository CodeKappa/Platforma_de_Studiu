package databaseView_PanelStudent;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class PanelButoaneStudent extends JPanel
{
	public final JButton buttonDatePersonale = new JButton("Date Persoanle");
	public final JButton buttonMaterii = new JButton("Materii");
	public final JButton buttonGrupuri = new JButton("Grupuri");
	public final JButton buttonMesajeGrup = new JButton("Mesaje Grup");
	public final JButton buttonActivitatiGrup = new JButton("Activitati Grup");
	
	PanelButoaneStudent()
	{
		setLayout(null);	
		
		buttonDatePersonale.setBounds(51, 86, 181, 20);
		add(buttonDatePersonale);
		
		buttonMaterii.setBounds(51, 116, 181, 20);
		add(buttonMaterii);
		
		buttonGrupuri.setBounds(51, 146, 181, 20);	
		add(buttonGrupuri);
		
		buttonMesajeGrup.setBounds(51, 176, 181, 20);
		add(buttonMesajeGrup);
		
		buttonActivitatiGrup.setBounds(51, 206, 181, 20);
		add(buttonActivitatiGrup);
		
		this.setBorder(new LineBorder(Color.BLACK, 1));
	}
}
