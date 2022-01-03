package databaseView_PanelAdmin;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import databaseModel.AdminSqlQueries;
import databaseModel.TreatException;
import main.MainClass;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class PanelCautaUtilizatori extends JPanel {
	private JTextField textField_nume;
	private JTextField textField_prenume;
	private JLabel label_nume;
	private JLabel label_prenume;
	public JButton btnCauta = new JButton("Cauta");
	private JRadioButton radio_admin = new JRadioButton("admin");
	private JRadioButton radio_profesor = new JRadioButton("profesor");
	private JRadioButton radio_student = new JRadioButton("student");
	JRadioButton radio_all = new JRadioButton("toti");
	
	private JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);
	private final JTextPane txtpnFiltruDupaTip = new JTextPane();
	
	private int tip;
	private String nume;
	private String prenume;
	
	/**
	 * Create the panel.
	 */
	public PanelCautaUtilizatori() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 447);
		add(jsp);
		
		textField_prenume = new JTextField();
		textField_prenume.setBounds(1069, 96, 112, 19);
		add(textField_prenume);
		textField_prenume.setColumns(10);
		
		textField_nume = new JTextField();
		textField_nume.setBounds(1069, 67, 112, 19);
		add(textField_nume);
		textField_nume.setColumns(10);
		
		label_nume = new JLabel("Nume");
		label_nume.setBounds(988, 70, 45, 13);
		add(label_nume);
		
		label_prenume = new JLabel("Prenume");
		label_prenume.setBounds(988, 99, 57, 13);
		add(label_prenume);
		
		btnCauta.setBounds(971, 278, 220, 21);
		add(btnCauta);
		
		setActionListeners();
			
		radio_admin.setBounds(988, 187, 69, 21);
		add(radio_admin);
		
		radio_profesor.setBounds(988, 210, 78, 21);
		add(radio_profesor);

		radio_student.setBounds(988, 233, 73, 21);
		add(radio_student);
			
		radio_all.setBounds(988, 164, 73, 21);
		add(radio_all);
		
		final ButtonGroup group = new ButtonGroup();
		
		group.add(radio_admin);
		group.add(radio_profesor);
		group.add(radio_student);
		group.add(radio_all);
		
		tip = 0;
		
		radio_all.setSelected(true);
		txtpnFiltruDupaTip.setBackground(SystemColor.menu);
		txtpnFiltruDupaTip.setEditable(false);
		txtpnFiltruDupaTip.setText("Filtru dupa tip");
		txtpnFiltruDupaTip.setBounds(988, 139, 203, 19);
		
		add(txtpnFiltruDupaTip);
	}

	public void setActionListeners()
	{
		radio_all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tip = 0;
			}
		});
		radio_admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tip = 1;
			}
		});
		
		radio_student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tip = 2;
			}
		});
		
		radio_profesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tip = 3;
			}
		});
		
		btnCauta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					setName();
					setTable(AdminSqlQueries.cauta_user(MainClass.db.getCon(), nume, prenume, tip));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
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
	
	public void setName()
	{	
		nume = textField_nume.getText();
		prenume = textField_prenume.getText();
	}

	public void setData() 
	{
		textField_nume.setText(null);
		textField_prenume.setText(null);
		radio_all.setSelected(true);
		try 
		{
			setName();
			setTable(AdminSqlQueries.cauta_user(MainClass.db.getCon(), nume, prenume, tip));
		} 
		catch (SQLException e1) { TreatException.printSQLException(e1); }
	}
}
