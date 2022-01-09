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
public class PanelCatalog extends JPanel {
	private JTextField textFieldCNP;
	public JButton btnNotareCatalog = new JButton("Notare");
	public JButton btnDescarcareCatalog = new JButton("Descarcare");
	@SuppressWarnings("rawtypes")
	public JComboBox comboBoxCategorie = new JComboBox();
	public JTable tableCatalog = new JTable();
	public JTable tableStudenti;
	
	private JScrollPane jsp = new JScrollPane(tableCatalog);
	private JTextField textFieldNota;
	private JLabel lblIdmaterie = new JLabel("ID_Materie");
	private JTextField textFieldMaterie = new JTextField();
	private final JLabel lblNewLabel = new JLabel("Note Studenti");
	private final JLabel lblStudentiInscrisi = new JLabel("Studenti Inscrisi");

	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PanelCatalog() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 22, 959, 205);
		add(jsp);
		
		textFieldCNP = new JTextField();
		textFieldCNP.setEditable(false);
		textFieldCNP.setBounds(1073, 40, 112, 19);
		add(textFieldCNP);
		textFieldCNP.setColumns(10);
		
		JLabel lblId = new JLabel("CNP");
		lblId.setBounds(965, 41, 105, 19);
		add(lblId);

		btnNotareCatalog.setBounds(1086, 415, 105, 21);
		add(btnNotareCatalog);
		
		textFieldNota = new JTextField();
		textFieldNota.setColumns(10);
		textFieldNota.setBounds(1073, 71, 112, 19);
		add(textFieldNota);
		
		comboBoxCategorie.setModel(new DefaultComboBoxModel(new String[] {"Curs", "Seminar", "Laborator"}));
		comboBoxCategorie.setBounds(1073, 132, 112, 19);
		add(comboBoxCategorie);
		
		JLabel lblId_1 = new JLabel("Nota");
		lblId_1.setBounds(965, 72, 87, 19);
		add(lblId_1);
		
		JLabel lblCategorie = new JLabel("Categorie");
		lblCategorie.setBounds(965, 132, 87, 19);
		add(lblCategorie);
		
		btnDescarcareCatalog.setBounds(971, 415, 105, 21);
		add(btnDescarcareCatalog);
		lblIdmaterie.setBounds(965, 102, 105, 19);
		
		add(lblIdmaterie);
		textFieldMaterie.setEditable(false);
		textFieldMaterie.setColumns(10);
		textFieldMaterie.setBounds(1073, 101, 112, 19);
		
		add(textFieldMaterie);
		
		JScrollPane jsp_1 = new JScrollPane((Component) null);
		jsp_1.setBounds(2, 245, 959, 205);
		add(jsp_1);
		
		tableStudenti = new JTable();
		jsp_1.setViewportView(tableStudenti);
		lblNewLabel.setBounds(2, 9, 94, 14);
		
		add(lblNewLabel);
		lblStudentiInscrisi.setBounds(2, 230, 94, 14);
		
		add(lblStudentiInscrisi);
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
	
	public void setData(HashMap<String, String> map)
	{
		if(map == null)
		{
			textFieldMaterie.setText(null);
			textFieldCNP.setText(null);
			textFieldNota.setText(null);
			comboBoxCategorie.setSelectedIndex(0);
		}
		else
		{
			textFieldMaterie.setText(map.get("id_materie"));
			textFieldCNP.setText(map.get("cnp"));
		}	
	}
	
	public ArrayList<String> getData()
	{
		ArrayList<String> data = new ArrayList<>();
		data.add(textFieldMaterie.getText());
		data.add(textFieldCNP.getText());
		if(comboBoxCategorie.getSelectedIndex() == 0) {
			data.add("Curs");
		}
		else if(comboBoxCategorie.getSelectedIndex() == 1) {
			data.add("Seminar");
		}
		else {
			data.add("Laborator");
		}
		data.add(textFieldNota.getText());
		return data;
	}
}
