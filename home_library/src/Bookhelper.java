
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import DAO.DatabaseHelper;

public class Bookhelper {	

	Border mrb = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.BLACK);
	Border borderz = BorderFactory.createLineBorder(Color.GREEN, 2);
	Border borderp = BorderFactory.createLineBorder(Color.RED, 2);
	Color homok = new Color(200, 137, 60);
	Color sotet = new Color(39, 25, 12);
	Color vbarna = new Color(223, 196, 155);
	Color lillas = new Color(123, 132, 173);
	Color szurke = new Color(122, 103, 88);
	//////////////////////////
	Color skek = new Color(23, 51, 72);
	Color vkek = new Color(3, 135, 174);
	Color piros = new Color(249, 73, 58);
	Color narancs1 = new Color(251, 191, 99);
	Color zold = new Color(157, 185, 66);

	Color vzold = new Color(124, 178, 66);
	Color vhzold = new Color(204, 230, 190);
	Color color1 = new Color(247, 94, 50);// t�glav
	Color vvzold = new Color(3, 134, 118); // vzold

	Color narancs = new Color(254, 179, 0);
	Color lerozsaszin = new Color(243, 78, 118);
	Color lvrozsasz = new Color(212, 183, 185);
	Connection con;
	Statement stmt;
	PreparedStatement pst;
	ResultSet rs;

	Font textf = new Font("Tahoma", Font.BOLD, 16);
	Border borderf = BorderFactory.createLineBorder(Color.BLACK, 1);
	Border thatBorder1 = new LineBorder(new Color(0, 102, 102), 2);
	Border headerBorder = new LineBorder(new Color(255, 255, 255), 1);
	Border sborder = BorderFactory.createLineBorder(Color.GRAY, 2);

	public JButton kkbutton(String string) {
		JButton bbutton = new JButton(string);
		bbutton.setBackground(new Color(0, 102, 102));
		bbutton.setFont(new java.awt.Font("Tahoma", 0, 24));
		bbutton.setForeground(new Color(255, 255, 255));
		bbutton.setBorder(mrb);
		bbutton.setPreferredSize(new Dimension(100, 30));
		return bbutton;
	}

	public JLabel cplabel(String string) {
		JLabel llabel = new JLabel(string);
		llabel.setFont(new Font("serif", Font.BOLD, 20));
		llabel.setForeground(narancs);
		// llabel.setBackground(Color.black);
		llabel.setPreferredSize(new Dimension(100, 30));
		return llabel;
	}

	public JLabel flabel(String string) {
		JLabel llabel = new JLabel(string);
		llabel.setFont(new Font("serif", Font.BOLD, 30));
		// llabel.setBackground(Color.YELLOW);
		llabel.setForeground(narancs);
		return llabel;
	}

	public JTextField cTextField(int hossz) {
		JTextField textField = new JTextField(hossz);
		textField.setFont(textf);
		textField.setBorder(borderf);
		textField.setBackground(vhzold);
		textField.setPreferredSize(new Dimension(250, 30));
		textField.setCaretColor(Color.RED);
		textField.putClientProperty("caretAspectRatio", 0.1);
		// textField.setHorizontalAlignment(JTextField.RIGHT)
		// textField.addFocusListener(dFocusListener);
		return textField;
	}

	public TitledBorder ctBorder(String title, Color szin) {
		// Border a = BorderFactory.createLineBorder(Color.black);
		Border a = new LineBorder(narancs, 2);
		TitledBorder b = BorderFactory.createTitledBorder(a, "");
		b.setTitle(title);
		b.setTitleColor(szin);
		b.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
		// b.setTitleJustification(TitledBorder.DEFAULT_JUSTIFICATION);
		b.setTitleJustification(TitledBorder.LEFT);
		// b.setTitlePosition(TitledBorder.CENTER);
		b.setTitlePosition(TitledBorder.LEFT);
		b.setTitlePosition(TitledBorder.TOP);
		// b.setTitlePosition(TitledBorder.ABOVE_TOP);
		return b;
	}

	public RoundedPanel myroundpanel(int radius, Color bgColor) {
		RoundedPanel ppanel = new RoundedPanel(radius, bgColor);
		return ppanel;
	}

	class JRoundedBorder extends AbstractBorder {
		private static final int THICKNESS = 2;

		JRoundedBorder() {
			super();
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			if (c.hasFocus()) {
				g2.setColor(Color.BLUE);
			} else {
				g2.setColor(Color.BLACK);
			}
			g2.setStroke(new BasicStroke(THICKNESS, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2.drawRoundRect(THICKNESS, THICKNESS, width - THICKNESS - 2, height - THICKNESS - 2, 20, 20);
			g2.dispose();
		}

		@Override
		public Insets getBorderInsets(Component c) {
			return new Insets(THICKNESS, THICKNESS, THICKNESS, THICKNESS);
		}

		@Override
		public Insets getBorderInsets(Component c, Insets insets) {
			insets.left = insets.top = insets.right = insets.bottom = THICKNESS;
			return insets;
		}

		public boolean isBorderOpaque() {
			return false;
		}
//	           
//	                // Add button with custom border
//	                final JButton button = new JButton("Hello");
//	                button.setBorder(new JRoundedBorder());
//	                button.setBackground(Color.YELLOW);
//	                button.setPreferredSize(new Dimension(200, 200));
	}

	class RoundedPanel extends JPanel {
		private Color backgroundColor;
		private int cornerRadius = 15;

		public RoundedPanel(LayoutManager layout, int radius) {
			super(layout);
			cornerRadius = radius;
		}

		public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
			super(layout);
			cornerRadius = radius;
			backgroundColor = bgColor;
		}

		public RoundedPanel(int radius) {
			super();
			cornerRadius = radius;
		}

		public RoundedPanel(int radius, Color bgColor) {
			super();
			cornerRadius = radius;
			backgroundColor = bgColor;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Dimension arcs = new Dimension(cornerRadius, cornerRadius);
			int width = getWidth();
			int height = getHeight();
			Graphics2D graphics = (Graphics2D) g;
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// Draws the rounded panel with borders.
			if (backgroundColor != null) {
				graphics.setColor(backgroundColor);
			} else {
				graphics.setColor(getBackground());
			}
			graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint background
			graphics.setColor(getForeground());
			graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint border
		}
	}

	public static String capitalizeWord(String str) {
		String words[] = str.split("\\s");
		String capitalizeWord = "";
		String first, afterfirst;
		for (String w : words) {
			if (w.length() == 0) {
				continue;
			}
			first = w.substring(0, 1);
			afterfirst = "";
			if (w.length() != 0) {
				afterfirst = w.substring(1);
			}
			capitalizeWord += first.toUpperCase() + afterfirst + " ";
		}
		return capitalizeWord.trim();
	}

	public void Author(JComboBox mycombo) {
		try {
			con = DatabaseHelper.getConnection();
			pst = con.prepareStatement("select szid, name from authors order by name COLLATE NOCASE ASC");
			ResultSet rs = pst.executeQuery();
			mycombo.removeAllItems();
			Authoritem A = new Authoritem(0, "");
			mycombo.addItem(A);
			while (rs.next()) {
				A = new Authoritem(rs.getInt(1), rs.getString(2));
				// cmbszerzok.addItem(new Authoritem(rs.getInt(1), rs.getString(2)));
				mycombo.addItem(A);
			}
			rs.close();
			pst.close();
			con.close();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void selfcombofill(JComboBox ccombo) {
		String Sql = " select name from selfs order by  name";
		try {
			con = DatabaseHelper.getConnection();
			pst = con.prepareStatement(Sql);
			rs = pst.executeQuery();		   
			ccombo.removeAllItems();		
			while (rs.next()) {
				ccombo.addItem(rs.getString("name"));
			}
			rs.close();
			pst.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static void setSelectedValue(JComboBox comboBox, int value) {
		Authoritem item;
		for (int i = 0; i < comboBox.getItemCount(); i++) {
			item = (Authoritem) comboBox.getItemAt(i);
			if (item.getId() == value) {
				comboBox.setSelectedIndex(i);
				break;
			}
		}
	}

	public void ztmessage(String mess, String header) {
		JOptionPane op = new JOptionPane(mess, JOptionPane.INFORMATION_MESSAGE);
		final JDialog dialog = op.createDialog(header);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				dialog.setVisible(false);
				dialog.dispose();
			}
		}, 1000);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setAlwaysOnTop(true);
		dialog.setModal(false);
		dialog.setVisible(true);
	}

	public Boolean cannotdelete(String sql) {
		Boolean found = false;	
		try {		
			con = DatabaseHelper.getConnection();
			 pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				found = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pst.close();
				con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return found;
	}
	
	public int table_maxid(String sql) {
		int myid=0;	
		try {
			   con = DatabaseHelper.getConnection(); 								
			    pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();	
				if (!rs.next()) {
				    System.out.println("Error.");		
				}else{
			    	myid = rs.getInt("max_id");
				}
				pst.close();
				con.close();
} catch (SQLException ex) {		
			System.err.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		}
		return myid;
	}
}

