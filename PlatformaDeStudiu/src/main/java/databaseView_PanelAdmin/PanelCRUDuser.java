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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PanelCRUDuser extends JPanel {
	private JTextField textField_cnp;
	private JTextField textField_nume;
	private JTextField textField_prenume;
	private JTextField textField_adresa;
	private JTextField textField_telefon;
	private JTextField textField_iban;
	private JLabel label_nume;
	private JLabel label_prenume;
	private JLabel label_adresa;
	private JLabel label_telefon;
	private JLabel label_iban;
	private JLabel label_nrContract;
	private JLabel label_parola;
	private JTextField textField_contract;
	private JTextField textField_parola;
	public JButton button_create = new JButton("Create User");
	public JButton button_update = new JButton("Update User");
	public JButton button_delete = new JButton("Delete User");
	private JRadioButton radio_admin = new JRadioButton("admin");
	private JRadioButton radio_profesor = new JRadioButton("profesor");
	private JRadioButton radio_student = new JRadioButton("student");
	
	private JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);
	private JTextField textField_an;
	private JTextField textField_nrOre;
	private JTextField textField_departament;
	private JLabel label_an;
	private JLabel label_nrOre;
	private JLabel label_departament;
	private JTextField textField_oreMin;
	private JTextField textField_oreMax;
	private JLabel label_nrMaxOre;
	private JLabel label_nrMinOre;
	private JTextField textField_email;
	private JLabel label_email;
	
	/**
	 * Create the panel.
	 */
	public PanelCRUDuser() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 447);
		add(jsp);
		
		textField_cnp = new JTextField();
		textField_cnp.setBounds(1069, 10, 112, 19);
		add(textField_cnp);
		textField_cnp.setColumns(10);
		
		textField_prenume = new JTextField();
		textField_prenume.setBounds(1069, 68, 112, 19);
		add(textField_prenume);
		textField_prenume.setColumns(10);
		
		textField_nume = new JTextField();
		textField_nume.setBounds(1069, 39, 112, 19);
		add(textField_nume);
		textField_nume.setColumns(10);
		
		textField_adresa = new JTextField();
		textField_adresa.setBounds(1069, 97, 112, 19);
		add(textField_adresa);
		textField_adresa.setColumns(10);
		
		textField_telefon = new JTextField();
		textField_telefon.setBounds(1069, 129, 112, 19);
		add(textField_telefon);
		textField_telefon.setColumns(10);
		
		textField_iban = new JTextField();
		textField_iban.setBounds(1069, 187, 112, 19);
		add(textField_iban);
		textField_iban.setColumns(10);
		
		JLabel label_cnp = new JLabel("CNP");
		label_cnp.setBounds(988, 13, 33, 13);
		add(label_cnp);
		
		label_nume = new JLabel("Nume");
		label_nume.setBounds(988, 42, 45, 13);
		add(label_nume);
		
		label_prenume = new JLabel("Prenume");
		label_prenume.setBounds(988, 71, 57, 13);
		add(label_prenume);
		
		label_adresa = new JLabel("Adresa");
		label_adresa.setBounds(988, 98, 45, 16);
		add(label_adresa);
		
		label_telefon = new JLabel("Telefon");
		label_telefon.setBounds(988, 132, 45, 13);
		add(label_telefon);
		
		label_iban = new JLabel("IBAN");
		label_iban.setBounds(988, 190, 45, 13);
		add(label_iban);
		
		label_nrContract = new JLabel("Nr. Contract");
		label_nrContract.setBounds(988, 219, 74, 13);
		add(label_nrContract);
		
		label_parola = new JLabel("Parola");
		label_parola.setBounds(988, 248, 45, 13);
		add(label_parola);
		
		textField_contract = new JTextField();
		textField_contract.setBounds(1069, 216, 112, 19);
		add(textField_contract);
		textField_contract.setColumns(10);
		
		textField_parola = new JTextField();
		textField_parola.setBounds(1069, 245, 112, 19);
		add(textField_parola);
		textField_parola.setColumns(10);
		
		button_create.setBounds(966, 381, 220, 21);
		add(button_create);

		button_update.setBounds(966, 412, 105, 21);
		add(button_update);

		button_delete.setBounds(1081, 412, 105, 21);
		add(button_delete);
		
		setActionListeners();
			
		radio_admin.setBounds(971, 354, 69, 21);
		add(radio_admin);
		
		radio_profesor.setBounds(1038, 354, 78, 21);
		add(radio_profesor);

		radio_student.setBounds(1118, 354, 73, 21);
		add(radio_student);
		
		final ButtonGroup group = new ButtonGroup();
		
		group.add(radio_admin);
		group.add(radio_profesor);
		group.add(radio_student);
		radio_admin.setSelected(true);
		
		textField_an = new JTextField();
		textField_an.setBounds(1069, 274, 112, 19);
		add(textField_an);
		textField_an.setColumns(10);
		
		textField_nrOre = new JTextField();
		textField_nrOre.setColumns(10);
		textField_nrOre.setBounds(1069, 303, 112, 19);
		add(textField_nrOre);
		
		textField_departament = new JTextField();
		textField_departament.setColumns(10);
		textField_departament.setBounds(1069, 332, 112, 19);
		add(textField_departament);
		
		label_an = new JLabel("An Studiu");
		label_an.setBounds(988, 277, 57, 13);
		add(label_an);
		
		label_nrOre = new JLabel("Nr. Ore");
		label_nrOre.setBounds(988, 306, 45, 13);
		add(label_nrOre);
		
		label_departament = new JLabel("Departament");
		label_departament.setBounds(988, 335, 74, 13);
		add(label_departament);
		
		textField_oreMin = new JTextField();
		textField_oreMin.setColumns(10);
		textField_oreMin.setBounds(1069, 274, 112, 19);
		add(textField_oreMin);
		
		textField_oreMax = new JTextField();
		textField_oreMax.setColumns(10);
		textField_oreMax.setBounds(1069, 303, 112, 19);
		add(textField_oreMax);
		
		label_nrMaxOre = new JLabel("Nr. Max Ore");
		label_nrMaxOre.setBounds(988, 306, 74, 13);
		add(label_nrMaxOre);
		
		label_nrMinOre = new JLabel("Nr. Min Ore");
		label_nrMinOre.setBounds(988, 277, 74, 13);
		add(label_nrMinOre);
		
		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(1069, 158, 112, 19);
		add(textField_email);
		
		label_email = new JLabel("Email");
		label_email.setBounds(988, 164, 45, 13);
		add(label_email);
		
		setExtraLabelsOnFalse();
	}
	
	public void setExtraLabelsOnFalse()
	{
		textField_an.setVisible(false);
		textField_nrOre.setVisible(false);
		textField_departament.setVisible(false);
		textField_oreMin.setVisible(false);
		textField_oreMax.setVisible(false);
		label_an.setVisible(false);
		label_nrOre.setVisible(false);
		label_nrMaxOre.setVisible(false);
		label_nrMinOre.setVisible(false);
		label_departament.setVisible(false);
	}
	
	public void setActionListeners()
	{
		
        tableAfis.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String cnp = tableAfis.getValueAt(tableAfis.getSelectedRow(), 0).toString();
                try 
                {
                	setData(AdminSqlQueries.read_user(MainClass.db.getCon(), cnp));
        		} 
                catch (SQLException e) { TreatException.printSQLException(e); }
            }
        });
        
		radio_admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setExtraLabelsOnFalse();
			}
		});
		
		radio_student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setExtraLabelsOnFalse();
				textField_an.setVisible(true);
				textField_nrOre.setVisible(true);
				label_an.setVisible(true);
				label_nrOre.setVisible(true);
			}
		});
		
		radio_profesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setExtraLabelsOnFalse();
				textField_departament.setVisible(true);
				textField_oreMin.setVisible(true);
				textField_oreMax.setVisible(true);
				label_nrMaxOre.setVisible(true);
				label_nrMinOre.setVisible(true);
				label_departament.setVisible(true);
			}
		});
		
		button_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					AdminSqlQueries.create_user(MainClass.db.getCon(), getData());
					setTable(AdminSqlQueries.all_user_data(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		button_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					AdminSqlQueries.update_user(MainClass.db.getCon(), getData());
					setTable(AdminSqlQueries.all_user_data(MainClass.db.getCon()));
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					AdminSqlQueries.delete_user(MainClass.db.getCon(), textField_cnp.getText());
					setTable(AdminSqlQueries.all_user_data(MainClass.db.getCon()));
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
		
		if(radio_admin.isSelected())
			arr.add("1");
		else if(radio_student.isSelected())
			arr.add("2");
		else if(radio_profesor.isSelected())
			arr.add("3");
		
		arr.add(textField_cnp.getText());
		arr.add(textField_nume.getText());
		arr.add(textField_prenume.getText());
		arr.add(textField_adresa.getText());
		arr.add(textField_telefon.getText());
		arr.add(textField_email.getText());
		arr.add(textField_iban.getText());
		arr.add(textField_contract.getText());
		arr.add(textField_parola.getText());

		
		if(arr.get(0).equals("1"))
		{
			arr.add(null);
			arr.add(null);
			arr.add(null);
		}
		else if(arr.get(0).equals("2"))
		{
			arr.add(textField_an.getText());
			arr.add(textField_nrOre.getText());
			arr.add(null);
		}
		else if(arr.get(0).equals("3"))
		{
			arr.add(textField_oreMin.getText());
			arr.add(textField_oreMax.getText());
			arr.add(textField_departament.getText());
		}
		
		return arr;
	}
	
	public void setData(ArrayList<String> arr)
	{
		if(arr == null)
		{
			textField_cnp.setText(null);
			textField_nume.setText(null);
			textField_prenume.setText(null);
			textField_adresa.setText(null);
			textField_telefon.setText(null);
			textField_email.setText(null);
			textField_iban.setText(null);
			textField_contract.setText(null);
		}
		else
		{
			textField_cnp.setText(arr.get(0));
			textField_nume.setText(arr.get(1));
			textField_prenume.setText(arr.get(2));
			textField_adresa.setText(arr.get(3));
			textField_telefon.setText(arr.get(4));
			textField_email.setText(arr.get(5));
			textField_iban.setText(arr.get(6));
			textField_contract.setText(arr.get(7));
			
			if(arr.get(arr.size()-1).equals("1"))
			{
				radio_admin.setSelected(true);
				for(ActionListener a: radio_admin.getActionListeners()) {
				    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
				    });
				}
			}			
			else if(arr.get(arr.size()-1).equals("2"))
			{
				radio_student.setSelected(true);
				for(ActionListener a: radio_student.getActionListeners()) {
				    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
				    });
				}
				textField_an.setText(arr.get(8));
				textField_nrOre.setText(arr.get(9));
			}
			else if(arr.get(arr.size()-1).equals("3"))
			{
				radio_profesor.setSelected(true);
				for(ActionListener a: radio_profesor.getActionListeners()) {
				    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
				    });
				}
				textField_oreMin.setText(arr.get(8));
				textField_oreMax.setText(arr.get(9));
				textField_departament.setText(arr.get(10));			
			}
		}	
	}
}
