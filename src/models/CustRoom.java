package models;

public class CustRoom {
	protected int customerID;
	protected String customerName;
	protected int roomID;
	
	public CustRoom(int customerID, String customerName, int roomID) {
		this(customerName, roomID);
		this.customerID = customerID;
	}
	public CustRoom(String customerName, int roomID) {
		this.customerName = customerName;
		this.roomID = roomID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getRoomId() {
		return roomID;
	}
	public void setRoomId(int roomID) {
		this.roomID = roomID;
	}
	
}
