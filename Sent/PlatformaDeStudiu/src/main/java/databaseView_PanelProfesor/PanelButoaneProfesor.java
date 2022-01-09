package databaseView_PanelProfesor;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class PanelButoaneProfesor extends JPanel
{
	public final JButton buttonDatePersonale = new JButton("Date Persoanle");
	public final JButton buttonVizualizareActivitatiStudiu = new JButton("Activitati studiu");
	public final JButton buttonCalendar = new JButton("Calendar");
	public final JButton buttonPonderiNote = new JButton("Ponderi note");
	public final JButton buttonCatalog = new JButton("Catalog");
	
	PanelButoaneProfesor()
	{
		setLayout(null);	
		
		buttonDatePersonale.setBounds(51, 65, 181, 20);
		add(buttonDatePersonale);
		
		buttonVizualizareActivitatiStudiu.setBounds(51, 95, 181, 20);
		add(buttonVizualizareActivitatiStudiu);
		
		buttonCalendar.setBounds(51, 126, 181, 20);	
		add(buttonCalendar);
		
		buttonPonderiNote.setBounds(51, 156, 181, 20);
		add(buttonPonderiNote);
		
		buttonCatalog.setBounds(51, 186, 181, 20);
		add(buttonCatalog);
		
		this.setBorder(new LineBorder(Color.BLACK, 1));
	}
}
