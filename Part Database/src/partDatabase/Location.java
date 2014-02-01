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
	
}
