
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;



public class Lib {
	Font textf = new Font("Tahoma", Font.PLAIN, 16);
	 Color skek = new Color(39, 50, 110);
	 Color slilla= new Color(204,0,153);
	 Color szold = new Color(0, 121, 104);
	 Color narancs = new Color(254, 179, 0);
	 Color homok = new Color(200, 137, 60);
	 Color vbarna = new Color(223, 196, 155);
	 Color narancs1 = new Color(251,191,99);
	 Color piros = new Color(249,73,58);
	Color vkek = new Color(3,135,174);
	Color citrom = new Color(254,255,3);
	//************************************
	Color lnarancs = new Color(255, 142, 1);
	 Color lskek = new Color(1,1,99);
	 Color lcitrom = new Color(255,237,112);	 
	 Color lvzold = new Color(0,216,169);	 
	 Color lvrozsasz = new Color(212,183,185);
	 Color lvkek = new Color(0,163,55);
	 Color lkkek = new Color(0,89,255);
	 Color lerozsaszin = new Color(243,78,118);
	 Border myRaisedBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.BLACK);
//************************************
	 public JLabel dlabel(String string){
			JLabel  llabel = new JLabel(string);
		//	llabel.setFont(new Font("serif", Font.BOLD, 20));	
			llabel.setFont(new java.awt.Font("Tahoma", 1 , 16)); 
			llabel.setForeground(lnarancs);	
			//llabel.setForeground(szold);
			llabel.setBackground(lskek);
			 llabel.setPreferredSize( new Dimension( 100, 30));
			return llabel;
			}	
	 
	public JLabel clabel(String string){
		JLabel  llabel = new JLabel(string);
	//	llabel.setFont(new Font("serif", Font.BOLD, 20));	
		llabel.setFont(new java.awt.Font("Tahoma", 0 , 16)); 
		llabel.setForeground(new java.awt.Color(0, 102, 102));	
		//llabel.setForeground(szold);
		//llabel.setBackground(Color.black);
		 llabel.setPreferredSize( new Dimension( 100, 30));
		return llabel;
		}	
	public JLabel cplabel(String string){
		JLabel  llabel = new JLabel(string);
		llabel.setFont(new Font("serif", Font.BOLD, 20));	
		llabel.setForeground(narancs);
		//llabel.setBackground(Color.black);
		 llabel.setPreferredSize( new Dimension( 100, 30));
		return llabel;
		}	
	public JLabel cmlabel(String string){
		JLabel  llabel = new JLabel(string);
		llabel.setFont(new Font("serif", Font.BOLD, 20));	
		llabel.setForeground(citrom);
		//llabel.setBackground(Color.black);
		 llabel.setPreferredSize( new Dimension( 100, 30));
		return llabel;
		}	
	public JLabel cblabel(String string){
		JLabel  llabel = new JLabel(string);
		llabel.setFont(new Font("serif", Font.BOLD, 20));	
		llabel.setForeground(vbarna);
		//llabel.setBackground(Color.black);
		 llabel.setPreferredSize( new Dimension( 100, 30));
		return llabel;
		}	
	public JLabel flabel(String string){
		JLabel  llabel = new JLabel(string);
		llabel.setFont(new Font("serif",Font.BOLD,30));	
		llabel.setFont(new java.awt.Font("Lucida Handwriting",0, 30));
		llabel.setForeground(new java.awt.Color(0, 102, 102));	
		return llabel;
	}
	public JLabel fblabel(String string){
		JLabel  llabel = new JLabel(string);
		llabel.setFont(new Font("serif",Font.BOLD,30));	
	  //  llabel.setBackground(Color.YELLOW);
		llabel.setForeground(homok);		
		return llabel;
	}
	public JLabel fklabel(String string){
		JLabel  llabel = new JLabel(string);
		llabel.setFont(new Font("serif",Font.BOLD,30));	
	  //  llabel.setBackground(Color.YELLOW);
		llabel.setForeground(narancs1);		
		return llabel;
	}
	public JLabel fmlabel(String string){
		JLabel  llabel = new JLabel(string);
		llabel.setFont(new Font("serif",Font.BOLD,30));	
	  //  llabel.setBackground(Color.YELLOW);
		llabel.setForeground(citrom);		
		return llabel;
	}

public JButton cbutton(String string){
JButton bbutton = new JButton(string);
bbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
// bbutton.setFont(new Font("Tahoma", Font.BOLD, 16));
 bbutton.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16)); 
 //bbutton.setBackground(new Color(0, 102, 102));
 bbutton.setBackground(lerozsaszin);
 bbutton.setForeground(new java.awt.Color(255, 255, 255));
 bbutton.setBorder(myRaisedBorder);
//bbutton.setBackground(Color.BLACK);
//bbutton.setForeground(Color.WHITE);
bbutton.setPreferredSize( new Dimension( 100, 30));
return bbutton;
}
public JButton ckbutton(String string){
JButton bbutton = new JButton(string);
// bbutton.setFont(new Font("Tahoma", Font.BOLD, 16));
 bbutton.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16)); 
 bbutton.setBackground(vkek);
