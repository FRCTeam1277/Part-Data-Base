package partDatabase;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;

public class Main implements ActionListener {
	
	private static Main mainInstance;
	
	public static ArrayList<Part> partList = new ArrayList<Part>();
	
	private JFrame frame;
		private JMenuBar toolBar;
			private JMenu partDatabaseMenu;
				private JMenuItem about;
				private JMenuItem close;
			private JMenu fileMenu;
				private JMenuItem openData;
				private JMenuItem saveData;
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
		
		Location l = Location.valueOf("Motor Bin");
		
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

		toolBar.add(partDatabaseMenu);
		toolBar.add(fileMenu);
		
		table = new JTable();
		frame.add(table);

		frame.setJMenuBar(toolBar);
		
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("open")) {
			System.out.println("Open pressed");
		} else if(ae.getActionCommand().equals("save")) {
			System.out.println("Save pressed");
			SaveManager.saveFile();
		} else if(ae.getActionCommand().equals("exit")) {
			running = false;
		}
		
	}
	
	private void destroy() {
		System.exit(0);
	}

	public static void main(String... args) {
		mainInstance = new Main();
	}
}