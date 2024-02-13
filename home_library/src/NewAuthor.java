import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import DAO.DatabaseHelper;

public class NewAuthor extends JFrame {
	int myid;
	Connection con;
	Statement stmt;
	PreparedStatement pst;
	ResultSet rs;
	Lib zt = new Lib();
	OwnClass saj = new OwnClass();
	Bookhelper ph = new Bookhelper();
	JComboBox visszacmb;

	public NewAuthor(JComboBox mycombo) {

		visszacmb = mycombo;
		initComponents();
	}

	private void initComponents() {
		this.setBackground(ph.skek);
		Container cp = getContentPane();
		cp.setBackground(ph.skek);
		this.setLayout(null);
		this.setBackground(ph.skek);
		setBounds(10, 10, 560, 450);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
//		      	 Books ob = new Books();
//		      	 ob.setVisible(true);
				dispose();
			}
		});

		ImageIcon ImageIcon = new ImageIcon(getClass().getResource("Images/lib32.png"));
		Image Image = ImageIcon.getImage();
		this.setIconImage(Image);
		lbheader = ph.flabel("New author input");
		lbheader.setBounds(100, 10, 300, 40);
		add(lbheader);
		datapanel = new JPanel(null);
		datapanel.setBackground(ph.skek);
		datapanel.setBounds(30, 60, 470, 300);
		datapanel.setBorder(ph.ctBorder("Data", ph.narancs));
		add(datapanel);

		lbname = ph.cplabel("Author's name:");
		lbname.setBounds(30, 50, 150, 30);
		datapanel.add(lbname);

		txtname = ph.cTextField(40);
		txtname.setBounds(190, 50, 250, 30);
		datapanel.add(txtname);
		txtname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (txtname.getText().length() == 0) {
					if (Character.isLowerCase(keyChar))
						e.setKeyChar(Character.toUpperCase(keyChar));
				}
			}
		});

		lbnationality = ph.cplabel("Nationality :");
		lbnationality.setBounds(30, 100, 150, 30);
		datapanel.add(lbnationality);

		txtnationality = ph.cTextField(40);
		txtnationality.setBounds(190, 100, 250, 30);
		datapanel.add(txtnationality);
		txtnationality.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (txtnationality.getText().length() == 0) {
					if (Character.isLowerCase(keyChar))
						e.setKeyChar(Character.toUpperCase(keyChar));
				}
			}
		});

		btnadd = zt.cbutton("Save");
		btnadd.setBounds(240, 170, 130, 30);
		btnadd.setBackground(ph.piros);
		datapanel.add(btnadd);
		btnadd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				addActionPerformed(evt);
			}
		});

		btncancel = zt.cbutton("Cancel");
		btncancel.setBounds(240, 220, 130, 30);
		btncancel.setBackground(ph.vkek);
		datapanel.add(btncancel);
		btncancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// Books ob = new Books();
				// ob.setVisible(true);
				dispose();
			}
		});

		setVisible(true);
	}

	private void addActionPerformed(java.awt.event.ActionEvent evt) {
		int myid = 0;
		String name = txtname.getText();
		String nationality = txtnationality.getText();
		if (authorvalidation(name, true) == false) {
			return;
		}
		name = ph.capitalizeWord(name);
		try {
			con = DatabaseHelper.getConnection();
			pst = con.prepareStatement("insert into authors (name, nationality) values (?,?) ");
			pst.setString(1, name);
			pst.setString(2, nationality);
			pst.executeUpdate();
			pst.close();
			con.close();
			myid = ph.table_maxid("SELECT MAX(szid) AS max_id FROM authors");
			combotolt();
			ph.setSelectedValue(visszacmb, myid);
			visszacmb.updateUI();
			dispose();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	private void combotolt() {
		ph.Author(visszacmb);
	}

	private boolean authorvalidation(String name, Boolean yes) {
		boolean ret = true;
		ArrayList<String> err = new ArrayList<String>();
		if (zt.isNullOrEmpty(name)) {
			if (yes == true) {
				err.add("Name is empty !");
			}
			ret = false;
		}
		if (err.size() > 0) {
			JOptionPane.showMessageDialog(null, err.toArray(), "Error", 1);
		}
		return ret;
	}
//
//	public static void main(String args[]) {
//		new ujszerzo();
//	}

	JPanel datapanel;
	JLabel lbid, lbname, lbnationality, lbheader;
	JTextField txtname, txtnationality;
	JButton btnadd, btncancel;
}
