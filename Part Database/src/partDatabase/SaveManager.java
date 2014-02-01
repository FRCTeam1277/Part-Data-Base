package partDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class SaveManager {
	public static double version;
	
	private static File file;
	private static PrintStream output;
	public static void init(String fileName) {
		file = new File(fileName);
		
	}
	
	public static void saveFile() {
		try {
			output = new PrintStream(file);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Unable to open file "+file.getPath());
		}
		output.println("-version "+version);
		for (Part p: Main.partList) {
			savePart(p);
		}
		output.flush();
		output.close();
	}
	
	public static void savePart(Part p) {
		String saveLine = "-part";
		saveLine+=","+p.name;
		saveLine+=","+String.valueOf(p.quantity);
		saveLine+=","+p.location.name;
		saveLine+=","+p.description;
		saveLine+=","+p.notes;
		saveLine+=","+String.valueOf(p.checkedOut);
		if (p.checkedOut) {
			saveLine+=","+p.whoChecked;
		}
		saveLine+=","+p.whoChecked;
		
		output.println(saveLine);
	}
	
	public static void openfile() {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(file));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Unable to open file "+file.getPath());
		}
		
		try {
			String line = input.readLine();

			double version = Double.valueOf(line);
			Main.partList = new ArrayList<Part>();
			while(line!=null) {
				line = input.readLine();
				if (line.startsWith("-part,")) {
					String[] comp = line.split(",");
					Part p = new Part(comp[1]);
					p.quantity = Integer.valueOf(comp[2]);
					p.location = Location.getLocationByName(comp[3]);
					p.description = comp[4];
					p.notes = comp[5];
					p.checkedOut = Boolean.valueOf(comp[6]);
					if (p.checkedOut)
						p.whoChecked = comp[7];
					Main.partList.add(p);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
