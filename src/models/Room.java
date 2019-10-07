package models;

public class Room {

	private int id;
	private String category;
	private String capacity;
	private Boolean availability;
	private Double price;
	
	public Room(String category, String capacity, Boolean availability, Double price) {
		this.category = category;
		this.capacity = capacity;
		this.availability = availability;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public Boolean getAvailability() {
		return availability;
	}
	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}
	
	
	
	
}