//bbutton.setBackground(Color.BLACK);
bbutton.setForeground(Color.BLACK);
bbutton.setPreferredSize( new Dimension( 100, 50));
bbutton.setFocusPainted(false);
return bbutton;
}
public JTextField cTextField(int hossz) {
	JTextField textField = new JTextField(hossz);
	// textField.setFont(textf);
	textField.setFont(new Font("Tahoma", 0, 16));
	// textField.setBorder(borderf);
	textField.setPreferredSize(new Dimension(250, 30));
	textField.setCaretColor(Color.RED);
	textField.putClientProperty("caretAspectRatio", 0.3);
	textField.setBackground(lvrozsasz);
	textField.setForeground(Color.BLACK);			
	// textField.setHorizontalAlignment(JTextField.RIGHT)
//	textField.addFocusListener(dFocusListener);
	return textField;
}
public JPasswordField cPasswordField(int hossz) {
	JPasswordField pField = new JPasswordField(hossz);
	//pField.setFont(textf);
	//pField.setBorder(borderf);
	pField.setPreferredSize(new Dimension(250, 30));
	pField.setCaretColor(Color.RED);
	pField.putClientProperty("caretAspectRatio", 0.3);
	pField.setBackground(lvrozsasz);
	pField.setForeground(Color.BLACK);	
	// textField.setHorizontalAlignment(JTextField.RIGHT)
   // pField.addFocusListener(pFocusListener);
	return pField;
} 

public TitledBorder ctBorder(String title, Color szin) {
 	 // Border a = BorderFactory.createLineBorder(Color.black);
 	  Border a = new LineBorder(new Color(0, 102, 102),1);
 	  TitledBorder b = BorderFactory.createTitledBorder(a,"");
 	  b.setTitle(title);
 	  b.setTitleColor(szin);
	  b.setTitleFont(new Font("Arial", Font.ITALIC, 14));
 	//  b.setTitleJustification(TitledBorder.DEFAULT_JUSTIFICATION);
 	  b.setTitleJustification(TitledBorder.LEFT);
 	 // b.setTitlePosition(TitledBorder.CENTER);
 	  b.setTitlePosition(TitledBorder.LEFT);
 	 b.setTitlePosition(TitledBorder.TOP);
 	 // b.setTitlePosition(TitledBorder.ABOVE_TOP); 	
 	  return b;
 	}



public boolean isNumeric(String str){
    return str != null && str.matches("[0-9.]+");
}
public boolean isNullOrEmpty(String s) {
	Boolean vissza = false;
	if (s == null ) {
		vissza = true;
	}else if (s.trim().length()== 0 ) {
		vissza = true;
	}else if (s == "null"){
		vissza = true;
	}
   // return s == "null" || s.length() == 0 || s == null;
	return vissza;
}

public void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
		double... percentages) {
	double total = 0;
	for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
		total += percentages[i];
	}
	for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
		TableColumn column = table.getColumnModel().getColumn(i);
		if (percentages[i] > 0.0) {
			int seged = (int) (tablePreferredWidth * (percentages[i] / total));
			column.setPreferredWidth(seged);
		} else {
			// column.setPreferredWidth(0);
			column.setMinWidth(0);
			column.setMaxWidth(0);
			column.setWidth(0);

		}
		// column.setPreferredWidth((int)(tablePreferredWidth *
		// (percentages[i] / total)));
	}
}
public static final String capitalize(String str)   
{  
   if (str == null || str.length() == 0) return str;  
   return str.substring(0, 1).toUpperCase() + str.substring(1);  
}  
public String igennemkerdes(String kerdes, String fejlec){
	String valasz;
	 Object[] options = {"Igen", "Nem"};
     int res = JOptionPane.showOptionDialog(null, kerdes, fejlec,
 	JOptionPane.YES_NO_OPTION, 
 	JOptionPane.QUESTION_MESSAGE,null, //nem haszn�lunk k�l�n ikont
 	 options, //A gombok feliratai
      options[0]); //Az alap�rtelmezett gomb felirata
     if (res == JOptionPane.YES_OPTION)
           valasz = "I";
      else
          valasz = "N";
  return valasz;
}

public void szuzenet(String uzenet, String fejlec)
{
	JOptionPane.showMessageDialog(null, uzenet, fejlec,
		    JOptionPane.INFORMATION_MESSAGE);	
	// JOptionPane.INFORMATION_MESSAGE
				//  JOptionPane.showMessageDialog(null,"java is fun","Title",1);
	//ERROR_MESSAGE,
	//INFORMATION_MESSAGE,
	//WARNING_MESSAGE,
	//QUESTION_MESSAGE, 
	//PLAIN_MESSAGE	
}


}

//      TitledBorder pan5 = BorderFactory.createTitledBorder(slimBorder, "Search", 
//		TitledBorder.DEFAULT_JUSTIFICATION,
//		TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 0, 16));
//    //	pan5.setTitleColor(new Color(0, 102, 102));
