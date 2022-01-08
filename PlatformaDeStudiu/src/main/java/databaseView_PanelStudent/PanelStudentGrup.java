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
public class PanelStudentGrup extends JPanel {
	private JTextField textField_idMaterie;
	private JLabel lbl_idMat;
	public JButton btnVeziGrupuri = new JButton("Vezi Grupuri");
	public JButton btnMembriGrup = new JButton("Vezi Membri Grup");
	public JButton btnInscriereGrup = new JButton("Inscriete La Grup");
	public JButton btnRenuntaLaGrup = new JButton("Renunta La Grup");
	
	private JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);
	
	public String cnp;
	private JTextField textField_id;
	private final JButton btnSugestiiGrup = new JButton("Sugestii Grup");
	
	/**
	 * Create the panel.
	 */
	public PanelStudentGrup() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 447);
		add(jsp);
		
		textField_idMaterie = new JTextField();
		textField_idMaterie.setBounds(1068, 157, 112, 19);
		add(textField_idMaterie);
		textField_idMaterie.setColumns(10);
		
		lbl_idMat = new JLabel("ID Materie");
		lbl_idMat.setBounds(987, 160, 71, 13);
		add(lbl_idMat);
		
		btnVeziGrupuri.setBounds(971, 200, 220, 21);
		add(btnVeziGrupuri);
		
		setActionListeners();
		
		btnMembriGrup.setBounds(971, 225, 220, 21);
		add(btnMembriGrup);

		btnInscriereGrup.setBounds(971, 250, 220, 21);
		add(btnInscriereGrup);
		
		btnRenuntaLaGrup.setBounds(971, 275, 220, 21);
		add(btnRenuntaLaGrup);
		
		textField_id = new JTextField();
		textField_id.setColumns(10);
		textField_id.setBounds(1068, 128, 112, 19);
		add(textField_id);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(987, 131, 45, 13);
		add(lblId);
		btnSugestiiGrup.setBounds(971, 300, 220, 21);
		
		add(btnSugestiiGrup);
		
        tableAfis.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tableAfisMouseClicked(evt);
            }
        });
	}

	public void setActionListeners()
	{	
		btnVeziGrupuri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					setTable(StudentSqlQueries.vezi_grupuri(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		btnMembriGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					setTable(StudentSqlQueries.membri_grup(MainClass.db.getCon(), textField_id.getText(), cnp));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		btnInscriereGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.inscriere_grup(MainClass.db.getCon(), textField_id.getText(), cnp);
					setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		btnRenuntaLaGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.renuntare_grup(MainClass.db.getCon(), textField_id.getText(), cnp);
					setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		btnSugestiiGrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					setTable(StudentSqlQueries.sugestii_grup(MainClass.db.getCon(), cnp));
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
		textField_idMaterie.setText(null);
		try 
		{
			setTable(StudentSqlQueries.vezi_grupuri(MainClass.db.getCon()));
		} 
		catch (SQLException e1) { TreatException.printSQLException(e1); }
	}
	
    private void tableAfisMouseClicked(MouseEvent evt) {
        String id = tableAfis.getValueAt(tableAfis.getSelectedRow(), 0).toString();
        String id_gr = tableAfis.getValueAt(tableAfis.getSelectedRow(), 1).toString();
        textField_idMaterie.setText(id_gr);
        textField_id.setText(id);
    }
}
