package partDatabase;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Main implements ActionListener {
	
	public static Main mainInstance;
	
	public static ArrayList<Part> partList = new ArrayList<Part>();
	
	private JFrame frame;
		private JMenuBar toolBar;
			private JMenu partDatabaseMenu;
				private JMenuItem about;
				private JMenuItem close;
			private JMenu fileMenu;
				private JMenuItem openData;
				private JMenuItem saveData;
			private JMenu partMenu;
				private JMenuItem addPart;
				private JMenuItem removePart;
				private JMenuItem editPart;
			private JScrollPane tableScroll;
				private DefaultTableModel tableModel;
				private JTable table;
		
	public boolean running = true;
	
	private static final int SCREEN_WIDTH, SCREEN_HEIGHT;
	
	static {
		DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

		SCREEN_WIDTH = dm.getWidth();
		SCREEN_HEIGHT = dm.getHeight();
	}
	
	public Main() {
		init();
		SaveManager.init("Parts.db");
		//Location l = Location.valueOf("Motor Bin");
		
		while(running) {
			
		}
		
		destroy();
	}
	
	@SuppressWarnings("serial")
	private void init() {
		frame = new JFrame() {{
			setTitle("Part Database");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(SCREEN_WIDTH / 4, SCREEN_HEIGHT / 4, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
		}};
		
		toolBar = new JMenuBar();
			partDatabaseMenu = new JMenu("Database");
				about = new JMenuItem("About");
				about.addActionListener(this);
				about.setActionCommand("about");
				close = new JMenuItem("Exit");
				close.addActionListener(this);
				close.setActionCommand("exit");
			partDatabaseMenu.add(about);
			partDatabaseMenu.add(close);
			fileMenu = new JMenu("File");
				openData = new JMenuItem("Open Database");
				openData.addActionListener(this);
				openData.setActionCommand("open");
				saveData = new JMenuItem("Save Database");
				saveData.addActionListener(this);
				saveData.setActionCommand("save");
			fileMenu.add(openData);
			fileMenu.add(saveData);
			partMenu = new JMenu("Part");
				addPart = new JMenuItem("Add Part");
				addPart.addActionListener(this);
				addPart.setActionCommand("add_part");
				removePart = new JMenuItem("Delete Part");
				removePart.addActionListener(this);
				removePart.setActionCommand("remove_part");
				editPart = new JMenuItem("Edit Part");
				editPart.addActionListener(this);
				editPart.setActionCommand("edit_part");
			partMenu.add(addPart);
			partMenu.add(removePart);
			partMenu.add(editPart);
		toolBar.add(partDatabaseMenu);
		toolBar.add(fileMenu);
		toolBar.add(partMenu);
		
		createTable();

		frame.setJMenuBar(toolBar);
				
		frame.setVisible(true);
		frame.setIconImage(getImage("res/table.jpg"));

	}
	
	@SuppressWarnings("serial")
	public void createTable() {
		if (tableScroll!=null) {
			frame.remove(tableScroll);
		}
		String[] columns = {"Name", "Descr.", "Location", "Quantity", "Notes", "Czeched Out"};
		for(int i = 0; i < 50; i++) {
			partList.add(new Part("Part " + i));
		}
		tableModel = new DefaultTableModel(getParts(), columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		tableScroll = new JScrollPane(table);
		frame.add(tableScroll);
		frame.invalidate();
		frame.validate();
	}
	
	private Object[][] getParts() {
		Object[][] objectArray = new Object[partList.size()][6];
		for(int i = 0; i < partList.size(); i++) {
			Part part = partList.get(i);
			objectArray[i][0] = part.name;
			objectArray[i][1] = part.description;
			objectArray[i][2] = part.location;
			objectArray[i][3] = part.quantity;
			objectArray[i][4] = part.notes;
			objectArray[i][5] = part.checkedOut;
		}
		return objectArray;
	}
	
	private BufferedImage getImage(String loc) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(loc));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	private ImageIcon getIcon(String loc) {
		return new ImageIcon(loc);
	}
	
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		if(action.equals("open")) {
			SaveManager.openfile();
			frame.repaint();
			createTable();
		} else if(action.equals("save")) {
			SaveManager.saveFile();
			frame.repaint();
		} else if(action.equals("about")) {
			JOptionPane.showMessageDialog(frame, "This database was created by Nick Burnett and Jesse King\nfor FIRST Team 1277, the Robotomies.", "Version 1.0", JOptionPane.PLAIN_MESSAGE);
		} else if(action.equals("exit")) {
			running = false;
			destroy();
			//I don't know why setting running to false isn't doing anything...
		} else if(action.equals("add_part")) {
			
		} else if(action.equals("remove_part")) {
			int id = table.getSelectedRow();
			if(id == -1) {
				JOptionPane.showMessageDialog(frame, "You must select a Part to remove.", "Whoops.", JOptionPane.PLAIN_MESSAGE);
			} else {
				Part p = partList.get(id);
				int result = JOptionPane.showConfirmDialog(frame, "Are you sure that you want to delete " + p.name + "?" , "Yo Dawg", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, getIcon("res/ermagerd.png"));
				if(result == 0) {
					partList.remove(id);
					tableModel.removeRow(id);
					table.removeRowSelectionInterval(0, table.getRowCount()-1);
				}
			}
		} else if(action.equals("edit_part")) {
			PartEditor.addPartDialog();
		}
	}
	
	private void destroy() {
		System.exit(0);
	}

	public static void main(String... args) {
		mainInstance = new Main();
	}
}
