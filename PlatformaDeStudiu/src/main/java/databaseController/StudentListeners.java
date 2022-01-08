package databaseController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import databaseModel.AdminSqlQueries;
import databaseModel.DatabaseModel;
import databaseModel.PersoaneSqlQueries;
import databaseModel.TreatException;
import databaseView.DatabaseView;
import main.MainClass;

public class StudentListeners{

	private DatabaseView theView;
	private DatabaseModel theModel;
	
	StudentListeners(DatabaseView v, DatabaseModel m)
	{
		this.theView = v;
		this.theModel = m;
		setActionListeners();
	}
	
    public void setActionListeners()
    {
    	theView.panelStudentView.inputPanel.buttonDatePersonale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{			
					theView.panelStudentView.datePersonale.setData(PersoaneSqlQueries.date_personale(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelStudentView.inputPanel.buttonMaterii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					theView.panelStudentView.materii.cnp = DatabaseController.user;
					theView.panelStudentView.materii.setData();		
			}
		});
    	theView.panelStudentView.inputPanel.buttonGrupuri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					theView.panelStudentView.grupuri.cnp = DatabaseController.user;
					theView.panelStudentView.grupuri.setData();		
			}
		});
    	theView.panelStudentView.inputPanel.buttonMesajeGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					theView.panelStudentView.mesaje.cnp = DatabaseController.user;
					theView.panelStudentView.mesaje.setData();		
			}
		});
    	theView.panelStudentView.inputPanel.buttonCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					theView.panelStudentView.calendar.setData();		
			}
		});
    }
}
