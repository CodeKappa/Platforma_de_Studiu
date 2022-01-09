package databaseView_PanelStudent;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import databaseController.DatabaseController;
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

@SuppressWarnings("serial")
public class PanelActivitatiGrup extends JPanel {
	private JTextField textField_grup;
	private JLabel lblGrup;
	public JButton btnActivitatiGrup = new JButton("Activitati Grupuri");
	public JButton btnInscrieActivitate = new JButton("Inscriere activitate");
	public JButton btnDescarcaActivitati = new JButton("Descarca Activitati");
	
	private JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);
	
	/**
	 * Create the panel.
	 */
	public PanelActivitatiGrup() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 447);
		add(jsp);
		
		textField_grup = new JTextField();
		textField_grup.setBounds(1068, 157, 112, 19);
		add(textField_grup);
		textField_grup.setColumns(10);
		
		lblGrup = new JLabel("Grup");
		lblGrup.setBounds(987, 160, 45, 13);
		add(lblGrup);
		
		btnActivitatiGrup.setBounds(971, 200, 220, 21);
		add(btnActivitatiGrup);
		
		setActionListeners();
		
		btnInscrieActivitate.setBounds(971, 225, 220, 21);
		add(btnInscrieActivitate);

		btnDescarcaActivitati.setBounds(971, 250, 220, 21);
		add(btnDescarcaActivitati);
		
        tableAfis.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tableAfisMouseClicked(evt);
            }
        });
	}

	public void setActionListeners()
	{	
		btnActivitatiGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					setTable(StudentSqlQueries.vezi_activitati_grupuri(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		btnInscrieActivitate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.inscriere_activitati_grup(MainClass.db.getCon(), textField_grup.getText(), DatabaseController.user);
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		btnDescarcaActivitati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.descarca_activitati_grup(MainClass.db.getCon(), DatabaseController.user);
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
	}
	
	public void setTable(ArrayList<ArrayList<String>> a)
	{
		if (a == null) return;
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
		tableAfis.setModel(dtm);
		repaint();
	}
	
	public void setData()
	{
		textField_grup.setText(null);
		try 
		{
			setTable(StudentSqlQueries.vezi_grupuri(MainClass.db.getCon()));
		} 
		catch (SQLException e1) { TreatException.printSQLException(e1); }
	}
	
    private void tableAfisMouseClicked(MouseEvent evt) {
        String id = tableAfis.getValueAt(tableAfis.getSelectedRow(), 0).toString();
        textField_grup.setText(id);
    }
}
