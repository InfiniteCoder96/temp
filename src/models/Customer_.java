package models;

public class Customer_ {
	protected int customerID;
	protected String customerName;
	protected String country;
	protected String NICPassport;
	protected int contactNo;
	public Customer_(int customerID, String customerName, String country, String NICPassport, int contactNo) {
		this(customerName, country, NICPassport, contactNo);
		this.customerID = customerID;
	}
	public Customer_(String customerName, String country, String NICPassport, int contactNo) {
		this.customerName = customerName;
		this.country = country;
		this.NICPassport = NICPassport;
		this.contactNo = contactNo;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNICPassport() {
		return NICPassport;
	}
	public void setNICPassport(String nICPassport) {
		NICPassport = nICPassport;
	}
	public int getContactNo() {
		return contactNo;
	}
	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}
	

}
