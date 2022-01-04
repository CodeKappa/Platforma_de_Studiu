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
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class PanelCatalog extends JPanel {
	private JTextField textFieldCNP;
	public JButton btnNotareCatalog = new JButton("Notare");
	public JButton btnDescarcareCatalog = new JButton("Descarcare");
	public JComboBox comboBoxCategorie = new JComboBox();
	
	private JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);
	private JTextField textFieldNota;
	private JLabel lblIdmaterie = new JLabel("ID_Materie");
	private JTextField textFieldMaterie = new JTextField();

	
	/**
	 * Create the panel.
	 */
	public PanelCatalog() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 450);
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
		
		setActionListeners();
		
	}
	
	public void setActionListeners()
	{
		btnNotareCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					ProfesorSqlQueries.notare_studenti(MainClass.db.getCon(), getData()); //TODO
					setTable(ProfesorSqlQueries.vizualizare_studenti(MainClass.db.getCon()));
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
	
	public void setData(ArrayList<String> arr)
	{
		if(arr == null)
		{
			textFieldCNP.setText(null);
		}
		else
		{
			textFieldCNP.setText(arr.get(0));
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
