package databaseView_PanelStudent;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import databaseModel.StudentSqlQueries;
import databaseModel.TreatException;
import main.MainClass;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;

@SuppressWarnings("serial")
public class PanelCalendar extends JPanel {
	private JTextField textField_idProgramare;
	private JLabel lblMaterie;
	public JButton btnInscriere = new JButton("Inscriere");
	public JButton btnGenerare = new JButton("Inscriere Automata");
	public JButton btnRenunta = new JButton("Anuleaza inscriere");
	public JButton btnRenuntaTotal = new JButton("Anuleaza toate inscrierile");
	public JTable tableCalendar = new JTable();
	public JTable tableOreDisponibile = new JTable();
	
	private JScrollPane jsp = new JScrollPane(tableCalendar);
	
	public String cnp;
	private final JScrollPane jsp_1 = new JScrollPane((Component) null);
	
	/**
	 * Create the panel.
	 */
	public PanelCalendar() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 225);
		add(jsp);
		
		textField_idProgramare = new JTextField();
		textField_idProgramare.setBounds(1068, 157, 112, 19);
		add(textField_idProgramare);
		textField_idProgramare.setColumns(10);
		
		lblMaterie = new JLabel("ID Activitate");
		lblMaterie.setBounds(987, 160, 71, 13);
		add(lblMaterie);
		
		setActionListeners();
		
		btnInscriere.setBounds(971, 225, 220, 21);
		add(btnInscriere);

		btnGenerare.setBounds(971, 250, 220, 21);
		add(btnGenerare);
		
		btnRenunta.setBounds(971, 275, 220, 21);
		add(btnRenunta);
		jsp_1.setBounds(2, 225, 959, 225);
		
		add(jsp_1);
		
		jsp_1.setViewportView(tableOreDisponibile);
		
		btnRenuntaTotal.setBounds(971, 301, 220, 21);
		add(btnRenuntaTotal);
		
		tableOreDisponibile.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
            	tableOreDisponibileMouseClicked(evt);
            }
        });
		
		tableCalendar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
            	tableCalendarMouseClicked(evt);
            }
        });
	}

	public void setActionListeners()
	{
		btnInscriere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.inscriere_calendar(MainClass.db.getCon(), textField_idProgramare.getText());
					setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		
		btnGenerare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.generare_calendar(MainClass.db.getCon());
					setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		btnRenunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.renuntare_calendar_activitate(MainClass.db.getCon(), textField_idProgramare.getText());
					setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		btnRenuntaTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.renuntare_calendar(MainClass.db.getCon());
					setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
	}
	
	public void setTable(JTable table, ArrayList<ArrayList<String>> a)
	{
		DefaultTableModel dtm = new DefaultTableModel();
		if(a.isEmpty() == false)
			dtm.setColumnCount(a.get(0).size());
		int i = 0, j = 0;
		for(ArrayList<String> arow : a)
		{
			dtm.setRowCount(dtm.getRowCount() + 1);
			j = 0;
			for(String s : arow)
			{		
				dtm.setValueAt(s, i, j);
				j++;
			}
			i++;
		}	
		table.setModel(dtm);
		repaint();
	}
	
	public void setData()
	{
		textField_idProgramare.setText(null);
		try 
		{
			setTable(tableOreDisponibile, StudentSqlQueries.vizualizare_ore_disponibile(MainClass.db.getCon()));
			setTable(tableCalendar, StudentSqlQueries.vizualizare_calendar(MainClass.db.getCon()));
		} 
		catch (SQLException e1) { TreatException.printSQLException(e1); }
	}
	
    private void tableOreDisponibileMouseClicked(MouseEvent evt) {
        String id_calendar = tableOreDisponibile.getValueAt(tableOreDisponibile.getSelectedRow(), 0).toString();
        textField_idProgramare.setText(id_calendar);
    }
    
    private void tableCalendarMouseClicked(MouseEvent evt) {
    	String id_calendar = tableCalendar.getValueAt(tableCalendar.getSelectedRow(), 0).toString();
        textField_idProgramare.setText(id_calendar);
    }
}
