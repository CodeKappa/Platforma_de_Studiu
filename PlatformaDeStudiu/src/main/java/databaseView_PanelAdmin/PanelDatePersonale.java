package databaseView_PanelAdmin;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import java.awt.Color;
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
	/**
	 * Create the panel.
	 */
	public PanelDatePersonale() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		textField_cnp = new JTextField("adas");
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
		textField_iban.setBounds(1069, 176, 112, 19);
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
		label_iban.setBounds(988, 179, 45, 13);
		add(label_iban);
		
		label_nrContract = new JLabel("Nr. Contract");
		label_nrContract.setBounds(988, 208, 74, 13);
		add(label_nrContract);
		
		textField_contract = new JTextField();
		textField_contract.setEditable(false);
		textField_contract.setBounds(1069, 205, 112, 19);
		add(textField_contract);
		textField_contract.setColumns(10);
		
		JRadioButton radio_admin = new JRadioButton("admin");
		radio_admin.setEnabled(false);
		radio_admin.setBounds(984, 227, 78, 21);
		add(radio_admin);
		
		JRadioButton radio_profesor = new JRadioButton("profesor");
		radio_profesor.setEnabled(false);
		radio_profesor.setBounds(984, 250, 78, 21);
		add(radio_profesor);
		
		JRadioButton radio_student = new JRadioButton("student");
		radio_student.setEnabled(false);
		radio_student.setBounds(984, 273, 87, 21);
		add(radio_student);
		
		final ButtonGroup group = new ButtonGroup();

		group.add(radio_admin);
		group.add(radio_profesor);
		group.add(radio_student);
		radio_admin.setSelected(true);
	}
}
