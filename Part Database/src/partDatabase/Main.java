package partDatabase;

import javax.swing.JFrame;

public class Main {
	
	private static JFrame frame;
	
	private static void init() {
		frame = new JFrame();
		frame.setTitle("Part Database");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private static void destroy() {
		System.exit(0);
	}

	public static void main(String... args) {
		init();
		
		destroy();
	}
}