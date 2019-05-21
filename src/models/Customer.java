package models;

public class Customer {

	private int id;
	private String fullname;
	private String address;
	private String country;
	private int mobile;
	private String email;
	private int adult_visitors;
	private int child_visitors;
	private String payment_method;
	private int nights;
	private int rooms;
	
	public Customer(String fullname, String address, String country, int mobile, String email, int adult_visitors,
			int child_visitors, String payment_method, int nights, int rooms) {
		
		this.fullname = fullname;
		this.address = address;
		this.country = country;
		this.mobile = mobile;
		this.email = email;
		this.adult_visitors = adult_visitors;
		this.child_visitors = child_visitors;
		this.payment_method = payment_method;
		this.nights = nights;
		this.rooms = rooms;
	}
	
	public Customer(int id, String fullname, String address, String country, int mobile, String email,
			int adult_visitors, int child_visitors, String payment_method, int nights, int rooms) {
		
		this.id = id;
		this.fullname = fullname;
		this.address = address;
		this.country = country;
		this.mobile = mobile;
		this.email = email;
		this.adult_visitors = adult_visitors;
		this.child_visitors = child_visitors;
		this.payment_method = payment_method;
		this.nights = nights;
		this.rooms = rooms;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public int getAdult_visitors() {
		return adult_visitors;
	}

	public void setAdult_visitors(int adult_visitors) {
		this.adult_visitors = adult_visitors;
	}

	public int getChild_visitors() {
		return child_visitors;
	}

	public void setChild_visitors(int child_visitors) {
		this.child_visitors = child_visitors;
	}
	
	public int getNights() {
		return nights;
	}

	public void setNights(int nights) {
		this.nights = nights;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

}
