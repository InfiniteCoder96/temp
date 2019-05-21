package models;

public class CustomerBill {
	private int billID;
	private int customerID;
	private int roomFee;
	private int drinkFee;
	private int foodFee;
	private int laundryFee;
	private int total;
	private String paymentMethod;
	
	public CustomerBill(int billID, int customerID, int roomFee, int drinkFee, int foodFee, int laundryFee, int total, String paymentMethod) {
		this(customerID, roomFee, drinkFee, foodFee, laundryFee, total, paymentMethod);
		this.billID = billID;
	}
	public CustomerBill( int customerID, int roomFee, int drinkFee, int foodFee, int laundryFee, int total, String paymentMethod) {
		this.customerID = customerID;
		this.roomFee = roomFee;
		this.drinkFee = drinkFee;
		this.foodFee = foodFee;
		this.laundryFee = laundryFee;
		this.total = total;
		this.paymentMethod = paymentMethod;
		
	}
	
	/**
	 * @return the billID
	 */
	public int getBillID() {
		return billID;
	}
	/**
	 * @param billID the billID to set
	 */
	public void setBillID(int billID) {
		this.billID = billID;
	}
	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}
	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	/**
	 * @return the roomFee
	 */
	public int getRoomFee() {
		return roomFee;
	}
	/**
	 * @param roomFee the roomFee to set
	 */
	public void setRoomFee(int roomFee) {
		this.roomFee = roomFee;
	}
	/**
	 * @return the drinkFee
	 */
	public int getDrinkFee() {
		return drinkFee;
	}
	/**
	 * @param drinkFee the drinkFee to set
	 */
	public void setDrinkFee(int drinkFee) {
		this.drinkFee = drinkFee;
	}
	/**
	 * @return the foodFee
	 */
	public int getFoodFee() {
		return foodFee;
	}
	/**
	 * @param foodFee the foodFee to set
	 */
	public void setFoodFee(int foodFee) {
		this.foodFee = foodFee;
	}
	/**
	 * @return the laundryFee
	 */
	public int getLaundryFee() {
		return laundryFee;
	}
	/**
	 * @param laundryFee the laundryFee to set
	 */
	public void setLaundryFee(int laundryFee) {
		this.laundryFee = laundryFee;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}
	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
	
}
