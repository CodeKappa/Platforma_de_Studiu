package databaseView_PanelProfesor;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;

@SuppressWarnings("serial")
public class PanelCalendar extends JPanel {
	private JTextField textFieldDataInceput;
	public JButton btnProgramareCalendar = new JButton("Programare");
	public JButton btnDescarcareCalendar = new JButton("Descarcare");
	@SuppressWarnings("rawtypes")
	public JComboBox comboBoxCategorie = new JComboBox();
	public JTable tableCalendar = new JTable();
	public JTable tableMaterii;
	private JScrollPane jsp = new JScrollPane(tableCalendar);
	private JTextField textFieldDataFinal;
	private JTextField textFieldDurata;
	private JTextField textFieldIdMaterie;
	private JTextField textFieldNrMaxElevi;

	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PanelCalendar() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 225);
		add(jsp);
		
		textFieldDataInceput = new JTextField();
		textFieldDataInceput.setBounds(1073, 40, 112, 19);
		add(textFieldDataInceput);
		textFieldDataInceput.setColumns(10);
		
		JLabel lblId = new JLabel("Data/ora inceput");
		lblId.setBounds(965, 41, 105, 19);
		add(lblId);

		btnProgramareCalendar.setBounds(1086, 415, 105, 21);
		add(btnProgramareCalendar);
		
		textFieldDataFinal = new JTextField();
		textFieldDataFinal.setColumns(10);
		textFieldDataFinal.setBounds(1073, 71, 112, 19);
		add(textFieldDataFinal);
		
		textFieldDurata = new JTextField();
		textFieldDurata.setColumns(10);
		textFieldDurata.setBounds(1073, 101, 112, 19);
		add(textFieldDurata);
		
		textFieldIdMaterie = new JTextField();
		textFieldIdMaterie.setEditable(false);
		textFieldIdMaterie.setColumns(10);
		textFieldIdMaterie.setBounds(1073, 131, 112, 19);
		add(textFieldIdMaterie);
		
		textFieldNrMaxElevi = new JTextField();
		textFieldNrMaxElevi.setColumns(10);
		textFieldNrMaxElevi.setBounds(1073, 161, 112, 19);
		add(textFieldNrMaxElevi);
		
		comboBoxCategorie.setModel(new DefaultComboBoxModel(new String[] {"Curs", "Seminar", "Laborator"}));
		comboBoxCategorie.setBounds(1073, 191, 112, 19);
		add(comboBoxCategorie);
		
		JLabel lblId_1 = new JLabel("Data/ora final");
		lblId_1.setBounds(965, 72, 87, 19);
		add(lblId_1);
		
		JLabel lblId_2 = new JLabel("Durata");
		lblId_2.setBounds(965, 101, 87, 19);
		add(lblId_2);
		
		JLabel lblId_3 = new JLabel("ID Materie");
		lblId_3.setBounds(965, 131, 87, 19);
		add(lblId_3);
		
		JLabel lblId_4 = new JLabel("Nr max elevi");
		lblId_4.setBounds(965, 161, 87, 19);
		add(lblId_4);
		
		JLabel lblCategorie = new JLabel("Categorie");
		lblCategorie.setBounds(965, 191, 87, 19);
		add(lblCategorie);
		
		btnDescarcareCalendar.setBounds(971, 415, 105, 21);
		add(btnDescarcareCalendar);
		
		JScrollPane jsp_1 = new JScrollPane((Component) null);
		jsp_1.setBounds(2, 223, 959, 225);
		add(jsp_1);
		
		tableMaterii = new JTable();
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
	
	public ArrayList<String> getData()
	{
		ArrayList<String> data = new ArrayList<>();
		data.add(textFieldDataInceput.getText());
		data.add(textFieldDataFinal.getText());
		data.add(textFieldDurata.getText());
		data.add(textFieldIdMaterie.getText());
		if(comboBoxCategorie.getSelectedIndex() == 0) {
			data.add("Curs");
		}
		else if(comboBoxCategorie.getSelectedIndex() == 1) {
			data.add("Seminar");
		}
		else {
			data.add("Laborator");
		}
		data.add(textFieldNrMaxElevi.getText());
		return data;
	}
	
	public void setData(HashMap<String, String> map)
	{
		if(map == null)
		{
			textFieldDataInceput.setText(null);
			textFieldDataFinal.setText(null);
			textFieldDurata.setText(null);
			textFieldIdMaterie.setText(null);
			textFieldNrMaxElevi.setText(null);
			comboBoxCategorie.setSelectedIndex(0);
		}
		else
		{
			textFieldIdMaterie.setText(map.get("id_materie"));
			textFieldNrMaxElevi.setText(map.get("nr_max_elevi"));
		}	
	}
}
