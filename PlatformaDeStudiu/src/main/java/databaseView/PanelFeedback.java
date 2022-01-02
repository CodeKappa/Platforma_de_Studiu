package databaseView;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class PanelFeedback extends JPanel{
	public String feedbackMessage = "Bine te-ai intors";
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.BLUE);
       // g.fillRect(0, 0, 100, 100);
        g.drawString(feedbackMessage, 10, 10);
		setBorder(new LineBorder(Color.BLACK, 1));
    }
    
    public void reset()
    {
    	feedbackMessage = "Bine te-ai intors";
    }
}
