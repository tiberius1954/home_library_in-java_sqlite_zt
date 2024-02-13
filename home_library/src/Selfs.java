import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DAO.DatabaseHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Selfs extends JFrame {
	Connection con;
	Statement stmt; 
	PreparedStatement pst;
	ResultSet rs;
	Lib zt = new Lib();	
	OwnClass saj = new OwnClass();
    Bookhelper ph = new Bookhelper();
    ztable self_table;	
	private String rowid = "";
	private int myrow = 0;
	
	Selfs(){
		initComponents();
		table_update();
	}
	private void initComponents() {
		this.setBackground(ph.skek);
		Container cp = getContentPane();	
		cp.setBackground(ph.skek);
		this.setLayout(null);
		this.setBackground(ph.skek);
		setBounds(10, 10, 720, 560);
		setLocationRelativeTo(null);
	
		 addWindowListener(new WindowAdapter() {
		       public void windowClosing(WindowEvent windowEvent){
		 //     	 MainFrame ob = new MainFrame();
		//      	 ob.setVisible(true);
		         dispose();
		       }        
		    });  

		   ImageIcon ImageIcon= new ImageIcon(getClass().getResource("Images/lib32.png"));
		    Image Image = ImageIcon.getImage();
		    this.setIconImage(Image);	
		
		lbheader = ph.flabel("Selfs");
		lbheader.setBounds(330, 10, 200, 40);
		add(lbheader);
		datapanel = new JPanel(null);
	    datapanel.setBackground(ph.skek);
		datapanel.setBounds(30, 60, 330, 420);
		datapanel.setBorder(ph.ctBorder("Data", ph.narancs));
		tablepanel = new JPanel(null);		
		tablepanel.setBackground(ph.skek);	
		tablepanel.setBounds(361, 60, 260,420);
		tablepanel.setBorder(ph.ctBorder("Table", ph.narancs));
		add(datapanel);
		add(tablepanel);		
		
		lbname = ph.cplabel("Name :");
		lbname.setBounds(30,80,65,30);
		datapanel.add(lbname);
		
		txtname = cTextField(40);
		txtname.setBounds(105,80,200,30);
		datapanel.add(txtname);	
		txtname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (txtname.getText().length() == 0) { 
				   if (Character.isLowerCase(keyChar)) 				
				       e.setKeyChar(Character.toUpperCase(keyChar));				
				}}		
				});
		
		   btnadd = zt.cbutton("Save");
					btnadd.setBounds(140,150 , 130, 30);	
					btnadd.setBackground(ph.piros);
					datapanel.add(btnadd);
					
					btnadd.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent evt) {
							 addActionPerformed(evt);
						}
					});
					
					btncancel= zt.cbutton("Cancel");		
					btncancel.setBounds(140, 200, 130, 30);
					btncancel.setBackground(ph.vkek);
					datapanel.add(btncancel);
					btncancel.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent evt) {
							cancelActionPerformed(evt);
						}
					});
				
					btndelete = zt.cbutton("Delete");		
					btndelete.setBounds(140, 250, 130, 30);	
					btndelete.setBackground(new Color(0, 102, 102));
					datapanel.add(btndelete);
					btndelete.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent evt) {
							deleteActionPerformed(evt);
						}
					});
					scrPane1 = new JScrollPane();
					scrPane1.setBounds(30, 50, 200, 330);			
			
					self_table = new ztable();
					self_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					self_table.setModel(new DefaultTableModel(new Object[][] {},
							new String[] { "Id", "Name of self" }) {
						Class[] types = new Class[] { Integer.class, String.class};

						@Override
						public Class getColumnClass(int columnIndex) {
							return types[columnIndex];
						}
					});
					saj.headermake(self_table);
				
		            saj.setJTableColumnsWidth(self_table, 200, 0, 100);	
		               self_table.addComponentListener(new ComponentAdapter() {
		                public void componentResized(ComponentEvent e) {
		                    self_table.scrollRectToVisible(self_table.getCellRect(self_table.getRowCount()-1, 0, true));
		                }
		            });
			
					self_table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					self_table.addMouseListener(new java.awt.event.MouseAdapter() {
						@Override
						public void mouseClicked(java.awt.event.MouseEvent evt) {
							self_tableMouseClicked(evt);
						}
					});
					scrPane1.setViewportView(self_table);		
					tablepanel.add(scrPane1);	
				
		setVisible(true);
	}
	
	private void addActionPerformed(java.awt.event.ActionEvent evt) {
		DefaultTableModel d1 = (DefaultTableModel) self_table.getModel();
		String name= txtname.getText();	
		if ( zt.isNullOrEmpty(name)) {			
			 JOptionPane.showMessageDialog(null,"The name is empty !" , "Error",1);
	       return;
		}	
		try {
			con = DatabaseHelper.getConnection();
			if (rowid != "") {		
				pst = con.prepareStatement("update selfs set name=? where fid= ?");
				pst.setString(1, name);					
				pst.setString(2, rowid);				
				d1.setValueAt(name, myrow, 1);			
		   }else {		
			   pst = con.prepareStatement("insert into selfs ( name) values (?) ");
				pst.setString(1, name);						   
		   }	
			pst.executeUpdate();	
			pst.close();
			con.close();			
			if (rowid == "") { 	      
				int myid = ph.table_maxid("SELECT MAX(fid) AS max_id FROM selfs");
				d1.insertRow(d1.getRowCount(), new Object[]  {myid, name});  
 	             saj.gotolastrow(self_table);
			}					
			txtclear();	
			ph.ztmessage("Success", "Message");
		} catch (SQLException ex) {		
			System.err.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	private void deleteActionPerformed(java.awt.event.ActionEvent evt) {
		String fid;
		DefaultTableModel d1 = (DefaultTableModel) self_table.getModel();
		myrow =self_table.getSelectedRow();
		  if (myrow < 0) {
		  	   return;
		  	  }  	  
		  if ( d1.getValueAt(myrow, 0) == null ) {
				return;
			}
		  fid = d1.getValueAt(myrow, 0).toString(); 	
		  String name = d1.getValueAt(myrow, 1).toString().trim();
		  String ss = "select self  from books  where self ='"+ name+"'";
		  if (ph.cannotdelete("select self  from books  where self ='"+ name+"'")==true) {
				 JOptionPane.showMessageDialog(null, "You can not delete this self !");
				 return;
			}		
		int a = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?");
		if (a == JOptionPane.YES_OPTION) {	
			try {
				con = DatabaseHelper.getConnection();
				pst = con.prepareStatement("delete from selfs where fid =?");
				pst.setString(1, fid);
				pst.executeUpdate();			
				pst.close();
				con.close();
				d1.removeRow(myrow);		
				txtclear();					
				ph.ztmessage("Success", "Message");

			} catch (SQLException ex) {				
				System.err.println("SQLException: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	private void cancelActionPerformed(java.awt.event.ActionEvent evt) {		
	    txtclear();
	}	
	
	private void self_tableMouseClicked(java.awt.event.MouseEvent evt) {
		DefaultTableModel d1 = (DefaultTableModel) self_table.getModel();
		myrow = self_table.getSelectedRow();			
		if ( d1.getValueAt(myrow, 0) == null ) {
			return;
		}
		if (myrow > -1) {
		rowid= d1.getValueAt(myrow, 0).toString();		
		txtname.setText(d1.getValueAt(myrow, 1).toString());		
		}
	}
	private void table_update() {
		try {
			con = DatabaseHelper.getConnection();
			pst = con.prepareStatement("select * from selfs order by name");
			ResultSet rs = pst.executeQuery();
			DefaultTableModel d = (DefaultTableModel) self_table.getModel();
			d.setRowCount(0);
			int sor = 0;
			while (rs.next()) {
				sor++;				
				Vector <String> v2 = new Vector<>();
					v2.add(rs.getString("fid"));
					v2.add(rs.getString("name"));				
				    d.addRow(v2);
			}
		//	saj.uressorok(self_table, sor, 10);
			rs.close();
			pst.close();
			con.close();
		} catch (SQLException ex) {
					System.err.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		}		
	}
	private void 	txtclear() {	
		txtname.setText("");	
		txtname.requestFocus();
		rowid="";
		myrow=0;
		}	
	public JTextField cTextField(int hossz) {
		JTextField textField = new JTextField(hossz);
		textField.setFont(ph.textf);
		textField.setBorder(ph.borderf);
		textField.setBackground(ph.vhzold);
		textField.setPreferredSize(new Dimension(250, 30));
		textField.setCaretColor(Color.RED);
		textField.putClientProperty("caretAspectRatio", 0.1);
		// textField.setHorizontalAlignment(JTextField.RIGHT)
		textField.setText("");
	//	textField.addFocusListener(dFocusListener);
		return textField;
	}
	
	
	public static void main(String args[]) {
		new Selfs().setVisible(true);
	}
	
	JPanel datapanel, tablepanel;
	JLabel  lbname, lbnationality, lbheader;
	JTextField txtname, txtnationality,txtsearch;	
	JButton btnadd,  btndelete, btncancel, btndel;
	JScrollPane scrPane1;
	

}
