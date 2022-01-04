package databaseView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class PanelLogin extends JPanel
{	
<<<<<<< Updated upstream
	private JTextField usernameField = new JTextField("superadmin");
	private JTextField passwordField = new JPasswordField("12345");
=======
	private JTextField usernameField = new JTextField("pasarila@gmail.com"); // superadmin pasarila@gmail.com
	private JTextField passwordField = new JPasswordField("parola5555"); // 12345 parola5555
>>>>>>> Stashed changes
	private JButton buttonLogin = new JButton("Log in");
	//private JButton buttonSignUpComute = new JButton("Sign Up");
	
	PanelLogin()
	{
		setLayout(null);
		
		JLabel usernameLabel = new JLabel("Email");
		JLabel passwordLabel = new JLabel("Password");

		//Contract Number	
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
		buttonLogin.setBounds(50, 110, 100, 25);
		
		//Button to go to Sign Up
		//add(buttonSignUpComute);
		//buttonSignUpComute.setBounds(150, 110, 100, 25);
		
		this.setBorder(new LineBorder(Color.BLACK, 5));
	}
	
	public void reset()
	{
		usernameField.setText("");
		passwordField.setText("");
		setBackgroundColor(null);
	}
	
	public void setBackgroundColor(Color c)
	{
		this.setBackground(c);	
	}
	
	public Dimension getPreferredSize() { return new Dimension(300,155); }
	
	public void addLoginListener(ActionListener listenForLoginButton)
	{
		buttonLogin.addActionListener(listenForLoginButton);
	}
	
//	public void addComuteListener(ActionListener SignUp_ComuteButton)
//	{
//		buttonSignUpComute.addActionListener(SignUp_ComuteButton);
//	}

	public String getUsername() {
		return usernameField.getText();
	}

	public String getPassword() {
		return passwordField.getText();
	}
}
