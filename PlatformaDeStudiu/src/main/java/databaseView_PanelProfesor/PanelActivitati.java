package databaseView_PanelProfesor;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import databaseModel.AdminSqlQueries;
import databaseModel.ProfesorSqlQueries;
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
public class PanelActivitati extends JPanel {
	private JTextField textField_id;
	public JButton btnInscriere = new JButton("Inscriere");
	
	private JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);

	
	/**
	 * Create the panel.
	 */
	public PanelActivitati() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 450);
		add(jsp);
		
		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setBounds(1052, 41, 112, 19);
		add(textField_id);
		textField_id.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(1009, 45, 33, 13);
		add(lblId);

		btnInscriere.setBounds(1052, 411, 105, 21);
		add(btnInscriere);
		
		setActionListeners();
		
        tableAfis.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tableAfisMouseClicked(evt);
            }
        });	
	}
	
	public void setActionListeners()
	{
		btnInscriere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					ProfesorSqlQueries.inscriere_activitati_studiu(MainClass.db.getCon(), getData());
					setTable(ProfesorSqlQueries.vizualizare_activitati_studiu(MainClass.db.getCon()));
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
	
	public int getData()
	{
		return Integer.parseInt(textField_id.getText());
	}
	
	public void setData(ArrayList<String> arr)
	{
		if(arr == null)
		{
			textField_id.setText(null);
		}
		else
		{
			textField_id.setText(arr.get(0));
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
