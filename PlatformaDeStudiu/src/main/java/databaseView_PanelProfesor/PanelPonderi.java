package databaseView_PanelProfesor;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import databaseModel.AdminSqlQueries;
import databaseModel.PersoaneSqlQueries;
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
import java.util.HashMap;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class PanelPonderi extends JPanel {
	private JTextField textFieldPC;
	public JButton btnActualizare = new JButton("Actualizare");
	
	private JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);
	private JTextField textFieldPS;
	private JLabel lblIdmaterie = new JLabel("Laborator(%)");
	private JTextField textFieldPL = new JTextField();
	private JTextField textFieldId = new JTextField();
	private JLabel lblIdMaterie = new JLabel("ID Materie");

	
	/**
	 * Create the panel.
	 */
	public PanelPonderi() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 450);
		add(jsp);
		
		textFieldPC = new JTextField();
		textFieldPC.setBounds(1073, 71, 112, 19);
		add(textFieldPC);
		textFieldPC.setColumns(10);
		
		JLabel lblId = new JLabel("Curs(%)");
		lblId.setBounds(971, 72, 105, 19);
		add(lblId);

		btnActualizare.setBounds(1086, 415, 105, 21);
		add(btnActualizare);
		
		textFieldPS = new JTextField();
		textFieldPS.setColumns(10);
		textFieldPS.setBounds(1073, 101, 112, 19);
		add(textFieldPS);
		
		JLabel lblId_1 = new JLabel("Seminar(%)");
		lblId_1.setBounds(971, 102, 87, 19);
		add(lblId_1);
		lblIdmaterie.setBounds(971, 132, 81, 19);
		
		add(lblIdmaterie);
		textFieldPL.setColumns(10);
		textFieldPL.setBounds(1073, 131, 112, 19);
		
		add(textFieldPL);
		textFieldId.setColumns(10);
		textFieldId.setBounds(1073, 40, 112, 19);
		
		add(textFieldId);
		lblIdMaterie.setBounds(971, 41, 87, 19);
		
		add(lblIdMaterie);
		
		setActionListeners();
		
		tableAfis.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tableAfisMouseClicked(evt);
            }
		});
		
	}
	
	public void setActionListeners()
	{
		btnActualizare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					ProfesorSqlQueries.actualizare_ponderi(MainClass.db.getCon(), getData()); //TODO
					setTable(ProfesorSqlQueries.vizualizare_ponderi(MainClass.db.getCon()));
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
	
	public ArrayList<Integer> getData()
	{
		ArrayList<Integer> data = new ArrayList<>();
		data.add(Integer.parseInt(textFieldId.getText()));
		data.add(Integer.parseInt(textFieldPC.getText()));
		data.add(Integer.parseInt(textFieldPS.getText()));
		data.add(Integer.parseInt(textFieldPL.getText()));
		return data;
	}
	
	public void setData(HashMap<String, String> map)
	{
		if(map == null)
		{
			textFieldId.setText(null);
			textFieldPC.setText(null);
			textFieldPS.setText(null);
			textFieldPL.setText(null);
		}
		else
		{
			textFieldId.setText(map.get("id_materie"));
			textFieldPC.setText(map.get("procent_curs"));
			textFieldPS.setText(map.get("procent_seminar"));
			textFieldPL.setText(map.get("procent_laborator"));
		}	
	}
	
	private void tableAfisMouseClicked(MouseEvent evt) {
    	HashMap<String, String> dataMap = new HashMap<String, String>();
    	dataMap.put("id_materie", tableAfis.getValueAt(tableAfis.getSelectedRow(), 1).toString());
    	dataMap.put("procent_curs", tableAfis.getValueAt(tableAfis.getSelectedRow(), 2).toString());
    	dataMap.put("procent_seminar", tableAfis.getValueAt(tableAfis.getSelectedRow(), 3).toString());
    	dataMap.put("procent_laborator", tableAfis.getValueAt(tableAfis.getSelectedRow(), 4).toString());
        setData(dataMap);
    }
}
