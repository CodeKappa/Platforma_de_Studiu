package databaseView_PanelProfesor;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class PanelButoaneProfesor extends JPanel
{
	public final JButton buttonDatePersonale = new JButton("Date Persoanle");
	public final JButton buttonOrar = new JButton("Orar");
	public final JButton buttonVizualizareActivitatiStudiu = new JButton("Activitati studiu");
	public final JButton buttonProgramareCalendar = new JButton("Programare activit\u0103\u021Bi calendar");
	public final JButton buttonPonderiNote = new JButton("Ponderi note");
	public final JButton buttonCatalog = new JButton("Catalog");
	
	PanelButoaneProfesor()
	{
		setLayout(null);	
		
		buttonDatePersonale.setBounds(51, 65, 181, 20);
		add(buttonDatePersonale);
		
		buttonVizualizareActivitatiStudiu.setBounds(51, 95, 181, 20);
		add(buttonVizualizareActivitatiStudiu);
		
		buttonProgramareCalendar.setBounds(51, 126, 181, 20);	
		add(buttonProgramareCalendar);
		
		buttonPonderiNote.setBounds(51, 156, 181, 20);
		add(buttonPonderiNote);
		
		buttonCatalog.setBounds(51, 186, 181, 20);
		add(buttonCatalog);
		
		buttonOrar.setBounds(51, 216, 181, 20);
		add(buttonOrar);
		
		this.setBorder(new LineBorder(Color.BLACK, 1));
	}
}
