package databaseView_PanelAdmin;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import databaseModel.AdminSqlQueries;
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
public class PanelCRUDmaterii extends JPanel {
	private JTextField textField_id;
	private JTextField textField_nume;
	private JTextField textField_descriere;
	private JTextField textField_pcurs;
	private JTextField textField_psem;
	private JTextField textField_nrMax;
	private JLabel label_nume;
	private JLabel lblDescriere;
	private JLabel lblProcentCurs;
	private JLabel lblProcentSeminar;
	private JLabel lblNumarMax;
	private JLabel lblRecurentaLab;
	private JTextField textField_recLab;
	private JTextField textField_recSem;
	private JTextField textField_recCurs;
	public JButton btnCreate = new JButton("Create");
	public JButton btnUpdate = new JButton("Update");
	public JButton btnDelete = new JButton("Delete");
	
	private JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);
	private JTextField textField_plab;
	private JLabel lblProcentLab;

	
	/**
	 * Create the panel.
	 */
	public PanelCRUDmaterii() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 447);
		add(jsp);
		
		textField_id = new JTextField();
		textField_id.setBounds(1069, 10, 112, 19);
		add(textField_id);
		textField_id.setColumns(10);
		
		textField_descriere = new JTextField();
		textField_descriere.setBounds(1069, 68, 112, 19);
		add(textField_descriere);
		textField_descriere.setColumns(10);
		
		textField_nume = new JTextField();
		textField_nume.setBounds(1069, 39, 112, 19);
		add(textField_nume);
		textField_nume.setColumns(10);
		
		textField_pcurs = new JTextField();
		textField_pcurs.setBounds(1069, 97, 112, 19);
		add(textField_pcurs);
		textField_pcurs.setColumns(10);
		
		textField_psem = new JTextField();
		textField_psem.setBounds(1069, 129, 112, 19);
		add(textField_psem);
		textField_psem.setColumns(10);
		
		textField_nrMax = new JTextField();
		textField_nrMax.setBounds(1069, 187, 112, 19);
		add(textField_nrMax);
		textField_nrMax.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(971, 16, 33, 13);
		add(lblId);
		
		label_nume = new JLabel("Nume");
		label_nume.setBounds(971, 45, 45, 13);
		add(label_nume);
		
		lblDescriere = new JLabel("Descriere");
		lblDescriere.setBounds(971, 74, 57, 13);
		add(lblDescriere);
		
		lblProcentCurs = new JLabel("Procent Curs");
		lblProcentCurs.setBounds(971, 101, 83, 16);
		add(lblProcentCurs);
		
		lblProcentSeminar = new JLabel("Procent Seminar");
		lblProcentSeminar.setBounds(971, 135, 105, 13);
		add(lblProcentSeminar);
		
		lblNumarMax = new JLabel("Numar Max");
		lblNumarMax.setBounds(971, 193, 71, 13);
		add(lblNumarMax);
		
		lblRecurentaLab = new JLabel("Recurenta Lab");
		lblRecurentaLab.setBounds(971, 222, 83, 13);
		add(lblRecurentaLab);
		
		textField_recLab = new JTextField();
		textField_recLab.setBounds(1069, 216, 112, 19);
		add(textField_recLab);
		textField_recLab.setColumns(10);
		
		textField_recSem = new JTextField();
		textField_recSem.setBounds(1069, 245, 112, 19);
		add(textField_recSem);
		textField_recSem.setColumns(10);
		
		btnCreate.setBounds(966, 381, 220, 21);
		add(btnCreate);

		btnUpdate.setBounds(966, 412, 105, 21);
		add(btnUpdate);

		btnDelete.setBounds(1081, 412, 105, 21);
		add(btnDelete);
		
		setActionListeners();
		
		textField_plab = new JTextField();
		textField_plab.setColumns(10);
		textField_plab.setBounds(1069, 158, 112, 19);
		add(textField_plab);
		
		lblProcentLab = new JLabel("Procent Lab");
		lblProcentLab.setBounds(971, 167, 83, 13);
		add(lblProcentLab);
		
		JLabel lblRecurentaSem = new JLabel("Recurenta Sem");
		lblRecurentaSem.setBounds(971, 251, 95, 13);
		add(lblRecurentaSem);
		
		JLabel lblRecurentaCurs = new JLabel("Recurenta Curs");
		lblRecurentaCurs.setBounds(971, 280, 95, 13);
		add(lblRecurentaCurs);
		
		textField_recCurs = new JTextField();
		textField_recCurs.setColumns(10);
		textField_recCurs.setBounds(1069, 274, 112, 19);
		add(textField_recCurs);
		
        tableAfis.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tableAfisMouseClicked(evt);
            }
        });
		
	}
	
	public void setActionListeners()
	{
		
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					AdminSqlQueries.create_materie(MainClass.db.getCon(), getData());
					setTable(AdminSqlQueries.all_materie_data(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					AdminSqlQueries.update_materie(MainClass.db.getCon(), getData());
					setTable(AdminSqlQueries.all_materie_data(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					AdminSqlQueries.delete_materie(MainClass.db.getCon(), textField_id.getText());
					setTable(AdminSqlQueries.all_materie_data(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
	}
	
	public void setTable(ArrayList<ArrayList<String>> a)
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
		tableAfis.setModel(dtm);
		repaint();
	}
	
	public ArrayList<String> getData()
	{
		ArrayList<String> arr = new ArrayList<String>();
		
		arr.add(textField_id.getText());
		arr.add(textField_nume.getText());
		arr.add(textField_descriere.getText());
		arr.add(textField_pcurs.getText());
		arr.add(textField_psem.getText());
		arr.add(textField_plab.getText());
		arr.add(textField_nrMax.getText());
		arr.add(textField_recCurs.getText());
		arr.add(textField_recSem.getText());
		arr.add(textField_recLab.getText());
		
		return arr;
	}
	
	public void setData(ArrayList<String> arr)
	{
		if(arr == null)
		{
			textField_id.setText(null);
			textField_nume.setText(null);
			textField_descriere.setText(null);
			textField_pcurs.setText(null);
			textField_psem.setText(null);
			textField_plab.setText(null);
			textField_nrMax.setText(null);
			textField_recLab.setText(null);
			textField_recSem.setText(null);
			textField_recCurs.setText(null);
		}
		else
		{
			textField_id.setText(arr.get(0));
			textField_nume.setText(arr.get(1));
			textField_descriere.setText(arr.get(2));
			textField_pcurs.setText(arr.get(3));
			textField_psem.setText(arr.get(4));
			textField_plab.setText(arr.get(5));
			textField_nrMax.setText(arr.get(6));
			textField_recLab.setText(arr.get(7));
			textField_recSem.setText(arr.get(8));
			textField_recCurs.setText(arr.get(9));
		}	
	}
	
    private void tableAfisMouseClicked(MouseEvent evt) {
        String id = tableAfis.getValueAt(tableAfis.getSelectedRow(), 0).toString();
        try 
        {
        	setData(AdminSqlQueries.read_materie(MainClass.db.getCon(), id));
		} 
        catch (SQLException e) { TreatException.printSQLException(e); }
    }
}
