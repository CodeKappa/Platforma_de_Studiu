package databaseView_PanelProfesor;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class PanelDatePersonale extends JPanel {
	private JTextField textField_cnp;
	private JTextField textField_nume;
	private JTextField textField_prenume;
	private JTextField textField_adresa;
	private JTextField textField_telefon;
	private JTextField textField_iban;
	private JTextField textField_contract;
	private JLabel label_nume;
	private JLabel label_prenume;
	private JLabel label_adresa;
	private JLabel label_telefon;
	private JLabel label_iban;
	private JLabel label_nrContract;
	private JRadioButton radio_admin = new JRadioButton("admin");
	private JRadioButton radio_profesor = new JRadioButton("profesor");
	private JRadioButton radio_student = new JRadioButton("student");
	private JTextField textField_email;
	private JLabel label_email;
	private JTextField textField_nrOreMin;
	private JTextField textField_nrOreMax;
	private JTextField textField_departament;
	/**
	 * Create the panel.
	 */
	public PanelDatePersonale() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		textField_cnp = new JTextField();
		textField_cnp.setEditable(false);
		textField_cnp.setBounds(1069, 28, 112, 19);
		add(textField_cnp);
		textField_cnp.setColumns(10);
		
		textField_prenume = new JTextField();
		textField_prenume.setEditable(false);
		textField_prenume.setBounds(1069, 86, 112, 19);
		add(textField_prenume);
		textField_prenume.setColumns(10);
		
		textField_nume = new JTextField();
		textField_nume.setEditable(false);
		textField_nume.setBounds(1069, 57, 112, 19);
		add(textField_nume);
		textField_nume.setColumns(10);
		
		textField_adresa = new JTextField();
		textField_adresa.setEditable(false);
		textField_adresa.setBounds(1069, 115, 112, 19);
		add(textField_adresa);
		textField_adresa.setColumns(10);
		
		textField_telefon = new JTextField();
		textField_telefon.setEditable(false);
		textField_telefon.setBounds(1069, 147, 112, 19);
		add(textField_telefon);
		textField_telefon.setColumns(10);
		
		textField_iban = new JTextField();
		textField_iban.setEditable(false);
		textField_iban.setBounds(1069, 206, 112, 19);
		add(textField_iban);
		textField_iban.setColumns(10);
		
		JLabel label_cnp = new JLabel("CNP");
		label_cnp.setBounds(988, 31, 33, 13);
		add(label_cnp);
		
		label_nume = new JLabel("Nume");
		label_nume.setBounds(988, 60, 45, 13);
		add(label_nume);
		
		label_prenume = new JLabel("Prenume");
		label_prenume.setBounds(988, 89, 57, 13);
		add(label_prenume);
		
		label_adresa = new JLabel("Adresa");
		label_adresa.setBounds(988, 116, 45, 16);
		add(label_adresa);
		
		label_telefon = new JLabel("Telefon");
		label_telefon.setBounds(988, 150, 45, 13);
		add(label_telefon);
		
		label_iban = new JLabel("IBAN");
		label_iban.setBounds(988, 209, 45, 13);
		add(label_iban);
		
		label_nrContract = new JLabel("Nr. Contract");
		label_nrContract.setBounds(988, 238, 74, 13);
		add(label_nrContract);
		
		textField_contract = new JTextField();
		textField_contract.setEditable(false);
		textField_contract.setBounds(1069, 235, 112, 19);
		add(textField_contract);
		textField_contract.setColumns(10);
				
		radio_admin.setEnabled(false);
		radio_admin.setBounds(953, 351, 74, 21);
		add(radio_admin);
		radio_profesor.setSelected(true);
		
		radio_profesor.setEnabled(false);
		radio_profesor.setBounds(1029, 351, 78, 21);
		add(radio_profesor);

		radio_student.setEnabled(false);
		radio_student.setBounds(1109, 351, 87, 21);
		add(radio_student);
		
		final ButtonGroup group = new ButtonGroup();

		group.add(radio_admin);
		group.add(radio_profesor);
		group.add(radio_student);
		
		textField_email = new JTextField();
		textField_email.setEditable(false);
		textField_email.setColumns(10);
		textField_email.setBounds(1069, 176, 112, 19);
		add(textField_email);
		
		label_email = new JLabel("Email");
		label_email.setBounds(988, 178, 45, 13);
		add(label_email);
		
		textField_nrOreMin = new JTextField();
		textField_nrOreMin.setEditable(false);
		textField_nrOreMin.setColumns(10);
		textField_nrOreMin.setBounds(1069, 265, 112, 19);
		add(textField_nrOreMin);
		
		textField_nrOreMax = new JTextField();
		textField_nrOreMax.setEditable(false);
		textField_nrOreMax.setColumns(10);
		textField_nrOreMax.setBounds(1069, 295, 112, 19);
		add(textField_nrOreMax);
		
		textField_departament = new JTextField();
		textField_departament.setEditable(false);
		textField_departament.setColumns(10);
		textField_departament.setBounds(1069, 325, 112, 19);
		add(textField_departament);
		
		JLabel label_nrContract_1 = new JLabel("Departament");
		label_nrContract_1.setBounds(988, 328, 74, 13);
		add(label_nrContract_1);
		
		JLabel label_nrOreMax = new JLabel("Nr. Max Ore");
		label_nrOreMax.setBounds(988, 298, 74, 13);
		add(label_nrOreMax);
		
		JLabel label_nrOreMin = new JLabel("Nr. Min Ore");
		label_nrOreMin.setBounds(988, 268, 74, 13);
		add(label_nrOreMin);
	}
	
	public void setData(ArrayList<String> arr)
	{
		textField_cnp.setText(arr.get(0));
		textField_nume.setText(arr.get(1));
		textField_prenume.setText(arr.get(2));
		textField_adresa.setText(arr.get(3));
		textField_telefon.setText(arr.get(4));
		textField_email.setText(arr.get(5));
		textField_iban.setText(arr.get(6));
		textField_contract.setText(arr.get(7));
		textField_nrOreMin.setText(arr.get(8));
		textField_nrOreMax.setText(arr.get(9));
		textField_departament.setText(arr.get(10));
		radio_profesor.setSelected(true);
	}
}
