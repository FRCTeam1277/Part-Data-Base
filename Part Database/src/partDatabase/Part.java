package partDatabase;

public class Part {
	String name;
	Location location;
	String description;
	String notes;
	int quantity;
	boolean checkedOut;
	
	public Part(String name) {
		this(name, Location.UNKNOWN, 0, "","");
	}
	
	public Part(String name, int quantity) {
		this(name, Location.UNKNOWN, quantity, "","");
	}
	
	public Part(String name, int quantity, Location location) {
		this(name, location, quantity, "", "");
	}
	
	public Part(String name, Location location, int quantity, String description, String notes) {
		this.name = name;
		this.location = location;
		this.quantity = quantity;
		this.description = description;
		this.notes = notes;
	}
}
