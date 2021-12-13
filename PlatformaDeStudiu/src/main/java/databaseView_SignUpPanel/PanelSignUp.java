package databaseView_SignUpPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


@SuppressWarnings("serial")
public class PanelSignUp extends JPanel
{	
	private ArrayList<SignUpField> data = new ArrayList<SignUpField>();
	private SignUpLabelMap lm = new SignUpLabelMap();
	private JButton buttonLoginComute = new JButton("Log in");
	private JButton buttonSignUp = new JButton("Sign Up");
	
	public void addComponentLabel(JLabel l, int x, int y, int width, int height)
	{
		add(l);
		l.setBounds(x, y, width, height);	
	}
	
	public void addComponentTextField(JTextField tf, int x, int y, int width, int height)
	{	
		add(tf);
		tf.setBounds(x, y, width, height);
	}
		
	public PanelSignUp()
	{
		setLayout(null);
		
		data.add(new SignUpField (SIGNUP_LABEL.NAME));		      //0
		data.add(new SignUpField (SIGNUP_LABEL.PRENUME));		  //1
		data.add(new SignUpField (SIGNUP_LABEL.CNP));		      //2
		data.add(new SignUpField (SIGNUP_LABEL.ADRESS));		  //3
		data.add(new SignUpField (SIGNUP_LABEL.PHONE));		      //4
		data.add(new SignUpField (SIGNUP_LABEL.EMAIL));		      //5
		data.add(new SignUpField (SIGNUP_LABEL.IBAN));		      //6
		data.add(new SignUpField (SIGNUP_LABEL.PASSWORD));	      //7
		data.add(new SignUpField (SIGNUP_LABEL.PASSWORDCONFIRM)); //8

		int gapBetweenComponents = 25;
		int i = 0;
		
		for(SignUpField f : data)
		{
			if(i++ % 2 == 0)
			{
				int x = 50;
				int y = gapBetweenComponents * i;
				addComponentLabel(f.getLabel(), x, y, SignUpField.lwidth, SignUpField.lheight);
				addComponentTextField(f.getTextField(), x, y+20, SignUpField.tfwidth, SignUpField.tfHeight);
			}
				
			else
			{
				int x = 250;
				int y = gapBetweenComponents * (i-1);
				addComponentLabel(f.getLabel(), x, y, SignUpField.lwidth, SignUpField.lheight);
				addComponentTextField(f.getTextField(), x, y+20, SignUpField.tfwidth, SignUpField.tfHeight);
			}
							
		}
		
		int y = gapBetweenComponents * (i+2);
		//Button to go to Login
		add(buttonLoginComute);
		buttonLoginComute.setBounds(150, y, 100, 25);
		
		//Button to Sign Up
		add(buttonSignUp);
		buttonSignUp.setBounds(250, y, 100, 25);
		
		this.setBorder(new LineBorder(Color.RED, 5));
	}

	public void setBackgroungColor(Color c)
	{
		this.setBackground(c);	
	}
	
	public Dimension getPreferredSize() { return new Dimension(500,300); }
	
	public void addSignUpListener(ActionListener listenForSignUpButton)
	{
		buttonSignUp.addActionListener(listenForSignUpButton);
	}
	
	public void addComuteListener(ActionListener Login_ComuteButton)
	{
		buttonLoginComute.addActionListener(Login_ComuteButton);
	}

	public String getTextFrom(SIGNUP_LABEL l) {
		for(SignUpField f : data)
		{
			if(f.getLabel().getText() == lm.getLabelName(l))
				return f.getTextField().getText();
		}
		return "nu exista";
	}
}
