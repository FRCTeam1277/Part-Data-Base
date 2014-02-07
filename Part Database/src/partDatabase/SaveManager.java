package partDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class SaveManager {
	public static double version;
	
	public static File file;
	private static PrintStream output;
	public static void init(String fileName) {
		file = new File(fileName);
		
	}
	
	public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
 
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
	
	public static boolean chooseSave() {
		JFileChooser cho = new JFileChooser(file);
		cho.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileFilter emu = new FileFilter(){{}
		@Override
		public boolean accept(File f) {
			if (f.isDirectory())
				return true;
			String ext = getExtension(f);
			if (ext != null) {
				if (ext.equals("db"))
					return true;
				else
					return false;
			}
			return false;
		}
		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "*.db Database Files";
		}};
		cho.addChoosableFileFilter((javax.swing.filechooser.FileFilter) emu);
		cho.setFileFilter(emu);
		cho.setSelectedFile(file);
		
		int choice = cho.showSaveDialog(Main.mainInstance.frame);
		System.out.println(choice);
		if (choice == JFileChooser.APPROVE_OPTION) {
			File f = cho.getSelectedFile();
			String ext = getExtension(f);
			if (ext==null||!ext.equals("db"))
				f = new File(f.getAbsolutePath()+".db");
			return saveFile(f);
		}
		
		return false;
	}
	
	public static boolean saveFile(File file) {
		try {
			output = new PrintStream(file);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Unable to open file "+file.getPath());
			return false;
		}
		output.println("-version "+version);
		for (Part p: Main.partList) {
			savePart(p);
		}
		output.flush();
		output.close();
		System.out.println("Saved File "+file.getPath());
		return true;
	}
	
	public static void savePart(Part p) {
		String saveLine = "-part";
		saveLine+="~"+p.name;
		saveLine+="~"+String.valueOf(p.quantity);
		saveLine+="~"+p.location.name;
		saveLine+="~"+p.description;
		saveLine+="~"+p.notes;
		saveLine+="~"+String.valueOf(p.checkedOut);
		if (p.checkedOut) {
			saveLine+="~"+p.whoChecked;
		}
		saveLine+="~"+p.whoChecked;
		
		output.println(saveLine);
	}
	
	public static boolean chooseOpen() {
		JFileChooser cho = new JFileChooser(file);
		cho.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileFilter emu = new FileFilter(){{}
		@Override
		public boolean accept(File f) {
			if (f.isDirectory())
				return true;
			String ext = getExtension(f);
			if (ext != null) {
				if (ext.equals("db"))
					return true;
				else
					return false;
			}
			return false;
		}
		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "*.db Database Files";
		}};
		cho.addChoosableFileFilter((javax.swing.filechooser.FileFilter) emu);
		cho.setFileFilter(emu);
		cho.setSelectedFile(file);
		
		int choice = cho.showSaveDialog(Main.mainInstance.frame);
		System.out.println(choice);
		if (choice == JFileChooser.APPROVE_OPTION) {
			File f = cho.getSelectedFile();
			String ext = getExtension(f);
			if (ext==null||!ext.equals("db"))
				f = new File(f.getAbsolutePath()+".db");
			return openFile(f);
		}
		
		return false;
	}
	
	public static boolean openFile(File file) {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(file));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Unable to open file "+file.getPath());
		}
		
		try {
			String line = input.readLine();

			double version = Double.valueOf(line.split(" ")[1]);
			Main.partList = new ArrayList<Part>();
			while((line = input.readLine())!=null) {
				
				if (line.startsWith("-part~")) {
					String[] comp = line.split("~");
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
		System.out.println("Opened File "+file.getPath());
		return true;
	}
}
