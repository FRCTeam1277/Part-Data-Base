package partDatabase;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;

import partDatabase.lib.SpringUtilities;

public class PartEditor {
	public static void addPartDialog() {
		JPanel addPart = new JPanel();
		JPanel input = new JPanel(new SpringLayout());
		JLabel nameLabel = new JLabel("Name*");
		JTextField name = new JTextField();
		JLabel quantLabel = new JLabel("Quantity");
		SpinnerNumberModel quantMod = new SpinnerNumberModel(1,0,10000,1);
		JSpinner quant = new JSpinner(quantMod);
		JLabel locationLabel = new JLabel("Location");
		JComboBox location = new JComboBox(Location.getLocationNames());
		JLabel descriptionLabel = new JLabel("Description");
		JTextField description = new JTextField();
		JLabel notesLabel = new JLabel("Notes");
		JTextField notes = new JTextField();
		input.add(nameLabel);
		input.add(name);
		input.add(quantLabel);
		input.add(quant);
		input.add(locationLabel);
		input.add(location);
		input.add(descriptionLabel);
		input.add(description);
		input.add(notesLabel);
		input.add(notes);
		SpringUtilities.makeCompactGrid(input, 5, 2, 6, 6, 10, 10);
		boolean add = true;
		while(add) {
			int val = JOptionPane.showConfirmDialog(null, input,"Add New Part",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			System.out.println(val);
			if (val==2||val==-1)
				break;
			if (name.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Items must have a name.");
				continue;
			}
			add = false;
			Part p = new Part(name.getText());
			p.quantity = (Integer) quant.getValue();
			p.location = Location.values()[location.getSelectedIndex()];
			p.description = description.getText();
			Main.partList.add(p);
			Main.mainInstance.updateTable();
			Main.mainInstance.setTableScrollTo(Main.partList.indexOf(p));
		}
	}
	
	public static void editPartDialog(Part p) {
		JPanel addPart = new JPanel();
		JPanel input = new JPanel(new SpringLayout());
		JLabel nameLabel = new JLabel("Name*");
		JTextField name = new JTextField(p.name);
		JLabel quantLabel = new JLabel("Quantity");
		SpinnerNumberModel quantMod = new SpinnerNumberModel(p.quantity,0,10000,1);
		JSpinner quant = new JSpinner(quantMod);
		JLabel locationLabel = new JLabel("Location");
		JComboBox location = new JComboBox(Location.getLocationNames());
		location.setSelectedIndex(p.location.getLocationNum());
		JLabel descriptionLabel = new JLabel("Description");
		JTextField description = new JTextField(p.description);
		JLabel notesLabel = new JLabel("Notes");
		JTextField notes = new JTextField(p.notes);
		input.add(nameLabel);
		input.add(name);
		input.add(quantLabel);
		input.add(quant);
		input.add(locationLabel);
		input.add(location);
		input.add(descriptionLabel);
		input.add(description);
		input.add(notesLabel);
		input.add(notes);
		SpringUtilities.makeCompactGrid(input, 5, 2, 6, 6, 10, 10);
		boolean add = true;
		while(add) {
			int val = JOptionPane.showConfirmDialog(null, input,"Add New Part",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			System.out.println(val);
			if (val==2||val==-1)
				break;
			if (name.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Items must have a name.");
				continue;
			}
			add = false;
			p.name = name.getText();
			p.quantity = (Integer) quant.getValue();
			p.location = Location.values()[location.getSelectedIndex()];
			p.description = description.getText();
			Main.mainInstance.updateTable();
			Main.mainInstance.setTableScrollTo(Main.partList.indexOf(p));
		}
	}
}
