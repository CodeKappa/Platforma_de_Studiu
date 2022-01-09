package databaseView_PanelStudent;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PanelStudentMesajeGrup extends JPanel {
	public JTextField textField_mesaj;
	public JLabel lblMesaj;
	public JButton btnTrimite = new JButton("Trimite Mesaj In Grup");
	public JButton btnMesaje = new JButton("Mesaje Grup");
	
	private JTable tableAfis = new JTable();
	private JScrollPane jsp = new JScrollPane(tableAfis);
	public final JTextField textField_grup = new JTextField();
	public final JLabel lblVeziGrup = new JLabel("Grup");
	
	public String cnp;
	/**
	 * Create the panel.
	 */
	public PanelStudentMesajeGrup() {
		setLayout(null);
		
		setBorder(new LineBorder(Color.BLACK, 1));
		
		jsp.setBounds(2, 2, 959, 447);
		add(jsp);
		
		textField_mesaj = new JTextField();
		textField_mesaj.setBounds(1068, 157, 112, 19);
		add(textField_mesaj);
		textField_mesaj.setColumns(10);
		
		lblMesaj = new JLabel("Mesaj");
		lblMesaj.setBounds(987, 160, 51, 13);
		add(lblMesaj);
		
		btnTrimite.setBounds(971, 236, 220, 21);
		add(btnTrimite);
		
		btnMesaje.setBounds(971, 261, 220, 21);
		add(btnMesaje);
		textField_grup.setColumns(10);
		textField_grup.setBounds(1068, 186, 112, 19);
		
		add(textField_grup);
		lblVeziGrup.setBounds(987, 189, 63, 13);
		
		add(lblVeziGrup);
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
		textField_mesaj.setText(null);
		textField_grup.setText(null);
	}
}
