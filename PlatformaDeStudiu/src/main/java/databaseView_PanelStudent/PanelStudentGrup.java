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
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PanelStudentGrup extends JPanel {
	public JTextField textField_idMaterie;
	private JLabel lbl_idMat;
	public JButton btnVeziGrupuri = new JButton("Vezi Grupuri");
	public JButton btnMembriGrup = new JButton("Vezi Membri Grup");
	public JButton btnInscriereGrup = new JButton("Inscriete La Grup");
	public JButton btnRenuntaLaGrup = new JButton("Renunta La Grup");
	
	public JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);
	
	public String cnp;
	public JTextField textField_id;
	public final JButton btnSugestiiGrup = new JButton("Sugestii Grup");
	
	/**
	 * Create the panel.
	 */
	public PanelStudentGrup() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 447);
		add(jsp);
		
		textField_idMaterie = new JTextField();
		textField_idMaterie.setEditable(false);
		textField_idMaterie.setBounds(1068, 157, 112, 19);
		add(textField_idMaterie);
		textField_idMaterie.setColumns(10);
		
		lbl_idMat = new JLabel("Materie");
		lbl_idMat.setBounds(987, 160, 71, 13);
		add(lbl_idMat);
		
		btnVeziGrupuri.setBounds(971, 200, 220, 21);
		add(btnVeziGrupuri);
		
		btnMembriGrup.setBounds(971, 225, 220, 21);
		add(btnMembriGrup);

		btnInscriereGrup.setBounds(971, 250, 220, 21);
		add(btnInscriereGrup);
		
		btnRenuntaLaGrup.setBounds(971, 275, 220, 21);
		add(btnRenuntaLaGrup);
		
		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setColumns(10);
		textField_id.setBounds(1068, 128, 112, 19);
		add(textField_id);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(987, 131, 45, 13);
		add(lblId);
		btnSugestiiGrup.setBounds(971, 300, 220, 21);
		
		add(btnSugestiiGrup);
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
		textField_idMaterie.setText(null);
		try 
		{
			setTable(StudentSqlQueries.vezi_grupuri(MainClass.db.getCon()));
		} 
		catch (SQLException e1) { TreatException.printSQLException(e1); }
	}
}
