package DatabaseView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


@SuppressWarnings("serial")
public class PanelLogin extends JPanel
{	
	private static JTextField usernameField = new JTextField();
	private static JTextField passwordField = new JTextField();
	private static JButton buttonLogin = new JButton("Login");
	
	PanelLogin()
	{
		setLayout(null);
		
		JLabel usernameLabel = new JLabel("ID");
		JLabel passwordLabel = new JLabel("Password");

		//Username		
		add(usernameLabel);
		add(usernameField);
		usernameLabel.setBounds(50, 10, 200, 20);	
		usernameField.setBounds(50, 30, 200, 25);
		
		//Password		
		add(passwordLabel);
		add(passwordField);
		passwordLabel.setBounds(50, 55, 200, 20);	
		passwordField.setBounds(50, 75, 200, 25);
		
		//Login Button
		add(buttonLogin);
		buttonLogin.setBounds(50, 110, 200, 25);
		
		this.setBorder(new LineBorder(Color.BLACK, 3));
	}
	
	public void setBackgroungColor(Color c)
	{
		this.setBackground(c);	
	}
	
	public Dimension getPreferredSize() { return new Dimension(300,155); }
	
	public void addSubmitListener(ActionListener listenForSubmitButton)
	{
		buttonLogin.addActionListener(listenForSubmitButton);
	}

	public String getUsernameField() {
		return usernameField.getText();
	}

	public String getPasswordField() {
		return passwordField.getText();
	}
}
