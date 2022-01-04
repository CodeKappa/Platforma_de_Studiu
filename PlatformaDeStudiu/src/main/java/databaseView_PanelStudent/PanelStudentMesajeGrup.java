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
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PanelStudentMesajeGrup extends JPanel {
	private JTextField textField_mesaj;
	private JLabel lblMesaj;
	public JButton btnTrimite = new JButton("Trimite");
	public JButton btnMesaje = new JButton("Mesaje Grup");
	
	private JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);
	private final JTextField textField_grup = new JTextField();
	private final JLabel lblVeziGrup = new JLabel("Vezi Mesaje Grup");
	
	public String cnp;
	/**
	 * Create the panel.
	 */
	public PanelStudentMesajeGrup() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 447);
		add(jsp);
		
		textField_mesaj = new JTextField();
		textField_mesaj.setBounds(1068, 157, 112, 19);
		add(textField_mesaj);
		textField_mesaj.setColumns(10);
		
		lblMesaj = new JLabel("Mesaj");
		lblMesaj.setBounds(987, 160, 51, 13);
		add(lblMesaj);
		
		btnTrimite.setBounds(971, 186, 220, 21);
		add(btnTrimite);
		
		setActionListeners();
		
		btnMesaje.setBounds(971, 251, 220, 21);
		add(btnMesaje);
		textField_grup.setColumns(10);
		textField_grup.setBounds(1068, 222, 112, 19);
		
		add(textField_grup);
		lblVeziGrup.setBounds(987, 225, 71, 13);
		
		add(lblVeziGrup);
	}

	public void setActionListeners()
	{	
		btnTrimite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					StudentSqlQueries.trimite_mesaj(MainClass.db.getCon(),textField_mesaj.getText(),textField_grup.getText(), cnp);
					setTable(StudentSqlQueries.preia_mesaje(MainClass.db.getCon(), textField_grup.getText()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		btnMesaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					setTable(StudentSqlQueries.preia_mesaje(MainClass.db.getCon(), textField_grup.getText()));
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
		textField_mesaj.setText(null);
		textField_grup.setText(null);
	}
}
