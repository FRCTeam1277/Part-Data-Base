package partDatabase;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class PartEditor {
	public static void addPartDialog() {
		JPanel addPart = new JPanel();
		JPanel input = new JPanel();
		JLabel nameLabel = new JLabel("Name");
		JTextField name = new JTextField();
		JLabel quantLabel = new JLabel("Quantity");
		SpinnerNumberModel quantMod = new SpinnerNumberModel(1,0,10000,1);
		JSpinner quant = new JSpinner(quantMod);
		JLabel locationLabel = new JLabel("Location");
		JComboBox location = new JComboBox(Location.getLocationNames());
		JLabel descriptionLabel = new JLabel("Description");
		JTextField description = new JTextField();
		input.add(nameLabel);
		input.add(name);
		input.add(quantLabel);
		input.add(quant);
		input.add(locationLabel);
		input.add(location);
		input.add(descriptionLabel);
		input.add(description);
		int val = JOptionPane.showConfirmDialog(null, input,"Add New Part",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
		
	}
}
