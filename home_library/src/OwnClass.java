import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import DAO.DatabaseHelper;

class ztable extends JTable {
	// Font f = new Font("Courier", Font.BOLD, 14);
	Font f = new Font("tahoma", Font.PLAIN, 16);
	Color color1 = new Color(247, 94, 50);// t�glav
	Color vhzold = new Color(204, 230, 190);
	Color narancs1 = new Color(251, 191, 99);

	public boolean isCellEditable(int rowIndex, int colIndex) {
		return false;
	}

	public ztable() {
		super();
		this.setFont(f);
		this.setGridColor(Color.red);
		this.setForeground(Color.black);
		this.setBackground(vhzold);
		this.setShowGrid(true);
		this.setSelectionBackground(narancs1);
		this.setRowHeight(30);
		this.setShowHorizontalLines(true);
		this.setShowVerticalLines(true);
		this.setRowSelectionAllowed(true);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setFillsViewportHeight(true);
		this.setFocusable(false);
	}

	public ztable(int rows, int cols) {
		super();
		this.setFont(f);
		this.setGridColor(Color.red);
		this.setForeground(Color.black);
		this.setBackground(vhzold);
		this.setShowGrid(true);
		this.setSelectionBackground(narancs1);
		this.setRowHeight(30);
		this.setShowHorizontalLines(true);
		this.setShowVerticalLines(true);
		this.setRowSelectionAllowed(true);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setFillsViewportHeight(true);
		this.setFocusable(false);

	}

	public ztable(Object[][] data, Object[] Column) {
		super();
		this.setFont(f);
		this.setGridColor(Color.red);
		this.setForeground(Color.black);
		this.setBackground(vhzold);
		this.setShowGrid(true);
		this.setSelectionBackground(narancs1);
		this.setRowHeight(30);
		this.setShowHorizontalLines(true);
		this.setShowVerticalLines(true);
		this.setRowSelectionAllowed(true);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setFillsViewportHeight(true);
		this.setFocusable(false);
	}

	public ztable(TableModel dm) {
		super();
		this.setFont(f);
		this.setGridColor(Color.red);
		this.setForeground(Color.black);
		this.setBackground(vhzold);
		this.setShowGrid(true);
		this.setSelectionBackground(narancs1);
		this.setRowHeight(30);
		this.setShowHorizontalLines(true);
		this.setShowVerticalLines(true);
		this.setRowSelectionAllowed(true);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setFillsViewportHeight(true);
		this.setFocusable(false);
	}
}

class zheader extends JTableHeader {
	Color color1 = new Color(3, 134, 118); // vzold
	Font f = new Font("tahoma", Font.BOLD, 16);

	public zheader() {
		super();
		this.setFont(f);
		this.setForeground(Color.white);
		this.setBackground(color1);
		this.setBorder(new LineBorder(Color.WHITE, 1));
	}

	public zheader(TableColumnModel cm) {
		super(cm);
		this.setFont(f);
		this.setBackground(color1);
		this.setForeground(Color.white);
		this.setBorder(new LineBorder(Color.WHITE, 1));
	}
}

public class OwnClass {
	static Color color1;
	static Font font;

	public void headermake(ztable table) {
		Border headerBorder = new LineBorder(new Color(255, 255, 255), 1);
		JTableHeader header = table.getTableHeader();
		UIManager.getDefaults().put("TableHeader.cellBorder", headerBorder);
		header.setBorder(new LineBorder(Color.WHITE, 1));
		font = new Font("tahoma", Font.BOLD, 16);
		header.setFont(font);
		color1 = new Color(3, 134, 118); // vzold
		header.setBackground(color1);
		header.setForeground(Color.white);
		header.setPreferredSize(new Dimension(10000, 28));
	}

	public static String capitalize(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public void setJTableColumnsWidth(JTable table, int tablePreferredWidth, double... percentages) {
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
				column.setMinWidth(0);
				column.setMaxWidth(0);
				column.setWidth(0);
			}
		}
	}

	public void uressorok(JTable mytable, int startRow, int noOfRows) {
		if (startRow > noOfRows) {
			return;
		} else {
			noOfRows = noOfRows - startRow;
		}
		DefaultTableModel model = (DefaultTableModel) mytable.getModel();
		int col_c = model.getColumnCount();
		Object[] row = new Object[col_c];
		for (int i = 0; i < col_c; i++)
			row[i] = null;
		for (int i = 0; i < noOfRows; i++)
			model.insertRow(startRow, row);
	}	
	public static void gotolastrow(JTable dtable) {
	dtable.addComponentListener(new ComponentAdapter() {
		public void componentResized(ComponentEvent e) {
			dtable.scrollRectToVisible(dtable.getCellRect(dtable.getRowCount() - 1, 0, true));
		}
	});
//	if (dtable.getRowCount() > 0) {
//		int row = dtable.getRowCount() - 1;
//		dtable.setRowSelectionInterval(row, row);
//	}
	}	
}
