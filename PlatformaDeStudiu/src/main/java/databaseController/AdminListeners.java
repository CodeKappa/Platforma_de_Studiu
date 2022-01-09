package databaseController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import databaseModel.AdminSqlQueries;
//import databaseModel.DatabaseModel;
import databaseModel.PersoaneSqlQueries;
import databaseModel.TreatException;
import databaseView.DatabaseView;
import main.MainClass;

public class AdminListeners{

	private DatabaseView theView;
	//private DatabaseModel theModel;
	
	//AdminListeners(DatabaseView v, DatabaseModel m)
	AdminListeners(DatabaseView v)
	{
		this.theView = v;
		//this.theModel = m;
		setActionListeners();
	}
	
    public void setActionListeners()
    {
    	theView.panelAdminView.inputPanel.buttonDatePersonale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{			
					theView.panelAdminView.datePersonale.setData(PersoaneSqlQueries.date_personale(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelAdminView.inputPanel.buttonCRUDuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelAdminView.crudUser.setData(null);
					theView.panelAdminView.crudUser.setTable(AdminSqlQueries.all_user_data(MainClass.db.getCon()));
				}
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelAdminView.inputPanel.buttonCRUDmaterii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelAdminView.crudMaterii.setData(null);
					theView.panelAdminView.crudMaterii.setTable(AdminSqlQueries.all_materie_data(MainClass.db.getCon()));
				}
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelAdminView.inputPanel.buttonCRUDgrupuri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelAdminView.crudGrupuri.setData(null);
					theView.panelAdminView.crudGrupuri.setTable(AdminSqlQueries.all_grup_data(MainClass.db.getCon()));
				}
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelAdminView.inputPanel.buttonCautaUtilizatori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelAdminView.cautaUtilizatori.setData();
			}
		});
    	theView.panelAdminView.inputPanel.buttonCautaMaterie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelAdminView.cautaMaterii.setData();
			}
		});
    	theView.panelAdminView.inputPanel.buttonAsignareProfesori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelAdminView.asignareProfesori.setData();
			}
		});
		theView.panelAdminView.inputPanel.buttonAdaugaActivitatiGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelAdminView.adaugaActivitati.setData();
			}
		});
		
		
		theView.panelSuperAdminView.inputPanel.buttonDatePersonale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{			
					theView.panelSuperAdminView.datePersonale.setData(PersoaneSqlQueries.date_personale(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelSuperAdminView.inputPanel.buttonCRUDuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelSuperAdminView.crudUser.setData(null);
					theView.panelSuperAdminView.crudUser.setTable(AdminSqlQueries.all_user_data(MainClass.db.getCon()));
				}
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelSuperAdminView.inputPanel.buttonCRUDmaterii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelSuperAdminView.crudMaterii.setData(null);
					theView.panelSuperAdminView.crudMaterii.setTable(AdminSqlQueries.all_materie_data(MainClass.db.getCon()));
				}
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelSuperAdminView.inputPanel.buttonCRUDgrupuri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelSuperAdminView.crudGrupuri.setData(null);
					theView.panelSuperAdminView.crudGrupuri.setTable(AdminSqlQueries.all_grup_data(MainClass.db.getCon()));
				}
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelSuperAdminView.inputPanel.buttonCautaUtilizatori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelSuperAdminView.cautaUtilizatori.setData();
			}
		});
    	theView.panelSuperAdminView.inputPanel.buttonCautaMaterie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelSuperAdminView.cautaMaterii.setData();
			}
		});
    	theView.panelSuperAdminView.inputPanel.buttonAsignareProfesori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelSuperAdminView.asignareProfesori.setData();
			}
		});
		theView.panelSuperAdminView.inputPanel.buttonAdaugaActivitatiGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelSuperAdminView.adaugaActivitati.setData();
			}
		});
    }
}
