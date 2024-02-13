import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import DAO.DatabaseHelper;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.Object;

public class Authors extends JFrame {
	Connection con;
	Statement stmt; 
	PreparedStatement pst;
	ResultSet rs;
	Lib zt = new Lib();	
	OwnClass saj = new OwnClass();
    Bookhelper ph = new Bookhelper();
    ztable writer_table;
	private String myhint;
	private String rowid = "";
	private int myrow = 0;
	
	public Authors() {
		initComponents();
		table_update();
		txtclear();
	}
	@SuppressWarnings("deprecation")
	private 	void initComponents() {
		this.setBackground(ph.skek);
		Container cp = getContentPane();	
		cp.setBackground(ph.skek);
		this.setLayout(null);
		this.setBackground(ph.skek);
		setBounds(10, 10, 1200, 620);
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
		
		lbheader = ph.flabel("Authors");
		lbheader.setBounds(570, 10, 200, 40);
		add(lbheader);
		datapanel = new JPanel(null);
	    datapanel.setBackground(ph.skek);
		datapanel.setBounds(30, 60, 460, 480);
		datapanel.setBorder(ph.ctBorder("Data", ph.narancs));
		tablepanel = new JPanel(null);		
		tablepanel.setBackground(ph.skek);	
		tablepanel.setBounds(491, 60, 660, 480);
		tablepanel.setBorder(ph.ctBorder("Table", ph.narancs));
		add(datapanel);
		add(tablepanel);		
		
		lbname = ph.cplabel("Name :");
		lbname.setBounds(30,80,150,30);
		datapanel.add(lbname);
		
		txtname = cTextField(40);
		txtname.setBounds(190,80,250,30);
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

		
		 lbnationality = ph.cplabel("Nationality :");
		 lbnationality.setBounds(30,130,150,30);
		 datapanel.add(lbnationality);
		 
		 txtnationality = cTextField(40);
		 txtnationality.setBounds(190,130,250,30);
		 datapanel.add(txtnationality);
			txtnationality.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();				
					if (txtnationality.getText().length() == 0) { 
					   if (Character.isLowerCase(c)) 				
					       e.setKeyChar(Character.toUpperCase(c));				
					}
					}		
					});
			txtnationality.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
			txtnationality.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();				
					if (txtnationality.getText().length() == 0) { 
					   if (Character.isLowerCase(c)) 				
					       e.setKeyChar(Character.toUpperCase(c));				
					}				
				}
				public void keyPressed(KeyEvent evt) {
					if (evt.getKeyCode() == evt.VK_TAB || evt.getKeyCode() == evt.VK_ENTER) {	
				     txtname.grabFocus();
					}
				}
			});

					
	
		    btnadd = zt.cbutton("Save");
			btnadd.setBounds(240,210 , 130, 30);	
			btnadd.setBackground(ph.piros);
			datapanel.add(btnadd);
			
			btnadd.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					 addActionPerformed(evt);
				}
			});
			
			btncancel= zt.cbutton("Cancel");		
			btncancel.setBounds(240, 270, 130, 30);
			btncancel.setBackground(ph.vkek);
			datapanel.add(btncancel);
			btncancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					cancelActionPerformed(evt);
				}
			});
		
			btndelete = zt.cbutton("Delete");		
			btndelete.setBounds(240, 330, 130, 30);	
			btndelete.setBackground(new Color(0, 102, 102));
			datapanel.add(btndelete);
			btndelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					deleteActionPerformed(evt);
				}
			});

			
			scrPane1 = new JScrollPane();
			scrPane1.setBounds(40, 50, 580, 330);			
	
			writer_table = new ztable();
			writer_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			writer_table.setModel(new DefaultTableModel(new Object[][] {},
					new String[] { "Id", "Author's name", "Nationality" }) {
				Class[] types = new Class[] { Integer.class, String.class, String.class };

				@Override
				public Class getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
			saj.headermake(writer_table);
		
            saj.setJTableColumnsWidth(writer_table, 610, 0, 60, 40);	
           writer_table.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent e) {
                    writer_table.scrollRectToVisible(writer_table.getCellRect(writer_table.getRowCount()-1, 0, true));
                }
            });
	
			writer_table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			writer_table.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					writer_tableMouseClicked(evt);
				}
			});
			scrPane1.setViewportView(writer_table);		
			tablepanel.add(scrPane1);				
			txtsearch = hTextField("You can search here !");		
			txtsearch.setBounds(200, 415, 300, 30);	
		
			tablepanel.add(txtsearch);	
			txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
				@Override
				public void keyReleased(java.awt.event.KeyEvent evt) {
					searchKeyReleased(evt);
				}
			});
			
			btndel = new JButton();
			btndel.setFont(new java.awt.Font("Tahoma", 1, 16));
			btndel.setMargin(new Insets(0, 0, 0, 0));
			btndel.setBounds(500, 415, 25, 28);
			btndel.setText("x");
			tablepanel.add(btndel);
			btndel.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        txtsearch.setText("");
			        txtsearch.requestFocus();
			    	table_update();
			    }
			});
			
		setVisible(true);		
	}
	private void writer_tableMouseClicked(java.awt.event.MouseEvent evt) {
		DefaultTableModel d1 = (DefaultTableModel) writer_table.getModel();
		myrow = writer_table.getSelectedRow();			
		if ( d1.getValueAt(myrow, 0) == null ) {
			return;
		}
		if (myrow > -1) {
		rowid= d1.getValueAt(myrow, 0).toString();		
		txtname.setText(d1.getValueAt(myrow, 1).toString());
		txtnationality.setText(d1.getValueAt(myrow, 2).toString());	
		}
	}
	private void addActionPerformed(java.awt.event.ActionEvent evt) {	
		DefaultTableModel d1 = (DefaultTableModel) writer_table.getModel();	
		String name= txtname.getText();
		String nationality = txtnationality.getText();	
		
			if (authorvalidation(name, true) == false) {
			return;
	  	}			 name = ph.capitalizeWord(name);
		
		try {
			con = DatabaseHelper.getConnection();
			if (rowid != "") {		
				pst = con.prepareStatement("update authors set name=?, nationality=? where szid= ?");
				pst.setString(1, name);
				pst.setString(2, nationality);		
				pst.setString(3, rowid);		
				d1.setValueAt(name, myrow, 1);
				d1.setValueAt(nationality, myrow, 2);	
		   }else {		
			   pst = con.prepareStatement("insert into authors ( name, nationality) values (?,?) ");
				pst.setString(1, name);
				pst.setString(2, nationality);				   
		   }	
			pst.executeUpdate();	
			pst.close();
			con.close();			
			if (rowid == "") { 	     
				int myid = ph.table_maxid("SELECT MAX(szid) AS max_id FROM authors");
				d1.insertRow(d1.getRowCount(), new Object[]  {myid, name,nationality});  
 	             saj.gotolastrow(writer_table);
			}					
			txtclear();	
			ph.ztmessage("Success", "Message");
		} catch (SQLException ex) {		
			System.err.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	private void deleteActionPerformed(java.awt.event.ActionEvent evt) {
		String szid;
		DefaultTableModel d1 = (DefaultTableModel) writer_table.getModel();
		myrow = writer_table.getSelectedRow();
		  if (myrow < 0) {
		  	   return;
		  	  }  	  
		  if ( d1.getValueAt(myrow, 0) == null ) {
				return;
			}
		  szid = d1.getValueAt(myrow, 0).toString(); 		
		  if (ph.cannotdelete("select szid  from books  where szid ="+ szid)==true) {
				 JOptionPane.showMessageDialog(null, "You can not delete this author !");
				 return;
			}		
		int a = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?");
		if (a == JOptionPane.YES_OPTION) {	
			try {
				con = DatabaseHelper.getConnection();
				pst = con.prepareStatement("delete from authors where szid =?");
				pst.setString(1, szid);
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
	private void table_update() {	
		try {
			con = DatabaseHelper.getConnection();
			pst = con.prepareStatement("select * from authors order by name");
			ResultSet rs = pst.executeQuery();
			DefaultTableModel d = (DefaultTableModel) writer_table.getModel();
			d.setRowCount(0);
			int sor = 0;
			while (rs.next()) {
				sor++;				
				Vector <String> v2 = new Vector<>();
					v2.add(rs.getString("szid"));
					v2.add(rs.getString("name"));
					v2.add(rs.getString("nationality"));	
				    d.addRow(v2);
			}
		//	saj.uressorok(writer_table, sor, 10);
			rs.close();
			pst.close();
			con.close();
		} catch (SQLException ex) {
					System.err.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	private void cancelActionPerformed(java.awt.event.ActionEvent evt) {		
	    txtclear();
	}
	
	private void 	txtclear() {	
	txtname.setText("");
	txtnationality.setText("");
	txtname.requestFocus();
	rowid="";
	myrow=0;
	}	
	private boolean authorvalidation(String name, Boolean yes) {
		boolean ret = true;		 
		  ArrayList<String> err = new ArrayList<String>();
		if ( zt.isNullOrEmpty(name)) {
			if (yes== true) {
			 err.add("The name is empty !");
			}
	       ret = false;
		}
		 if (err.size() > 0) {
             JOptionPane.showMessageDialog(null, err.toArray(),"Error",1);					               
         }
		return ret;
	}     	

	private void searchKeyReleased(java.awt.event.KeyEvent evt) {	
      if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
		String szid, name, nationality;
		DefaultTableModel d1 = (DefaultTableModel) writer_table.getModel();
		String query;
		d1.setRowCount(0);
		String txt = txtsearch.getText();	
		if (txt.equals("")) {
		 query ="SELECT szid, name , nationality from author "
				+ " order by name";
			
	   }else{
		   String seg = txtsearch.getText().trim().toLowerCase();
			String szov = "name";
			query ="SELECT szid, name, nationality from  authors " 
				+	" where " + szov +" LIKE '%" + seg + "%'"
			    +   " order by name COLLATE NOCASE ASC";
		}	
		try {			
				con = DatabaseHelper.getConnection();
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);	
				int sor = 0;
				while (rs.next()) {
					sor++;
				szid = rs.getString("szid");
				name = rs.getString("name");
				name = ph.capitalizeWord(name);	
				nationality=rs.getString("nationality");												
				Object[] row = { szid, name, nationality};
				d1.addRow(row);
				}			
		//		saj.uressorok(writer_table, sor, 10);
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}		
      }
	}
	
	private JTextField hTextField(final String hint) {
	       Font gainFont = new Font("Tahoma", Font.BOLD ,16);  
			Font lostFont = new Font("Tahoma", Font.ITALIC, 16); 
			 myhint = hint;
			JTextField textField = new JTextField(hint);
			  textField.setText(hint);  
			  textField.setFont(lostFont);  
			  textField.setForeground(Color.GRAY);	
			  textField.setBackground(ph.vhzold);
		//	textField.setBorder(ph.borderf);
			textField.setBorder(ph.thatBorder1);
			textField.setPreferredSize(new Dimension(250, 30));
			textField.setCaretColor(Color.RED);
			textField.putClientProperty("caretAspectRatio", 0.2);		
			textField.addFocusListener(hFocusListener);
			return textField;	
		}
		
		 private final FocusListener hFocusListener = new FocusListener() {
			  Font gainFont = new Font("Tahoma", Font.BOLD, 16);  
			  Font lostFont = new Font("Tahoma", Font.ITALIC, 16); 
				@Override			
				public void focusGained(FocusEvent e) {
					JTextField Txt = (JTextField) e.getSource();	
				  if (Txt.getText().equals(myhint)) {  
			           Txt.setText("");  
			          Txt.setFont(gainFont);  
			         } else {  
			           Txt.setText(Txt.getText());  
			           Txt.setFont(gainFont);  			          
			         }		
				    Txt.setForeground(Color.BLACK);
				}

				@Override
				public void focusLost(FocusEvent e) {
					JTextField Txt = (JTextField) e.getSource();	
				 if (Txt.getText().equals(myhint)|| Txt.getText().length()==0) {  
			           Txt.setText(myhint);  
			           Txt.setFont(this.lostFont);  
			           Txt.setForeground(Color.GRAY);  
			         } else {  
			           Txt.setText(Txt.getText());  
			           Txt.setFont(gainFont);  
			           Txt.setForeground(Color.BLACK);  
			         } 				
				}
			};	
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
			textField.addFocusListener(dFocusListener);
			return textField;
		}
		private final FocusListener dFocusListener = new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				JComponent c = (JComponent) e.getSource();
				c.setBorder(ph.borderz);
			}

			@Override
			public void focusLost(FocusEvent e) {		
				String tartalom = "";
				boolean vissza = true;
				JTextField Txt = (JTextField) e.getSource();
				tartalom = Txt.getText();
				if (Txt == txtname) {
					Txt.setText(tartalom);
					vissza = authorvalidation(tartalom, false);
				}				
				if (vissza == true) {
					Txt.setBorder(ph.borderf);
				} else {
					Txt.setBorder(ph.borderp);
				}
			}
		};

	public static void main(String args[]) {
		new Authors().setVisible(true);
	}
	JPanel datapanel, tablepanel;
	JLabel  lbname, lbnationality, lbheader;
	JTextField txtname, txtnationality,txtsearch;	
	JButton btnadd,  btndelete, btncancel, btndel;
	JScrollPane scrPane1;	

}
