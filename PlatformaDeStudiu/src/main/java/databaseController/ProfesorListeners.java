package databaseController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import databaseModel.AdminSqlQueries;
import databaseModel.DatabaseModel;
import databaseModel.PersoaneSqlQueries;
import databaseModel.ProfesorSqlQueries;
import databaseModel.TreatException;
import databaseView.DatabaseView;
import main.MainClass;

public class ProfesorListeners{

	private DatabaseView theView;
	private DatabaseModel theModel;
	
	ProfesorListeners(DatabaseView v, DatabaseModel m)
	{
		this.theView = v;
		this.theModel = m;
		setActionListeners();
	}
	
    public void setActionListeners()
    {
    	theView.panelProfesorView.inputPanel.buttonDatePersonale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{			
					theView.panelProfesorView.datePersonale.setData(ProfesorSqlQueries.date_personale(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelProfesorView.inputPanel.buttonVizualizareActivitatiStudiu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelProfesorView.activitatiPanel.setData(null);
					theView.panelProfesorView.activitatiPanel.setTable(ProfesorSqlQueries.vizualizare_activitati_studiu(MainClass.db.getCon()));
				}
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelProfesorView.inputPanel.buttonCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelProfesorView.calendarPanel.setData(null);
					theView.panelProfesorView.calendarPanel.setTable(PersoaneSqlQueries.vizualizare_calendar(MainClass.db.getCon(), false));
				}
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelProfesorView.inputPanel.buttonCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelProfesorView.catalogPanel.setTable(ProfesorSqlQueries.vizualizare_studenti(MainClass.db.getCon()));
				}
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelProfesorView.inputPanel.buttonPonderiNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelProfesorView.ponderiPanel.setTable(ProfesorSqlQueries.vizualizare_ponderi(MainClass.db.getCon()));
				}
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    }
}
