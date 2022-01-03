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

public class AdminListeners{

	private DatabaseView theView;
	private DatabaseModel theModel;
	
	AdminListeners(DatabaseView v, DatabaseModel m)
	{
		this.theView = v;
		this.theModel = m;
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
					theView.panelAdminView.crudUser.setData(AdminSqlQueries.read_user(MainClass.db.getCon()));
				}
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelAdminView.inputPanel.buttonCRUDmaterii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
    	theView.panelAdminView.inputPanel.buttonCRUDgrupuri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
    	theView.panelAdminView.inputPanel.buttonCautaUtilizatori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
    	theView.panelAdminView.inputPanel.buttonCRUDmaterii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
    	theView.panelAdminView.inputPanel.buttonAsignareProfesori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		theView.panelAdminView.inputPanel.buttonAdaugaActivitatiGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
    }
}
