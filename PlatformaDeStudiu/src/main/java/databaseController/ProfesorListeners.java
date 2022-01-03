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
    	/*
    	theView.panelProfesorView.inputPanel.buttonCRUDuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelProfesorView.crudUser.setData(null);
					theView.panelProfesorView.crudUser.setTable(AdminSqlQueries.all_user_data(MainClass.db.getCon()));
				}
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelProfesorView.inputPanel.buttonCRUDmaterii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelProfesorView.crudMaterii.setData(null);
					theView.panelProfesorView.crudMaterii.setTable(AdminSqlQueries.all_materie_data(MainClass.db.getCon()));
				}
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelProfesorView.inputPanel.buttonCRUDgrupuri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelProfesorView.crudGrupuri.setData(null);
					theView.panelProfesorView.crudGrupuri.setTable(AdminSqlQueries.all_grup_data(MainClass.db.getCon()));
				}
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelProfesorView.inputPanel.buttonCautaUtilizatori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelProfesorView.cautaUtilizatori.setData();
			}
		});
    	theView.panelProfesorView.inputPanel.buttonCRUDmaterii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
    	theView.panelProfesorView.inputPanel.buttonAsignareProfesori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		theView.panelProfesorView.inputPanel.buttonAdaugaActivitatiGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		*/
    }
}
