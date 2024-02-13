import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import DAO.DatabaseHelper;

public class MainFrame extends JFrame {
	Bookhelper ph = new Bookhelper();
	Lib zt = new Lib();
	Color narancs = new Color(254, 179, 0);
	JLabel lbheader, lbbooks, lbauthors, lbinform;
	JButton btnbooks, btnauthors, btninformation;
	Border myRaisedBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.BLACK);
	
//	BorderFactory.createBevelBorder(int type, Color highlight, Color shadow)
//	BorderFactory.createBevelBorder(int type, Color highlightOuter,
//			Color highlightInner, Color shadowOuter, Color shadowInner)

	public MainFrame() {
		Init();
		menuprepare();
	}

	private void Init() {
		this.setLayout(null);
		setBounds(10, 10, 1000, 600);
		setLocationRelativeTo(null);
		setBackground(new Color(255, 255, 255));

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				int x, y, d;
				x = 1000;
				y = 600;
				d = 10;
				while (x > 0 && y > 0) {
					setSize(x, y);
					x = x - 2 * d;
					y = y - d;
					setVisible(true);
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						System.out.println("This error is :" + e);
					}
				}
				dispose();
			}
		});

		ImageIcon ImageIcon = new ImageIcon(getClass().getResource("Images/lib32.png"));
		Image Image = ImageIcon.getImage();
		this.setIconImage(Image);

		lbheader = new JLabel();
		lbheader.setFont(new Font("Footlight MT Light", 1, 32));

		lbheader.setForeground(new Color(0, 102, 102));
		lbheader.setText("MY HOME LIBRARY");
		lbheader.setBounds(335, 10, 500, 50);
		add(lbheader);

		lbbooks = new JLabel();
		lbbooks.setIcon(new ImageIcon(getClass().getResource("Images/Book_Details.png")));
		lbbooks.setBounds(50, 130, 250, 250);
		add(lbbooks);

		btnbooks = ph.kkbutton("Books");
		btnbooks.setBounds(90, 400, 150, 40);
		add(btnbooks);
		btnbooks.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Books ob = new Books();
				ob.setVisible(true);
			//	dispose();
			}
		});

		lbinform = new JLabel();
		lbinform.setIcon(new ImageIcon(getClass().getResource("Images/search2.png")));
		lbinform.setBounds(360, 40, 250, 250);
		add(lbinform);

		btninformation = ph.kkbutton("Information");
		btninformation.setBounds(430, 310, 150, 40);
		add(btninformation);
		btninformation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Information ob = new Information();
				ob.setVisible(true);
			//	dispose();
			}
		});

		lbauthors = new JLabel();
		lbauthors.setIcon(new ImageIcon(getClass().getResource("Images/writer2.png")));
		lbauthors.setBounds(685, 200, 220, 165);
		lbauthors.setBorder(myRaisedBorder);
		add(lbauthors);

		btnauthors = ph.kkbutton("Authors");
		btnauthors.setBounds(730, 400, 150, 40);
		add(btnauthors);
		btnauthors.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Authors ob = new Authors();
				ob.setVisible(true);
			//	dispose();
			}
		});
		setVisible(true);
	}

	private void menuprepare() {
		// create a menu bar
		final JMenuBar menuBar = new JMenuBar();
		Font f = new Font("Tahoma", Font.BOLD, 16);
		UIManager.put("Menu.font", f);
		// create menus
		JMenu inputMenu = new JMenu("Input data");
		JMenu questionMenu = new JMenu("Informations");
		final JMenu helpMenu = new JMenu("Help");

		menuBar.add(inputMenu);
		menuBar.add(questionMenu);
		menuBar.add(helpMenu);

		// create menu items
		JMenuItem bookMenuItem = new JMenuItem("Books");
		bookMenuItem.setFont(f);
		bookMenuItem.setMnemonic(KeyEvent.VK_B);
		
		bookMenuItem.setActionCommand("Book");
		JMenuItem writerMenuItem = new JMenuItem("Authors");
		writerMenuItem.setFont(f);
		writerMenuItem.setActionCommand("Writer");
		
		JMenuItem selfMenuItem = new JMenuItem("Selfs");
		selfMenuItem.setFont(f);
		selfMenuItem.setActionCommand("Self");
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setFont(f);
		exitMenuItem.setActionCommand("Exit");
		
		inputMenu.add(bookMenuItem);
		inputMenu.add(writerMenuItem);
		inputMenu.add(selfMenuItem);
		inputMenu.add(exitMenuItem);
		// ***************** second column 

		JMenuItem question1MenuItem = new JMenuItem("Information");
		question1MenuItem.setFont(f);
		question1MenuItem.setActionCommand("Information");

		questionMenu.add(question1MenuItem);
		// ****************************** third oszlop

		JMenuItem helpMenuItem = new JMenuItem("Layout");
		helpMenuItem.setFont(f);
		helpMenuItem.setActionCommand("Map");
		helpMenu.add(helpMenuItem);

		this.setJMenuBar(menuBar);

		MenuItemListener menuItemListener = new MenuItemListener();
		bookMenuItem.addActionListener(menuItemListener);
		writerMenuItem.addActionListener(menuItemListener);
		selfMenuItem.addActionListener(menuItemListener);
		exitMenuItem.addActionListener(menuItemListener);
		question1MenuItem.addActionListener(menuItemListener);
		helpMenuItem.addActionListener(menuItemListener);		
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
	}

	class MenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String par = e.getActionCommand();
			if (par == "Exit") {
				System.exit(0);
			} else if (par == "Book") {
				Books ob = new Books();
				ob.setVisible(true);
		//		dispose();
			} else if (par == "Writer") {
				Authors ob = new Authors();
				ob.setVisible(true);
				//dispose();
			} else if (par == "Self") {
				Selfs ob = new Selfs();
				ob.setVisible(true);
			//	dispose();
			} else if (par == "Information") {
				Information ob = new Information();
				ob.setVisible(true);
			//	dispose();
			} else if (par == "Map") {
				Map ob = new Map();
				ob.setVisible(true);
			}
		}
	}
}
