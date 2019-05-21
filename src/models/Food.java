package models;

public class Food {
	protected int foodID;
	protected String foodName;
	protected int price;
	
	public Food(int foodID, String foodName, int price) {
		this(foodName, price);
		this.foodID = foodID;
	}
	public Food(String foodName, int price) {
		this.foodName = foodName;
		this.price = price;
	}
	
	public int getFoodID() {
		return foodID;
	}
	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}


}
