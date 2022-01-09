package databaseController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashMap;

import databaseModel.AdminSqlQueries;
//import databaseModel.DatabaseModel;
import databaseModel.PersoaneSqlQueries;
import databaseModel.ProfesorSqlQueries;
import databaseModel.TreatException;
import databaseView.DatabaseView;
import main.MainClass;

public class ProfesorListeners{

	private DatabaseView theView;
	//private DatabaseModel theModel;
	
	//ProfesorListeners(DatabaseView v, DatabaseModel m)
	ProfesorListeners(DatabaseView v)
	{
		this.theView = v;
		//this.theModel = m;
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
					theView.panelProfesorView.calendarPanel.setTable(theView.panelProfesorView.calendarPanel.tableCalendar, PersoaneSqlQueries.vizualizare_calendar(MainClass.db.getCon(), false));
					theView.panelProfesorView.calendarPanel.setTable(theView.panelProfesorView.calendarPanel.tableMaterii, ProfesorSqlQueries.vizualizare_materii(MainClass.db.getCon()));
				}
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	theView.panelProfesorView.inputPanel.buttonCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					theView.panelProfesorView.catalogPanel.setData(null);
					theView.panelProfesorView.catalogPanel.setTable(theView.panelProfesorView.catalogPanel.tableCatalog, ProfesorSqlQueries.vizualizare_studenti_note(MainClass.db.getCon()));
					theView.panelProfesorView.catalogPanel.setTable(theView.panelProfesorView.catalogPanel.tableStudenti, ProfesorSqlQueries.vizualizare_studenti_inscrisi(MainClass.db.getCon()));
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
    	
    	
    	theView.panelProfesorView.inputPanel.buttonCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelProfesorView.frontPanel.setVisible(false);
				theView.panelProfesorView.catalogPanel.setVisible(true);	
				theView.panelProfesorView.frontPanel = theView.panelProfesorView.catalogPanel;
			}
		});
    	theView.panelProfesorView.inputPanel.buttonDatePersonale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelProfesorView.frontPanel.setVisible(false);
				theView.panelProfesorView.datePersonale.setVisible(true);	
				theView.panelProfesorView.frontPanel = theView.panelProfesorView.datePersonale;
			}
		});
    	theView.panelProfesorView.inputPanel.buttonPonderiNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelProfesorView.frontPanel.setVisible(false);
				theView.panelProfesorView.ponderiPanel.setVisible(true);	
				theView.panelProfesorView.frontPanel = theView.panelProfesorView.ponderiPanel;
			}
		});
    	theView.panelProfesorView.inputPanel.buttonCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				theView.panelProfesorView.frontPanel.setVisible(false);
				theView.panelProfesorView.calendarPanel.setVisible(true);	
				theView.panelProfesorView.frontPanel = theView.panelProfesorView.calendarPanel;
				
			}
		});
    	theView.panelProfesorView.inputPanel.buttonVizualizareActivitatiStudiu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theView.panelProfesorView.frontPanel.setVisible(false);
				theView.panelProfesorView.activitatiPanel.setVisible(true);	
				theView.panelProfesorView.frontPanel = theView.panelProfesorView.activitatiPanel;
			}
		});
    	
    	
    	theView.panelProfesorView.ponderiPanel.btnActualizare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					ProfesorSqlQueries.actualizare_ponderi(MainClass.db.getCon(), theView.panelProfesorView.ponderiPanel.getData());
					theView.panelProfesorView.ponderiPanel.setTable(ProfesorSqlQueries.vizualizare_ponderi(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	
    	theView.panelProfesorView.ponderiPanel.tableAfis.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
            	HashMap<String, String> dataMap = new HashMap<String, String>();
            	dataMap.put("id_materie", theView.panelProfesorView.ponderiPanel.tableAfis.getValueAt(theView.panelProfesorView.ponderiPanel.tableAfis.getSelectedRow(), 1).toString());
            	dataMap.put("procent_curs", theView.panelProfesorView.ponderiPanel.tableAfis.getValueAt(theView.panelProfesorView.ponderiPanel.tableAfis.getSelectedRow(), 2).toString());
            	dataMap.put("procent_seminar", theView.panelProfesorView.ponderiPanel.tableAfis.getValueAt(theView.panelProfesorView.ponderiPanel.tableAfis.getSelectedRow(), 3).toString());
            	dataMap.put("procent_laborator", theView.panelProfesorView.ponderiPanel.tableAfis.getValueAt(theView.panelProfesorView.ponderiPanel.tableAfis.getSelectedRow(), 4).toString());
            	theView.panelProfesorView.ponderiPanel.setData(dataMap);
            }
		});
    	
    	theView.panelProfesorView.catalogPanel.tableStudenti.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
            	HashMap<String, String> dataMap = new HashMap<String, String>();
            	dataMap.put("id_materie", theView.panelProfesorView.catalogPanel.tableStudenti.getValueAt(theView.panelProfesorView.catalogPanel.tableStudenti.getSelectedRow(), 0).toString());
            	dataMap.put("cnp", theView.panelProfesorView.catalogPanel.tableStudenti.getValueAt(theView.panelProfesorView.catalogPanel.tableStudenti.getSelectedRow(), 4).toString());
            	theView.panelProfesorView.catalogPanel.setData(dataMap);
            }
		});
		
    	theView.panelProfesorView.catalogPanel.btnNotareCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					ProfesorSqlQueries.notare_studenti(MainClass.db.getCon(), theView.panelProfesorView.catalogPanel.getData()); //TODO
					theView.panelProfesorView.catalogPanel.setTable(theView.panelProfesorView.catalogPanel.tableCatalog, ProfesorSqlQueries.vizualizare_studenti_note(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		

    	theView.panelProfesorView.catalogPanel.btnDescarcareCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					ProfesorSqlQueries.descarcare_studenti(MainClass.db.getCon());
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	
    	theView.panelProfesorView.calendarPanel.tableMaterii.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
            	HashMap<String, String> dataMap = new HashMap<String, String>();
            	dataMap.put("id_materie", theView.panelProfesorView.calendarPanel.tableMaterii.getValueAt(theView.panelProfesorView.calendarPanel.tableMaterii.getSelectedRow(), 0).toString());
            	dataMap.put("nr_max_elevi", theView.panelProfesorView.calendarPanel.tableMaterii.getValueAt(theView.panelProfesorView.calendarPanel.tableMaterii.getSelectedRow(), 3).toString());
            	theView.panelProfesorView.calendarPanel.setData(dataMap);
            }
		});
		
    	theView.panelProfesorView.calendarPanel.btnProgramareCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					ProfesorSqlQueries.programare_calendar(MainClass.db.getCon(), theView.panelProfesorView.calendarPanel.getData()); //TODO
					theView.panelProfesorView.calendarPanel.setTable(theView.panelProfesorView.calendarPanel.tableCalendar, PersoaneSqlQueries.vizualizare_calendar(MainClass.db.getCon(), false));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		

    	theView.panelProfesorView.calendarPanel.btnDescarcareCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					ProfesorSqlQueries.descarcare_calendar(MainClass.db.getCon(), false);
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    	
    	theView.panelProfesorView.activitatiPanel.tableAfis.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String id = theView.panelProfesorView.activitatiPanel.tableAfis.getValueAt(theView.panelProfesorView.activitatiPanel.tableAfis.getSelectedRow(), 0).toString();
                try 
                {
                	theView.panelProfesorView.activitatiPanel.setData(AdminSqlQueries.read_grup(MainClass.db.getCon(), id));
        		} 
                catch (SQLException e) { TreatException.printSQLException(e); }
            }
        });	
        
    	theView.panelProfesorView.activitatiPanel.btnInscriere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					ProfesorSqlQueries.inscriere_activitati_studiu(MainClass.db.getCon(), theView.panelProfesorView.activitatiPanel.getData());
					theView.panelProfesorView.activitatiPanel.setTable(ProfesorSqlQueries.vizualizare_activitati_studiu(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
    }
}
