package partDatabase;

public enum Location {
	
	UNKNOWN("Unknown"),MOTOR_BIN("Motor Bin"),WHEELS_BIN("Wheels Bin"),SMALL_WHEEL_BIN("Small Wheel Bin"),ELECTRICAL_BIN("Electrical Bin"),
	NEW_2014("New for 2014 Bin"),MISC("Misc. Bin"),RANDOM("Random Bin"),BUMPER_BIN("Bumper Bin"),SQUARE_BIN("Square Metal Bin"),
	C_CHANNEL_BIN("C-Channel Bin"),MISC_METAL_BIN("Misc. Metal Bin"),ANGLED_METAL_BIN("Angled Metal Bin"),
	PNEUMATICS_BIN("Pneumatic Bin"),STANLEY_TOOL_BOX("Stanley Tool Box"),BACK_TOOL_BOX("Black Tool Box"),
	ELECTRICAL_SHELVES_1("Electrical Shelves 1"),ELECTRICAL_SHELVES_2("Electrical Shelves 2"),MOTOR_DRAWER("Motor Drawer"),
	GEAR_DRAWER("Gear Drawer"),ELECTRICAL_DRAWER("Electrical Drawer"),SOFTWARE_LOCK_BOX("Software Lock Box");
	
	String name;
	
	Location(String name) {
		this.name = name;
		
	}
	
	public static Location getLocationByName(String name) {
		for (Location l : Location.values()) {
			if (l.name.equals(name)) {
				return l;
			}
		}
		return UNKNOWN;
	}
	
	public static String[] getLocationNames() {
		String[] names = new String[Location.values().length];
		for (int i=0; i<Location.values().length; i++) {
			names[i] = Location.values()[i].name;
		}
		return names;
	}
	
	public int getLocationNum() {
		for (int i=0; i<Location.values().length; i++) {
			if (Location.values()[i].equals(this)) {
				return i;
			}
		}
		return -1;
	}
}
