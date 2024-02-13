import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Connection;
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

//import javax.swing.table.JTableHeader;
import DAO.DatabaseHelper;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
//import java.awt.event.*;
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
import javax.swing.plaf.ColorUIResource;

public class Books extends JFrame {
	Connection con;
	Statement stmt;
	PreparedStatement pst;
	ResultSet rs;
	Lib zt = new Lib();
	OwnClass saj = new OwnClass();
	Bookhelper ph = new Bookhelper();
	private String myhint;
	ztable book_table;
	Color lpiroska = new Color(255, 51, 51);
	Color feher = new Color(255, 255, 255);
	Color homok = new Color(242, 242, 189);
	Color lpiros = new Color(255, 102, 75);
	private String rowid = "";
	private int myrow = 0;

	public Books() {
		UIManager.put("ComboBox.selectionBackground", lpiros);
		UIManager.put("ComboBox.selectionForeground", feher);
		UIManager.put("ComboBox.background", new ColorUIResource(homok));
		UIManager.put("ComboBox.foreground", Color.BLACK);
		UIManager.put("ComboBox.border", new LineBorder(Color.green, 1));

		initComponents();
		ph.Author(cmbauthors);
		cmbauthors.setSelectedIndex(-1);
		table_update("");
		txtclear();
	}

