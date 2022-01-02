package databaseView_PanelAdmin;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class PanelCRUDuser extends JPanel {
	private JTextField textField_cnp;
	private JTextField textField_nume;
	private JTextField textField_prenume;
	private JTextField textField_adresa;
	private JTextField textField_telefon;
	private JTextField textField_iban;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTextField textField_contract;
	private JTextField textField_parola;

	/**
	 * Create the panel.
	 */
	public PanelCRUDuser() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		textField_cnp = new JTextField();
		textField_cnp.setBounds(1069, 28, 112, 19);
		add(textField_cnp);
		textField_cnp.setColumns(10);
		
		textField_prenume = new JTextField();
		textField_prenume.setBounds(1069, 86, 112, 19);
		add(textField_prenume);
		textField_prenume.setColumns(10);
		
		textField_nume = new JTextField();
		textField_nume.setBounds(1069, 57, 112, 19);
		add(textField_nume);
		textField_nume.setColumns(10);
		
		textField_adresa = new JTextField();
		textField_adresa.setBounds(1069, 115, 112, 19);
		add(textField_adresa);
		textField_adresa.setColumns(10);
		
		textField_telefon = new JTextField();
		textField_telefon.setBounds(1069, 147, 112, 19);
		add(textField_telefon);
		textField_telefon.setColumns(10);
		
		textField_iban = new JTextField();
		textField_iban.setBounds(1069, 176, 112, 19);
		add(textField_iban);
		textField_iban.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CNP");
		lblNewLabel.setBounds(988, 31, 33, 13);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Nume");
		lblNewLabel_1.setBounds(988, 60, 45, 13);
		add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Prenume");
		lblNewLabel_2.setBounds(988, 89, 57, 13);
		add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Adresa");
		lblNewLabel_3.setBounds(988, 116, 45, 16);
		add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Telefon");
		lblNewLabel_4.setBounds(988, 150, 45, 13);
		add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("IBAN");
		lblNewLabel_5.setBounds(988, 179, 45, 13);
		add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Nr. Contract");
		lblNewLabel_6.setBounds(988, 208, 74, 13);
		add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Parola");
		lblNewLabel_7.setBounds(988, 237, 45, 13);
		add(lblNewLabel_7);
		
		textField_contract = new JTextField();
		textField_contract.setBounds(1069, 205, 112, 19);
		add(textField_contract);
		textField_contract.setColumns(10);
		
		textField_parola = new JTextField();
		textField_parola.setBounds(1069, 234, 112, 19);
		add(textField_parola);
		textField_parola.setColumns(10);
		
		JButton button_create = new JButton("Create User");
		button_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_create.setBounds(988, 331, 193, 21);
		add(button_create);
		
		JButton button_update = new JButton("Update User");
		button_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_update.setBounds(988, 356, 193, 21);
		add(button_update);
		
		JButton button_delete = new JButton("Delete User");
		button_delete.setBounds(988, 381, 193, 21);
		add(button_delete);
		
		JRadioButton radio_admin = new JRadioButton("admin");
		radio_admin.setBounds(984, 258, 78, 21);
		add(radio_admin);
		
		JRadioButton radio_profesor = new JRadioButton("profesor");
		radio_profesor.setBounds(984, 281, 78, 21);
		add(radio_profesor);
		
		JRadioButton radio_student = new JRadioButton("student");
		radio_student.setBounds(984, 304, 87, 21);
		add(radio_student);
		
		final ButtonGroup group = new ButtonGroup();

		group.add(radio_admin);
		group.add(radio_profesor);
		group.add(radio_student);
		radio_admin.setSelected(true);
	}
}
