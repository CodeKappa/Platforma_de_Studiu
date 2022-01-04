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
public class PanelStudentMaterii extends JPanel {
	private JTextField textField_materie;
	private JLabel lblMaterie;
	public JButton btnMateriiPropri = new JButton("Materiile Tale");
	public JButton btnCautaMaterieDupaNume = new JButton("Cauta Materie Dupa Nume");
	public JButton btnInscriereMaterie = new JButton("Inscriete La Materie (nume)");
	public JButton btnRenuntaLaMaterie = new JButton("Renunta La Materie (id)");
	
	private JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);
	
	public String cnp;
	private JTextField textField_id;
	
	/**
	 * Create the panel.
	 */
	public PanelStudentMaterii() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 447);
		add(jsp);
		
		textField_materie = new JTextField();
		textField_materie.setBounds(1068, 157, 112, 19);
		add(textField_materie);
		textField_materie.setColumns(10);
		
		lblMaterie = new JLabel("Materie");
		lblMaterie.setBounds(987, 160, 45, 13);
		add(lblMaterie);
		
		btnMateriiPropri.setBounds(971, 200, 220, 21);
		add(btnMateriiPropri);
		
		setActionListeners();
		
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
		
        tableAfis.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tableAfisMouseClicked(evt);
            }
        });
	}

	public void setActionListeners()
	{	
		btnMateriiPropri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					setTable(StudentSqlQueries.materii_propri(MainClass.db.getCon(),cnp));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		btnCautaMaterieDupaNume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					setTable(AdminSqlQueries.cauta_materie(MainClass.db.getCon(), textField_materie.getText()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		btnInscriereMaterie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					setTable(StudentSqlQueries.inscriere_materie(MainClass.db.getCon(), textField_materie.getText(), cnp));
					setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		btnRenuntaLaMaterie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					setTable(StudentSqlQueries.renuntare_materie(MainClass.db.getCon(), textField_id.getText(), cnp));
					setData();
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
		textField_materie.setText(null);
		try 
		{
			setTable(AdminSqlQueries.all_materie_data(MainClass.db.getCon()));
		} 
		catch (SQLException e1) { TreatException.printSQLException(e1); }
	}
	
    private void tableAfisMouseClicked(MouseEvent evt) {
        String id = tableAfis.getValueAt(tableAfis.getSelectedRow(), 0).toString();
        String nume = tableAfis.getValueAt(tableAfis.getSelectedRow(), 1).toString();
        textField_materie.setText(nume);
        textField_id.setText(id);
    }
}
