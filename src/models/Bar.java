package models;

public class Bar {
	protected int drinkID;
	protected String drinkName;
	protected int price;
	
	public Bar(int drinkID, String drinkName, int price) {
		this(drinkName, price);
		this.drinkID = drinkID;
		
	}
	
	public Bar(String drinkName, int price) {
		this.drinkName = drinkName;
		this.price = price;
	}

	public int getDrinkID() {
		return drinkID;
	}
	public void setDrinkID(int drinkID) {
		this.drinkID = drinkID;
	}
	public String getDrinkName() {
		return drinkName;
	}
	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	 
}
