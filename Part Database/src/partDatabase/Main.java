package partDatabase;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class Main {
	
	private static JFrame frame;
	private static final int SCREEN_WIDTH, SCREEN_HEIGHT;
	public static boolean running = true;
	
	static {
		DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

		SCREEN_WIDTH = dm.getWidth();
		SCREEN_HEIGHT = dm.getHeight();
	}
	
	private static void init() {
		frame = new JFrame();
		frame.setTitle("Part Database");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setBounds(SCREEN_WIDTH / 4, SCREEN_HEIGHT / 4, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
		
		frame.setVisible(true);
	}
	
	private static void destroy() {
		System.exit(0);
	}

	public static void main(String... args) {
		init();
		
		while(running) {
			
		}
		
		destroy();
	}
}