package partDatabase;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;

public class Main implements ActionListener {
	
	private static Main mainInstance;
	
	private static JFrame frame;
		private static JMenuBar toolBar;
			private static JMenu fileMenu;
				private static JMenuItem openData;
				private static JMenuItem saveData;
		private static JTable table;
		
	private static final int SCREEN_WIDTH, SCREEN_HEIGHT;
	public static boolean running = true;
	
	static {
		DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

		SCREEN_WIDTH = dm.getWidth();
		SCREEN_HEIGHT = dm.getHeight();
	}
	
	public Main() {
		init();
		
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
			fileMenu = new JMenu("File");
				openData = new JMenuItem("Open Database");
				openData.addActionListener(this);
				openData.setActionCommand("open");
				saveData = new JMenuItem("Save Database");
				saveData.addActionListener(this);
				saveData.setActionCommand("save");
			fileMenu.add(openData);
			fileMenu.add(saveData);
		toolBar.add(fileMenu);
		
		table = new JTable();
		frame.add(table);

		frame.setJMenuBar(toolBar);
		
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("open")) {
			System.out.println("Open Pressed");
		} else {
			
		}
		
	}
	
	private void destroy() {
		System.exit(0);
	}

	public static void main(String... args) {
		mainInstance = new Main();
	}
}