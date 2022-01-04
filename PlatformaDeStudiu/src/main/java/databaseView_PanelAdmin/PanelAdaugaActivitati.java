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
public class PanelAdaugaActivitati extends JPanel {
	private JTextField textField_id;
	private JTextField textField_grup;
	private JTextField textField_cnp;
	private JTextField textField_nume;
	private JTextField textField_descriere;
	private JTextField textField_durata;
	private JLabel lblIdGrup;
	private JLabel label_cnpProf;
	private JLabel label_descriere;
	private JLabel lblDurata;
	private JLabel label_dataE;
	private JTextField textField_dataE;
	private JTextField textField_nrMin;
	public JButton btnCreate = new JButton("Create");
	
	private JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);
	private JTextField textField_dataP;
	private JLabel lblDataProgramarii;
	private JLabel label_nume;

	
	/**
	 * Create the panel.
	 */
	public PanelAdaugaActivitati() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 447);
		add(jsp);
		
		textField_id = new JTextField();
		textField_id.setBounds(1074, 56, 112, 19);
		add(textField_id);
		textField_id.setColumns(10);
		
		textField_cnp = new JTextField();
		textField_cnp.setBounds(1074, 114, 112, 19);
		add(textField_cnp);
		textField_cnp.setColumns(10);
		
		textField_grup = new JTextField();
		textField_grup.setBounds(1074, 85, 112, 19);
		add(textField_grup);
		textField_grup.setColumns(10);
		
		textField_nume = new JTextField();
		textField_nume.setBounds(1074, 143, 112, 19);
		add(textField_nume);
		textField_nume.setColumns(10);
		
		textField_descriere = new JTextField();
		textField_descriere.setBounds(1074, 172, 112, 19);
		add(textField_descriere);
		textField_descriere.setColumns(10);
		
		textField_durata = new JTextField();
		textField_durata.setToolTipText("");
		textField_durata.setBounds(1074, 230, 112, 19);
		add(textField_durata);
		textField_durata.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(971, 59, 33, 13);
		add(lblId);
		
		lblIdGrup = new JLabel("ID Grup");
		lblIdGrup.setBounds(971, 88, 45, 13);
		add(lblIdGrup);
		
		label_cnpProf = new JLabel("CNP Profesor");
		label_cnpProf.setBounds(971, 117, 88, 13);
		add(label_cnpProf);
		
		label_nume = new JLabel("Nume");
		label_nume.setBounds(971, 144, 83, 16);
		add(label_nume);
		
		label_descriere = new JLabel("Descriere");
		label_descriere.setBounds(971, 175, 105, 13);
		add(label_descriere);
		
		lblDurata = new JLabel("Durata");
		lblDurata.setBounds(971, 233, 71, 13);
		add(lblDurata);
		
		label_dataE = new JLabel("Data Expirarii");
		label_dataE.setBounds(971, 262, 83, 13);
		add(label_dataE);
		
		textField_dataE = new JTextField();
		textField_dataE.setBounds(1074, 259, 112, 19);
		add(textField_dataE);
		textField_dataE.setColumns(10);
		
		textField_nrMin = new JTextField();
		textField_nrMin.setBounds(1074, 288, 112, 19);
		add(textField_nrMin);
		textField_nrMin.setColumns(10);
		
		btnCreate.setBounds(971, 320, 220, 21);
		add(btnCreate);
		
		setActionListeners();
		
		textField_dataP = new JTextField();
		textField_dataP.setColumns(10);
		textField_dataP.setBounds(1074, 201, 112, 19);
		add(textField_dataP);
		
		lblDataProgramarii = new JLabel("Data Programarii");
		lblDataProgramarii.setBounds(971, 204, 95, 13);
		add(lblDataProgramarii);
		
		JLabel lblNrMinParticipanti = new JLabel("Nr. Min Studenti");
		lblNrMinParticipanti.setBounds(971, 291, 105, 13);
		add(lblNrMinParticipanti);
		
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
					AdminSqlQueries.adauga_activitate(MainClass.db.getCon(), getData());
					setTable(AdminSqlQueries.all_group_activities(MainClass.db.getCon()));
					setData(null);
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
		
		//arr.add(textField_id.getText());
		arr.add(textField_grup.getText());
		arr.add(textField_cnp.getText());
		arr.add(textField_nume.getText());
		arr.add(textField_descriere.getText());
		arr.add(textField_dataP.getText());
		arr.add(textField_durata.getText());
		arr.add(textField_dataE.getText());
		arr.add(textField_nrMin.getText());
		
		return arr;
	}
	
	public void setData(ArrayList<String> arr)
	{
		if(arr == null)
		{
			textField_id.setText(null);
			textField_grup.setText(null);
			textField_cnp.setText(null);
			textField_nume.setText(null);
			textField_descriere.setText(null);
			textField_dataP.setText(null);
			textField_durata.setText(null);
			textField_dataE.setText(null);
			textField_nrMin.setText(null);
		}
		else
		{
			textField_id.setText(arr.get(0));
			textField_grup.setText(arr.get(1));
			textField_cnp.setText(arr.get(2));
			textField_nume.setText(arr.get(3));
			textField_descriere.setText(arr.get(4));
			textField_dataP.setText(arr.get(5));
			textField_durata.setText(arr.get(6));
			textField_dataE.setText(arr.get(7));
			textField_nrMin.setText(arr.get(8));
		}	
	}

    private void tableAfisMouseClicked(MouseEvent evt) {
        String id = tableAfis.getValueAt(tableAfis.getSelectedRow(), 0).toString();
        try 
        {
        	setData(AdminSqlQueries.read_activitate(MainClass.db.getCon(), id));
		} 
        catch (SQLException e) { TreatException.printSQLException(e); }
    }
	
	public void setData() {
		setData(null);
		try 
		{
			setTable(AdminSqlQueries.all_group_activities(MainClass.db.getCon()));
		} 
		catch (SQLException e1) { TreatException.printSQLException(e1); }
	}
}
