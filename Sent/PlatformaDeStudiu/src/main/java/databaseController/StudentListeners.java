package databaseController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import databaseModel.AdminSqlQueries;
//import databaseModel.DatabaseModel;
import databaseModel.PersoaneSqlQueries;
import databaseModel.StudentSqlQueries;
import databaseModel.TreatException;
import databaseView.DatabaseView;
import main.MainClass;

public class StudentListeners{

	private DatabaseView theView;
	//private DatabaseModel theModel;
	
	//StudentListeners(DatabaseView v, DatabaseModel m)
	StudentListeners(DatabaseView v)
	{
		this.theView = v;
		//this.theModel = m;
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
    	
    	
    	
    	
    	theView.panelStudentView.mesaje.btnTrimite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.trimite_mesaj(MainClass.db.getCon(),theView.panelStudentView.mesaje.textField_mesaj.getText(),theView.panelStudentView.mesaje.textField_grup.getText(), theView.panelStudentView.mesaje.cnp);
					theView.panelStudentView.mesaje.setTable(StudentSqlQueries.preia_mesaje(MainClass.db.getCon(), theView.panelStudentView.mesaje.textField_grup.getText()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	
    	theView.panelStudentView.mesaje.btnMesaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelStudentView.mesaje.setTable(StudentSqlQueries.preia_mesaje(MainClass.db.getCon(), theView.panelStudentView.mesaje.textField_grup.getText()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	
    	theView.panelStudentView.materii.btnCautaMaterieDupaNume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelStudentView.materii.setTable(theView.panelStudentView.materii.tableMaterii, AdminSqlQueries.cauta_materie(MainClass.db.getCon(), theView.panelStudentView.materii.textField_materie.getText()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelStudentView.materii.btnInscriereMaterie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.inscriere_materie(MainClass.db.getCon(), theView.panelStudentView.materii.textField_materie.getText(), theView.panelStudentView.materii.cnp);
					theView.panelStudentView.materii.setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelStudentView.materii.btnRenuntaLaMaterie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.renuntare_materie(MainClass.db.getCon(), theView.panelStudentView.materii.textField_id.getText(), theView.panelStudentView.materii.cnp);
					theView.panelStudentView.materii.setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	
    	theView.panelStudentView.materii.tableMaterii.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String id = theView.panelStudentView.materii.tableMaterii.getValueAt(theView.panelStudentView.materii.tableMaterii.getSelectedRow(), 0).toString();
                String nume = theView.panelStudentView.materii.tableMaterii.getValueAt(theView.panelStudentView.materii.tableMaterii.getSelectedRow(), 1).toString();
                theView.panelStudentView.materii.textField_materie.setText(nume);
                theView.panelStudentView.materii.textField_id.setText(id);
            }
        });
		
    	theView.panelStudentView.materii.tableInscrise.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String id = theView.panelStudentView.materii.tableInscrise.getValueAt(theView.panelStudentView.materii.tableInscrise.getSelectedRow(), 0).toString();
                String nume = theView.panelStudentView.materii.tableInscrise.getValueAt(theView.panelStudentView.materii.tableInscrise.getSelectedRow(), 1).toString();
                theView.panelStudentView.materii.textField_materie.setText(nume);
                theView.panelStudentView.materii.textField_id.setText(id);
            }
        });
    	
    	
    	theView.panelStudentView.inputPanel.buttonDatePersonale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelStudentView.frontPanel.setVisible(false);
				theView.panelStudentView.datePersonale.setVisible(true);	
				theView.panelStudentView.frontPanel = theView.panelStudentView.datePersonale;
			}
		});
    	theView.panelStudentView.inputPanel.buttonMaterii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelStudentView.frontPanel.setVisible(false);
				theView.panelStudentView.materii.setVisible(true);
				theView.panelStudentView.frontPanel = theView.panelStudentView.materii;
			}
		});
    	theView.panelStudentView.inputPanel.buttonGrupuri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelStudentView.frontPanel.setVisible(false);
				theView.panelStudentView.grupuri.setVisible(true);	
				theView.panelStudentView.frontPanel = theView.panelStudentView.grupuri;
			}
		});
    	theView.panelStudentView.inputPanel.buttonMesajeGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelStudentView.frontPanel.setVisible(false);
				theView.panelStudentView.mesaje.setVisible(true);
				theView.panelStudentView.frontPanel = theView.panelStudentView.mesaje;
			}
		});
    	theView.panelStudentView.inputPanel.buttonActivitatiGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelStudentView.frontPanel.setVisible(false);
				theView.panelStudentView.activitati.setVisible(true);
				theView.panelStudentView.frontPanel = theView.panelStudentView.activitati;
			}
		});
    	theView.panelStudentView.inputPanel.buttonCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelStudentView.frontPanel.setVisible(false);
				theView.panelStudentView.calendar.setVisible(true);
				theView.panelStudentView.frontPanel = theView.panelStudentView.calendar;
			}
		});
    	theView.panelStudentView.inputPanel.btnCreazaActivitati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelStudentView.frontPanel.setVisible(false);
				theView.panelStudentView.creazaActivitati.setVisible(true);
				theView.panelStudentView.frontPanel = theView.panelStudentView.creazaActivitati;
			}
		});
    	
    	
    	theView.panelStudentView.calendar.tableOreDisponibile.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String id_calendar = theView.panelStudentView.calendar.tableOreDisponibile.getValueAt(theView.panelStudentView.calendar.tableOreDisponibile.getSelectedRow(), 0).toString();
                theView.panelStudentView.calendar.textField_idProgramare.setText(id_calendar);
            }
        });
		
    	theView.panelStudentView.calendar.tableCalendar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
            	String id_calendar = theView.panelStudentView.calendar.tableCalendar.getValueAt(theView.panelStudentView.calendar.tableCalendar.getSelectedRow(), 0).toString();
            	theView.panelStudentView.calendar.textField_idProgramare.setText(id_calendar);
            }
        });
    	
    	theView.panelStudentView.calendar.btnInscriere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.inscriere_calendar(MainClass.db.getCon(), theView.panelStudentView.calendar.textField_idProgramare.getText());
					theView.panelStudentView.calendar.setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		
    	theView.panelStudentView.calendar.btnGenerare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.generare_calendar(MainClass.db.getCon());
					theView.panelStudentView.calendar.setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelStudentView.calendar.btnRenunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.renuntare_calendar_activitate(MainClass.db.getCon(), theView.panelStudentView.calendar.textField_idProgramare.getText());
					theView.panelStudentView.calendar.setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelStudentView.calendar.btnRenuntaTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.renuntare_calendar(MainClass.db.getCon());
					theView.panelStudentView.calendar.setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	
    	
    	theView.panelStudentView.activitati.btnActivitatiGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelStudentView.activitati.setTable(StudentSqlQueries.vezi_activitati_grupuri(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelStudentView.activitati.btnInscrieActivitate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.inscriere_activitati_grup(MainClass.db.getCon(), theView.panelStudentView.activitati.textField_grup.getText(), DatabaseController.user);
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelStudentView.activitati.btnDescarcaActivitati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.descarca_activitati_grup(MainClass.db.getCon(), DatabaseController.user);
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		
    	theView.panelStudentView.activitati.tableAfis.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String id = theView.panelStudentView.activitati.tableAfis.getValueAt(theView.panelStudentView.activitati.tableAfis.getSelectedRow(), 0).toString();
                theView.panelStudentView.activitati.textField_grup.setText(id);
            }
        });
    	
    	
    	
    	theView.panelStudentView.grupuri.tableAfis.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String id = theView.panelStudentView.grupuri.tableAfis.getValueAt(theView.panelStudentView.grupuri.tableAfis.getSelectedRow(), 0).toString();
                String id_gr = theView.panelStudentView.grupuri.tableAfis.getValueAt(theView.panelStudentView.grupuri.tableAfis.getSelectedRow(), 1).toString();
                theView.panelStudentView.grupuri.textField_idMaterie.setText(id_gr);
                theView.panelStudentView.grupuri.textField_id.setText(id);
            }
        });
    	theView.panelStudentView.grupuri.btnVeziGrupuri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelStudentView.grupuri.setTable(StudentSqlQueries.vezi_grupuri(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelStudentView.grupuri.btnMembriGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelStudentView.grupuri.setTable(StudentSqlQueries.membri_grup(MainClass.db.getCon(), theView.panelStudentView.grupuri.textField_id.getText(), theView.panelStudentView.grupuri.cnp));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelStudentView.grupuri.btnInscriereGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.inscriere_grup(MainClass.db.getCon(), theView.panelStudentView.grupuri.textField_id.getText(), theView.panelStudentView.grupuri.cnp);
					theView.panelStudentView.grupuri.setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelStudentView.grupuri.btnRenuntaLaGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.renuntare_grup(MainClass.db.getCon(), theView.panelStudentView.grupuri.textField_id.getText(), theView.panelStudentView.grupuri.cnp);
					theView.panelStudentView.grupuri.setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelStudentView.grupuri.btnSugestiiGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelStudentView.grupuri.setTable(StudentSqlQueries.sugestii_grup(MainClass.db.getCon(), theView.panelStudentView.grupuri.cnp));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	
    	
    	theView.panelStudentView.creazaActivitati.btnCreazaActivitate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.creaza_activitate(MainClass.db.getCon(), theView.panelStudentView.creazaActivitati.getData());
					theView.panelStudentView.creazaActivitati.setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	
    	theView.panelStudentView.creazaActivitati.btnSugereazaFerestre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelStudentView.creazaActivitati.setTable(StudentSqlQueries.vizualizare_ferestre(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    }
}
