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
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PanelStudentCreazaActivitatiGrup extends JPanel {
	public JTextField textField_nume;
	private JLabel lbl_nume;
	public JButton btnCreazaActivitate = new JButton("Creaza Activitate");
	public JButton btnSugereazaFerestre = new JButton("Sugereaza Ferestre");
	
	public JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);
	
	public String cnp;
	public JTextField textField_id;
	private final JTextField textField_descriere = new JTextField();
	private final JTextField textField_dataProgramare = new JTextField();
	private final JTextField textField_durata = new JTextField();
	private final JLabel lblDescriere = new JLabel("Descriere");
	private final JLabel lbl_idMat_1 = new JLabel("Data Programarii");
	private final JLabel lbl_Durata = new JLabel("Durata");
	private final JLabel lbl_dataExpirarii = new JLabel("Data Expirare ");
	private final JTextField textField_dataExpirarii = new JTextField();
	private final JTextField textField_nrMin = new JTextField();
	private final JLabel lbl_nrMin = new JLabel("Nr. Min");
	
	/**
	 * Create the panel.
	 */
	public PanelStudentCreazaActivitatiGrup() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 447);
		add(jsp);
		
		textField_nume = new JTextField();
		textField_nume.setBounds(1068, 157, 112, 19);
		add(textField_nume);
		textField_nume.setColumns(10);
		
		lbl_nume = new JLabel("Nume ");
		lbl_nume.setBounds(987, 160, 71, 13);
		add(lbl_nume);
		
		btnCreazaActivitate.setBounds(973, 344, 220, 21);
		add(btnCreazaActivitate);
		
		textField_id = new JTextField();
		textField_id.setColumns(10);
		textField_id.setBounds(1068, 128, 112, 19);
		add(textField_id);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(987, 131, 45, 13);
		add(lblId);
		textField_descriere.setColumns(10);
		textField_descriere.setBounds(1068, 186, 112, 19);
		
		add(textField_descriere);
		textField_dataProgramare.setColumns(10);
		textField_dataProgramare.setBounds(1068, 215, 112, 19);
		
		add(textField_dataProgramare);
		textField_durata.setColumns(10);
		textField_durata.setBounds(1068, 244, 112, 19);
		
		add(textField_durata);
		lblDescriere.setBounds(987, 189, 71, 13);
		
		add(lblDescriere);
		lbl_idMat_1.setBounds(987, 218, 83, 13);
		
		add(lbl_idMat_1);
		lbl_Durata.setBounds(987, 247, 71, 13);
		
		add(lbl_Durata);
		lbl_dataExpirarii.setBounds(987, 276, 71, 13);
		
		add(lbl_dataExpirarii);
		textField_dataExpirarii.setColumns(10);
		textField_dataExpirarii.setBounds(1068, 273, 112, 19);
		
		add(textField_dataExpirarii);
		textField_nrMin.setColumns(10);
		textField_nrMin.setBounds(1068, 302, 112, 19);
		
		add(textField_nrMin);
		lbl_nrMin.setBounds(987, 305, 71, 13);
		
		add(lbl_nrMin);
		btnSugereazaFerestre.setBounds(973, 371, 220, 21);
		
		add(btnSugereazaFerestre);
		
		
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
		textField_nume.setText(null);
		textField_id.setText(null);
		textField_descriere.setText(null);
		textField_dataProgramare.setText(null);
		textField_durata.setText(null);
		textField_dataExpirarii.setText(null);
		textField_nrMin.setText(null);
	}
	
	public ArrayList <String> getData()
	{
		ArrayList <String> arr = new ArrayList <String> ();
		arr.add(textField_id.getText());
		arr.add(textField_nume.getText());
		arr.add(textField_descriere.getText());
		arr.add(textField_dataProgramare.getText());
		arr.add(textField_durata.getText());
		arr.add(textField_dataExpirarii.getText());
		arr.add(textField_nrMin.getText());
		return arr;
	}
}
