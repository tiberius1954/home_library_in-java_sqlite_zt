import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import DAO.DatabaseHelper;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.Object;
import javax.swing.plaf.ColorUIResource;

public class Information extends JFrame {
	Connection con;
	Statement stmt;
	PreparedStatement pst;
	ResultSet rs;
	Lib zt = new Lib();
	OwnClass saj = new OwnClass();
	Bookhelper ph = new Bookhelper();
	ztable qtable;
	Color lpiroska = new Color(255, 51, 51);
	Color feher = new Color(255, 255, 255);
	Color homok = new Color(242, 242, 189);
	Color lpiros = new Color(255, 102, 75);
	private String myhint;
	int hit;

	public Information() {

		UIManager.put("ComboBox.selectionBackground", lpiros);
		UIManager.put("ComboBox.selectionForeground", feher);
		UIManager.put("ComboBox.background", new ColorUIResource(homok));
		UIManager.put("ComboBox.foreground", Color.BLACK);
		UIManager.put("ComboBox.border", new LineBorder(Color.green, 1));
	
		initComponents();
		ph.Author(cmbauthors);
		cmbauthors.setSelectedIndex(-1);
		cmbselfs.setSelectedIndex(-1);
		table_update("");
		txtclear();
	}

	private void initComponents() {
		this.setBackground(ph.skek);
		Container cp = getContentPane();
		cp.setBackground(ph.skek);
		this.setLayout(null);
		this.setBackground(ph.skek);
		setBounds(10, 10, 1180, 620);
		setLocationRelativeTo(null);
	
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				// MainFrame ob = new MainFrame();
				// ob.setVisible(true);
				dispose();
			}
		});

		ImageIcon ImageIcon = new ImageIcon(getClass().getResource("Images/lib32.png"));
		Image Image = ImageIcon.getImage();
		this.setIconImage(Image);
		lbheader = ph.flabel("Informations");
		lbheader.setBounds(490, 10, 200, 40);
		add(lbheader);
		datapanel = new JPanel(null);
		datapanel.setBackground(ph.skek);
		datapanel.setBounds(30, 60, 1100, 80);
		datapanel.setBorder(ph.ctBorder("Input data", ph.narancs));
		tablepanel = new JPanel(null);
		tablepanel.setBackground(ph.skek);
		tablepanel.setBounds(30, 150, 1100, 380);
		tablepanel.setBorder(ph.ctBorder("Results", ph.narancs));
		add(datapanel);
		
		lbauthor = zt.cplabel("Authors :");
		lbauthor.setBounds(20, 30, 80, 30);
		datapanel.add(lbauthor);

		cmbauthors = new JComboBox<>();	
		cmbauthors.setFont(new Font("Tahoma", Font.BOLD, 16));
		cmbauthors.setBounds(110, 30, 260, 30);
		datapanel.add(cmbauthors);

		lbselfs = zt.cplabel("Selfs :");
		lbselfs.setBounds(380, 30, 60, 30);
		datapanel.add(lbselfs);
	
		cmbselfs = new JComboBox<>();	
		cmbselfs.setBounds(445, 30, 100, 30);
		cmbselfs.setFont(new Font("Tahoma", Font.BOLD, 16));
		datapanel.add(cmbselfs);
		ph.selfcombofill(cmbselfs);

		lbsearch = ph.cplabel("Titles:");
		lbsearch.setBounds(560, 30, 60, 30);
		datapanel.add(lbsearch);

		txtsearch = cTextField(50);
		txtsearch.setBounds(625, 30, 250, 30);
		datapanel.add(txtsearch);
		
		btnsearch = zt.cbutton("Search");
		btnsearch.setFont(new java.awt.Font("Tahoma", 1, 16));
		btnsearch.setBounds(885, 30, 90, 30);
		datapanel.add(btnsearch);
		btnsearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hit = 0;
				searchmodul();
			}
		});

	
		btndelete = zt.cbutton("Clear");
		btndelete.setFont(new java.awt.Font("Tahoma", 1, 16));		
		btndelete.setBounds(985, 30, 90, 30);	
		btndelete.setBackground(new Color(0, 102, 102));	
		datapanel.add(btndelete);
		btndelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
				DefaultTableModel d = (DefaultTableModel) qtable.getModel();
				d.setRowCount(0);				
				saj.uressorok(qtable, 0, 10);
			txtclear();
			}
		});
	
		add(tablepanel);
		scrPane1 = new JScrollPane();
		scrPane1.setBounds(20, 20, 1055, 300);

		qtable = new ztable();
	
		qtable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Azon.", "Title of book", "Author's name", "Self" }) {
			Class[] types = new Class[] { Integer.class, String.class, String.class, String.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});

		saj.headermake(qtable);
		saj.setJTableColumnsWidth(qtable, 1040, 0, 45, 35, 10);
		scrPane1.setViewportView(qtable);
		tablepanel.add(scrPane1);
	

		lbfoundat = zt.cplabel("Hit :");
		lbfoundat.setBounds(540, 330, 80, 30);
		tablepanel.add(lbfoundat);

		lbfound = zt.cplabel("");
		lbfound.setBounds(630, 330, 40, 30);
		tablepanel.add(lbfound);

		setVisible(true);
	}

	private void table_update(String mysql) {
		int j;
		String sql;
		hit = 0;
		if (zt.isNullOrEmpty(mysql)) {
			saj.uressorok(qtable, 0, 10);
		} else {
			sql = mysql;
			try {
				con = DatabaseHelper.getConnection();
				pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				DefaultTableModel d = (DefaultTableModel) qtable.getModel();
				d.setRowCount(0);
				int sor = 0;
				while (rs.next()) {
					sor++;
					Vector <String> v2 = new Vector<>();
					v2.add(rs.getString("kid"));
					v2.add(rs.getString("title"));
					v2.add(rs.getString("name"));
					v2.add(rs.getString("self"));
					d.addRow(v2);
				}
				saj.uressorok(qtable, sor, 10);
				hit = sor;
				lbfound.setText(String.valueOf(hit));
				rs.close();
				pst.close();
				con.close();
			} catch (SQLException ex) {
				System.err.println("SQLException: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
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
		return textField;
	}

	private void searchmodul() {
		String query = "";
		String txt =txtsearch.getText();
		String self = (String) cmbselfs.getSelectedItem();
		int seged = cmbauthors.getSelectedIndex();
		int szid = 0;
		if (seged > 0) {
			szid = ((Authoritem) cmbauthors.getSelectedItem()).getId();
		}
		if (zt.isNullOrEmpty(txt) && zt.isNullOrEmpty(self) && szid <= 0) {
			table_update("");
		} else {

			if (txt.length() > 0) {
				String seg =txtsearch.getText().trim().toLowerCase();
				String szov = "k.title";

				query = "select k.kid, k.title, s.name, k.self, k.szid from books k "
						+ " left join authors s  on k.szid = s.szid  "
						+ "  where " + szov + " like '%" + seg + "%'" + " order by k.title COLLATE NOCASE ASC";
			

			} else if (!zt.isNullOrEmpty(self)) {
				query = "select k.kid, k.title, s.name, k.self, k.szid from books k "
						+ " left join authors s  on k.szid = s.szid  where self = '" + self + "'" + " order by kid";

			} else if (szid > 0) {
				query = "select k.kid, k.title, s.name, k.self, k.szid from books k "
						+ " left join authors s  on k.szid = s.szid  where k.szid=" + szid
						+ " order by k.title COLLATE NOCASE ASC";
			}
			table_update(query);
		}
	}
	private void txtclear(){		
	txtsearch.setText("");
	cmbauthors.setSelectedIndex(-1);
	cmbselfs.setSelectedIndex(-1);
}

	public static void main(String args[]) {
		new Information();

	}

	JLabel lbheader, lbsearch, lbauthor, lbselfs, lbfoundat, lbfound;
	JTextField txtsearch;
	JPanel datapanel, tablepanel;
	JButton btndelete, btnsearch;
	JComboBox cmbauthors;
	JComboBox<String> cmbselfs;
	String[] polcstring;
	JScrollPane scrPane1;
}
