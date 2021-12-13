package databaseView_SignUpPanel;

import java.util.HashMap;

public class SignUpLabelMap {
	private static HashMap<SIGNUP_LABEL, String> hm = new HashMap<SIGNUP_LABEL, String>();
	
	public SignUpLabelMap()
	{
		hm.put(SIGNUP_LABEL.NAME, "Name");
		hm.put(SIGNUP_LABEL.PRENUME, "Prenume");
		hm.put(SIGNUP_LABEL.CNP, "CNP");
		hm.put(SIGNUP_LABEL.ADRESS, "Adress");
		hm.put(SIGNUP_LABEL.PHONE, "Adress");
		hm.put(SIGNUP_LABEL.EMAIL, "Email");
		hm.put(SIGNUP_LABEL.IBAN, "IBAN");
		hm.put(SIGNUP_LABEL.PASSWORD, "Password");
		hm.put(SIGNUP_LABEL.PASSWORDCONFIRM, "Confirm Password");
	}

	public String getLabelName(SIGNUP_LABEL label) 
	{
		return hm.get(label);
	}
}
