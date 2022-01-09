package databaseView_PanelStudent;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import databaseModel.AdminSqlQueries;
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
import java.awt.Component;

@SuppressWarnings("serial")
public class PanelStudentMaterii extends JPanel {
	public JTextField textField_materie;
	public JLabel lblMaterie;
	public JButton btnCautaMaterieDupaNume = new JButton("Cauta Materie Dupa Nume");
	public JButton btnInscriereMaterie = new JButton("Inscriete La Materie (ID)");
	public JButton btnRenuntaLaMaterie = new JButton("Renunta La Materie (ID)");
	public JTable tableInscrise = new JTable();
	public JTable tableMaterii = new JTable();
	
	private JScrollPane jsp = new JScrollPane(tableInscrise);
	
	public String cnp;
	public JTextField textField_id;
	public final JScrollPane jsp_1 = new JScrollPane((Component) null);
	
	/**
	 * Create the panel.
	 */
	public PanelStudentMaterii() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 225);
		add(jsp);
		
		textField_materie = new JTextField();
		textField_materie.setBounds(1068, 157, 112, 19);
		add(textField_materie);
		textField_materie.setColumns(10);
		
		lblMaterie = new JLabel("Materie");
		lblMaterie.setBounds(987, 160, 45, 13);
		add(lblMaterie);
		
		btnCautaMaterieDupaNume.setBounds(971, 225, 220, 21);
		add(btnCautaMaterieDupaNume);

		btnInscriereMaterie.setBounds(971, 250, 220, 21);
		add(btnInscriereMaterie);
		
		btnRenuntaLaMaterie.setBounds(971, 275, 220, 21);
		add(btnRenuntaLaMaterie);
		
		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setColumns(10);
		textField_id.setBounds(1068, 128, 112, 19);
		add(textField_id);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(987, 131, 45, 13);
		add(lblId);
		jsp_1.setBounds(2, 225, 959, 225);
		
		add(jsp_1);
		
		jsp_1.setViewportView(tableMaterii);
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
		textField_materie.setText(null);
		try 
		{
			setTable(tableMaterii, AdminSqlQueries.all_materie_data(MainClass.db.getCon()));
			setTable(tableInscrise, StudentSqlQueries.materii_proprii(MainClass.db.getCon()));
		} 
		catch (SQLException e1) { TreatException.printSQLException(e1); }
	}
}
