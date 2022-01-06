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
import java.awt.Component;

@SuppressWarnings("serial")
public class PanelCatalog extends JPanel {
	private JTextField textFieldCNP;
	public JButton btnNotareCatalog = new JButton("Notare");
	public JButton btnDescarcareCatalog = new JButton("Descarcare");
	public JComboBox comboBoxCategorie = new JComboBox();
	public JTable tableCatalog = new JTable();
	public JTable tableStudenti;
	
	private JScrollPane jsp = new JScrollPane(tableCatalog);
	private JTextField textFieldNota;
	private JLabel lblIdmaterie = new JLabel("ID_Materie");
	private JTextField textFieldMaterie = new JTextField();

	
	/**
	 * Create the panel.
	 */
	public PanelCatalog() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 225);
		add(jsp);
		
		textFieldCNP = new JTextField();
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
		textFieldMaterie.setColumns(10);
		textFieldMaterie.setBounds(1073, 101, 112, 19);
		
		add(textFieldMaterie);
		
		JScrollPane jsp_1 = new JScrollPane((Component) null);
		jsp_1.setBounds(2, 225, 959, 225);
		add(jsp_1);
		
		tableStudenti = new JTable();
		jsp_1.setViewportView(tableStudenti);
		
		setActionListeners();
		
		tableStudenti.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tableStudentiMouseClicked(evt);
            }
		});
	}
	
	public void setActionListeners()
	{
		btnNotareCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					ProfesorSqlQueries.notare_studenti(MainClass.db.getCon(), getData()); //TODO
					setTable(tableCatalog, ProfesorSqlQueries.vizualizare_studenti_note(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		

		btnDescarcareCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					ProfesorSqlQueries.descarcare_studenti(MainClass.db.getCon());//TODO
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
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
	
    private void tableStudentiMouseClicked(MouseEvent evt) {
    	HashMap<String, String> dataMap = new HashMap<String, String>();
    	dataMap.put("id_materie", tableStudenti.getValueAt(tableStudenti.getSelectedRow(), 0).toString());
    	dataMap.put("cnp", tableStudenti.getValueAt(tableStudenti.getSelectedRow(), 4).toString());
        setData(dataMap);
    }
}
