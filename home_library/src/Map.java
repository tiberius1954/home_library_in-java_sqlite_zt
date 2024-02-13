
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class Map extends JFrame{
	JLabel alap;
	JButton gomb;
	Color lskek = new Color(1, 1, 99);
	Border borderp = BorderFactory.createLineBorder(Color.RED, 1);
	Container fr; 

	public Map() {
		setLayout(null);	
		setBounds(10,10,860,630);	
		setLocationRelativeTo(null);	
		fr = this.getContentPane();
		fr.setBackground(lskek);			
		alap = new JLabel();
		alap.setBounds(20,20,805,550);
		alap.setBorder(borderp);
		add(alap);
		alap.setIcon(new ImageIcon(getClass().getResource("Images/map.png")));		
		setVisible(true);	
		}
}
