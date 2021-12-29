package databaseView;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import main.MainClass;

public class CustomWindowListener implements WindowListener{

	@Override
	public void windowClosing(WindowEvent e) {
		try{MainClass.db.closeConnection();}
		catch (Exception ex) { ex.printStackTrace();}	
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub	
	}

}
