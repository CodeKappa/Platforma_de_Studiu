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
public class PanelCRUDgrupuri extends JPanel {
	private JTextField textField_id;
	private JTextField textField_idMaterie;
	private JLabel lblIdMaterie;
	public JButton btnCreate = new JButton("Create");
	public JButton btnUpdate = new JButton("Update");
	public JButton btnDelete = new JButton("Delete");
	
	private JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);

	
	/**
	 * Create the panel.
	 */
	public PanelCRUDgrupuri() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 450);
		add(jsp);
		
		textField_id = new JTextField();
		textField_id.setBounds(1074, 39, 112, 19);
		add(textField_id);
		textField_id.setColumns(10);
		
		textField_idMaterie = new JTextField();
		textField_idMaterie.setBounds(1074, 68, 112, 19);
		add(textField_idMaterie);
		textField_idMaterie.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(976, 45, 33, 13);
		add(lblId);
		
		lblIdMaterie = new JLabel("ID Materie");
		lblIdMaterie.setBounds(976, 74, 77, 13);
		add(lblIdMaterie);
		
		btnCreate.setBounds(966, 381, 220, 21);
		add(btnCreate);

		btnUpdate.setBounds(966, 412, 105, 21);
		add(btnUpdate);

		btnDelete.setBounds(1081, 412, 105, 21);
		add(btnDelete);
		
		setActionListeners();
		
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
					AdminSqlQueries.create_grup(MainClass.db.getCon(), getData());
					setTable(AdminSqlQueries.all_grup_data(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					AdminSqlQueries.update_grup(MainClass.db.getCon(), getData());
					setTable(AdminSqlQueries.all_grup_data(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					AdminSqlQueries.delete_grup(MainClass.db.getCon(), textField_id.getText());
					setTable(AdminSqlQueries.all_grup_data(MainClass.db.getCon()));
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
		arr.add(textField_idMaterie.getText());
		
		return arr;
	}
	
	public void setData(ArrayList<String> arr)
	{
		if(arr == null)
		{
			textField_id.setText(null);
			textField_idMaterie.setText(null);
		}
		else
		{
			textField_id.setText(arr.get(0));
			textField_idMaterie.setText(arr.get(1));
		}	
	}
	
    private void tableAfisMouseClicked(MouseEvent evt) {
        String id = tableAfis.getValueAt(tableAfis.getSelectedRow(), 0).toString();
        try 
        {
        	setData(AdminSqlQueries.read_grup(MainClass.db.getCon(), id));
		} 
        catch (SQLException e) { TreatException.printSQLException(e); }
    }
}
