package databaseView_PanelAdmin;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import databaseModel.AdminSqlQueries;
import databaseModel.TreatException;
import main.MainClass;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PanelAsignareProfesori extends JPanel {
	private JTextField textField_idProfesor;
	private JTextField textField_idMaterie;
	private JLabel label_idProfesor;
	private JLabel label_idMaterie;
	public JButton btnAssign = new JButton("Asigneaza");
	
	private JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);
	
	/**
	 * Create the panel.
	 */
	public PanelAsignareProfesori() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 447);
		add(jsp);
		
		textField_idMaterie = new JTextField();
		textField_idMaterie.setBounds(1069, 96, 112, 19);
		add(textField_idMaterie);
		textField_idMaterie.setColumns(10);
		
		textField_idProfesor = new JTextField();
		textField_idProfesor.setBounds(1069, 67, 112, 19);
		add(textField_idProfesor);
		textField_idProfesor.setColumns(10);
		
		label_idProfesor = new JLabel("CNP Profesor");
		label_idProfesor.setBounds(988, 70, 82, 13);
		add(label_idProfesor);
		
		label_idMaterie = new JLabel("ID Materie");
		label_idMaterie.setBounds(988, 99, 82, 13);
		add(label_idMaterie);
		
		btnAssign.setBounds(988, 125, 193, 21);
		add(btnAssign);
		
		setActionListeners();
	}

	public void setActionListeners()
	{
		
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					AdminSqlQueries.asigneaza_profesor(MainClass.db.getCon(), textField_idProfesor.getText(), textField_idMaterie.getText());
					setData();
				} 
				catch (SQLException e1) { TreatException.printSQLException(e1); }
			}
		});
	}
	
	public void setData() 
	{
		textField_idProfesor.setText(null);
		textField_idMaterie.setText(null);
	}
}
