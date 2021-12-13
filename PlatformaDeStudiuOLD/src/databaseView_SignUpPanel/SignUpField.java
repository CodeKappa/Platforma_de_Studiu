package databaseView_SignUpPanel;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class SignUpField
{
	private JLabel label;
	private JTextField textField;
	
	private SignUpLabelMap lm = new SignUpLabelMap();
	
	public static final int lwidth = 200;
	public static final int tfwidth = 200;
	public static final int lheight = 20;
	public static final int tfHeight = 25;
		
	public SignUpField(SIGNUP_LABEL label)
	{
		this.label = new JLabel(lm.getLabelName(label));
		this.textField = new JTextField();
	}

	public JLabel getLabel() {
		return label;
	}

	public JTextField getTextField() {
		return textField;
	}
}