	private void initComponents() {
		this.setBackground(ph.skek);
		Container cp = getContentPane();
		cp.setBackground(ph.skek);
		this.setLayout(null);
		this.setBackground(ph.skek);
		setBounds(10, 10, 1200, 620);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
//		      	 MainFrame ob = new MainFrame();
//		      	 ob.setVisible(true);
				dispose();
			}
		});

		ImageIcon ImageIcon = new ImageIcon(getClass().getResource("Images/lib32.png"));
		Image Image = ImageIcon.getImage();
		this.setIconImage(Image);
		lbheader = ph.flabel("Books");
		lbheader.setBounds(570, 10, 200, 40);
		add(lbheader);

		datapanel = new JPanel(null);
		datapanel.setBackground(ph.skek);
		datapanel.setBounds(30, 60, 430, 490);
		datapanel.setBorder(ph.ctBorder("Data", ph.narancs));
		tablepanel = new JPanel(null);
		tablepanel.setBackground(ph.skek);
		tablepanel.setBounds(461, 60, 690, 490);
		tablepanel.setBorder(ph.ctBorder("Table", ph.narancs));
		add(datapanel);
		add(tablepanel);

		lbauthor = zt.cplabel("Author :");
		lbauthor.setBounds(30, 80, 80, 30);
		datapanel.add(lbauthor);

		cmbauthors = new JComboBox<>();
		cmbauthors.setFont(new Font("Tahoma", Font.BOLD, 16));
		cmbauthors.setBounds(120, 80, 280, 30);
		datapanel.add(cmbauthors);

		btnnewauthor = zt.cbutton("New author");
		btnnewauthor.setBounds(270, 130, 130, 30);
		btnnewauthor.setBackground(ph.zold);
		btnnewauthor.setFocusable(false);
		datapanel.add(btnnewauthor);

		btnnewauthor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// ujszerzoActionPerformed(evt);
				NewAuthor ob = new NewAuthor(cmbauthors);
				ob.setVisible(true);
				// setVisible(false);
			}
		});

		lbtitle = ph.cplabel("Title :");
		lbtitle.setBounds(30, 180, 80, 30);
		datapanel.add(lbtitle);

		txttitle = cTextField(40);
		txttitle.setBounds(120, 180, 280, 30);
		datapanel.add(txttitle);
		txttitle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (txttitle.getText().length() == 0) {
					if (Character.isLowerCase(keyChar))
						e.setKeyChar(Character.toUpperCase(keyChar));
				}
			}
		});

		lbselfs = zt.cplabel("Selfs :");
		lbselfs.setBounds(30, 230, 80, 30);
		datapanel.add(lbselfs);

	
		cmbselfs = new JComboBox<>();
		cmbselfs.setBounds(120, 230, 280, 30);
		cmbselfs.setFont(new Font("Tahoma", Font.BOLD, 16));
		datapanel.add(cmbselfs);
		ph.selfcombofill(cmbselfs);

		lbgenre = zt.cplabel("Genre :");
		lbgenre.setBounds(30, 280, 80, 30);
		datapanel.add(lbgenre);

		txtgenre = cTextField(40);
		txtgenre.setBounds(120, 280, 280, 30);
		datapanel.add(txtgenre);
		
		// this is working with VK_TAB
		txtgenre.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		txtgenre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();				
				if (txtgenre.getText().length() == 0) { 
				   if (Character.isLowerCase(c)) 				
				       e.setKeyChar(Character.toUpperCase(c));				
				}				
			}
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == evt.VK_TAB || evt.getKeyCode() == evt.VK_ENTER) {	
			     txttitle.grabFocus();
				}
			}
		});
		
		btnadd = zt.cbutton("Save");
		btnadd.setBounds(200, 330, 130, 30);
		btnadd.setBackground(ph.piros);
		datapanel.add(btnadd);
		btnadd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				addActionPerformed(evt);
			}
		});

		btncancel = zt.cbutton("Cancel");
		btncancel.setBounds(200, 380, 130, 30);
		btncancel.setBackground(ph.vkek);
		datapanel.add(btncancel);
		btncancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				elvetActionPerformed(evt);
			}
		});

		btndelete = zt.cbutton("Delete");
		btndelete.setBounds(200, 430, 130, 30);
		btndelete.setBackground(new Color(0, 102, 102));
		datapanel.add(btndelete);
		btndelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				deleteActionPerformed(evt);
			}
		});

		scrPane1 = new JScrollPane();
		scrPane1.setBounds(20, 50, 650, 355);

		book_table = new ztable();
		book_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		book_table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Title", "Author", "Self", "Genre", "szid" }) {
			Class[] types = new Class[] { Integer.class, String.class, String.class, String.class, String.class,
					Integer.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});

		saj.headermake(book_table);

		saj.setJTableColumnsWidth(book_table, 610, 0, 40, 30, 8, 14, 0);
		book_table.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				book_table.scrollRectToVisible(book_table.getCellRect(book_table.getRowCount() - 1, 0, true));
			}
		});

		book_table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		book_table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				book_tableMouseClicked(evt);
			}
		});
		scrPane1.setViewportView(book_table);

		tablepanel.add(scrPane1);

		txtsearch = hTextField("You can search here !");
		txtsearch.setBounds(200, 430, 300, 30);
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
		btndel.setBounds(500, 431, 25, 28);
		btndel.setText("x");
		tablepanel.add(btndel);
		btndel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtsearch.setText("");
				txtsearch.requestFocus();
				table_update("");
			}
		});

		setVisible(true);
	}

	private void table_update(String mysql) {
		int j;
		String sql;
		if (zt.isNullOrEmpty(mysql)) {
			sql = "select k.kid, k.title, s.name, k.self, k.genre, k.szid  from books k "
					+ " left join authors s  on k.szid = s.szid " + " order by kid COLLATE NOCASE ASC";

		} else {
			sql = mysql;
		}
		try {
			con = DatabaseHelper.getConnection();
			pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			DefaultTableModel d = (DefaultTableModel) book_table.getModel();
			d.setRowCount(0);
			int sor = 0;
			while (rs.next()) {
				sor++;			
				Vector <String> v2 = new Vector<>();
				v2.add(rs.getString("kid"));
				v2.add(rs.getString("title"));
				v2.add(rs.getString("name"));
				v2.add(rs.getString("self"));
				v2.add(rs.getString("genre"));
				v2.add(rs.getString("szid"));
				d.addRow(v2);
			}
	//		saj.uressorok(book_table, sor, 11);
			rs.close();
			pst.close();
			con.close();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	private void elvetActionPerformed(java.awt.event.ActionEvent evt) {
		txtclear();
	}

	private void txtclear() {
		txttitle.setText("");
		txtgenre.setText("");
		cmbselfs.setSelectedIndex(-1);
		cmbauthors.setSelectedIndex(-1);
		cmbauthors.requestFocus();
		rowid = "";
		myrow = 0;
	}

	public JTextField cTextField(int hossz) {
		JTextField textField = new JTextField(hossz);
		textField.setFont(ph.textf);
		textField.setBorder(ph.borderf);
		textField.setBackground(ph.vhzold);
		textField.setPreferredSize(new Dimension(250, 30));
		textField.setCaretColor(Color.RED);
		textField.putClientProperty("caretAspectRatio", 0.1);
		textField.setText("");
		// textField.setHorizontalAlignment(JTextField.RIGHT)
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
			if (Txt == txttitle) {
				Txt.setText(tartalom);
				vissza = bookvalid(tartalom);
			}
			if (vissza == true) {
				Txt.setBorder(ph.borderf);
			} else {
				Txt.setBorder(ph.borderp);
			}
		}
	};

	private JTextField hTextField(final String hint) {
		Font gainFont = new Font("Tahoma", Font.BOLD, 16);
		Font lostFont = new Font("Tahoma", Font.ITALIC, 16);	
		myhint = hint;
		JTextField textField = new JTextField(hint);
		textField.setText(hint);
		textField.setFont(lostFont);
		textField.setForeground(Color.GRAY);
		textField.setBackground(ph.vhzold);	
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
			if (Txt.getText().equals(myhint) || Txt.getText().length() == 0) {
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

	private void searchKeyReleased(java.awt.event.KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
			String query;
			String txt = txtsearch.getText();
			if (txt.equals("")) {
				table_update("");
			} else {
				String seg = txtsearch.getText().trim().toLowerCase();
				String szov = "k.title";
				query = "select k.kid, k.title, s.name, k.self, k.genre, k.szid  from books k "
						+ " left join authors s  on k.szid = s.szid  where " + szov + " LIKE '%" + seg
						+ "%'  order by k.kid COLLATE NOCASE ASC";
				table_update(query);
			}
		}
	}

	private void deleteActionPerformed(java.awt.event.ActionEvent evt) {
		String kid;
		DefaultTableModel d1 = (DefaultTableModel) book_table.getModel();
		myrow = book_table.getSelectedRow();
		 if (myrow < 0) {
		  	   return;
		  	  }  	  
		  if ( d1.getValueAt(myrow, 0) == null ) {
				return;
			}		
		  kid = d1.getValueAt(myrow, 0).toString(); 
	
		int a = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?");
		if (a == JOptionPane.YES_OPTION) {	
			try {
				con = DatabaseHelper.getConnection();
				pst = con.prepareStatement("delete from books where kid =?");
				pst.setString(1, kid);
				pst.executeUpdate();
				pst.close();
				con.close();
				d1.removeRow(myrow);
				txtclear();			
				ph.ztmessage("Book deleted !", "Message");
			} catch (SQLException ex) {
				System.err.println("SQLException: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

	private void addActionPerformed(java.awt.event.ActionEvent evt) {
		int id = 0;
		int myid = 0;
		String title = txttitle.getText();
		String genre = txtgenre.getText();
		int seged = cmbauthors.getSelectedIndex();
		String name = "";
		if (seged > 0) {
			name = ((Authoritem) cmbauthors.getSelectedItem()).getName();
		}
		int szid = ((Authoritem) cmbauthors.getSelectedItem()).getId();
		String self = (String) cmbselfs.getSelectedItem();

		if (bookvalidation(title, name, self) == false) {
			return;
		}	
		try {
			con = DatabaseHelper.getConnection();
			if (rowid != "") {	
				int sIndex = book_table.getSelectedRow();
				pst = con.prepareStatement(
						"update books set title=?, self =?,  genre = ?, " + "  szid = ?  where kid= ?");
				pst.setString(1, title);
				pst.setString(2, self);
				pst.setString(3, genre);
				pst.setInt(4, szid);
				pst.setString(5, rowid);		
				DefaultTableModel d1 = (DefaultTableModel) book_table.getModel();
				d1.setValueAt(title, myrow, 1);
				d1.setValueAt(name, myrow, 2);
				d1.setValueAt(self, myrow, 3);
				d1.setValueAt(genre, myrow, 4);
				d1.setValueAt(szid, myrow, 5);
			} else {		
				pst = con.prepareStatement("insert into books (title, self, genre, szid) values (?,?,?,?) ");
				pst.setString(1, title);
				pst.setString(2, self);
				pst.setString(3, genre);
				pst.setInt(4, szid);
			}
			pst.executeUpdate();		
			pst.close();
			con.close();
			if (rowid=="") {
				table_update("");
			}
			txtclear();
			ph.ztmessage("Success", "Message");
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	private void book_tableMouseClicked(java.awt.event.MouseEvent evt) {
		DefaultTableModel d1 = (DefaultTableModel) book_table.getModel();
		myrow = book_table.getSelectedRow();
		if (d1.getValueAt(myrow, 0) == null) {
			return;
		}
		if (myrow > -1) {
			rowid = d1.getValueAt(myrow, 0).toString();
			txttitle.setText(d1.getValueAt(myrow, 1).toString());
			String self = d1.getValueAt(myrow, 3).toString();
			if (d1.getValueAt(myrow, 2) != null) {
				String cnum = d1.getValueAt(myrow, 5).toString();
				int number = 0;
				if (!zt.isNullOrEmpty(cnum)) {
					number = Integer.parseInt(cnum);
				}
				ph.setSelectedValue(cmbauthors, number);
				cmbauthors.updateUI();
			}
			cmbselfs.getModel().setSelectedItem(self);		
		}else {
			cmbauthors.setSelectedIndex(-1);
		}	
	}

	public void combofriss(int szam, String text) {
		cmbauthors.requestFocus();
		Authoritem A = new Authoritem(szam, text);		
		cmbauthors.getModel().setSelectedItem(A);	
	}

	private Boolean bookvalid(String cim) {
		Boolean vissza = true;
		if (zt.isNullOrEmpty(cim)) {
			vissza = false;
		}
		return vissza;
	}
	
	private Boolean bookvalidation(String cim, String nev, String polc) {
		Boolean vissza = true;
		ArrayList<String> err = new ArrayList<String>();
		if (zt.isNullOrEmpty(cim)) {
			err.add("The title is empty !");
			vissza = false;
		}
		if (zt.isNullOrEmpty(nev)) {
			err.add("The author is empty !");
			vissza = false;
		}
		if (zt.isNullOrEmpty(polc)) {
			err.add("Self is empty !");
			vissza = false;
		}
		if (err.size() > 0) {
			JOptionPane.showMessageDialog(null, err.toArray(), "Error", 1);
		}
		return vissza;
	}

	public static void main(String args[]) {
		new Books();
		// .setVisible(true);
	}	

	JPanel datapanel, tablepanel;
	JLabel lbauthor, lbselfs, lbaddress, lbgenre, lbheader, lbtitle;
	JTextField txttitle, txtgenre, txtsearch;
	JComboBox cmbauthors;
	JComboBox<String> cmbselfs;
	String[] polcstring;
	JButton btnadd, btndelete, btncancel, btnnewauthor, btndel;
	JScrollPane scrPane1;
}



