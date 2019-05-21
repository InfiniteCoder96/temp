package models;

public class Laundry {
	protected int clothID;
	protected String cloth;
	protected int price;
	
	public Laundry(int clothID, String cloth, int price) {
		this(cloth, price);
		this.clothID = clothID;
	}
	public Laundry(String cloth, int price) {
		this.cloth = cloth;
		this.price = price;
	}
	
	public int getClothID() {
		return clothID;
	}
	public void setClothID(int clothID) {
		this.clothID = clothID;
	}
	public String getCloth() {
		return cloth;
	}
	public void setCloth(String cloth) {
		this.cloth = cloth;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
