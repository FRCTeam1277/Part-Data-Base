package partDatabase;

public enum Location {
	
	UNKNOWN("Unknown"),MOTOR_BIN("Motor Bin");
	
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
