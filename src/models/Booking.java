package models;

import java.sql.Date;

public class Booking {

	private int id;
	private int room_id;
	private int customer_id;
	private Date check_in;
	private Date check_out;
	private Date book_date;
	
	
	public Booking(int room_id, int customer_id, Date book_date, Date check_in, Date check_out) {
		
		this.room_id = room_id;
		this.customer_id = customer_id;
		this.check_in = check_in;
		this.check_out = check_out;
		this.book_date = book_date;
		
	}
	
	public Booking(int id, int room_id, int customer_id, Date check_in, Date check_out) {
		
		this.id = id;
		this.room_id = room_id;
		this.customer_id = customer_id;
		this.check_in = check_in;
		this.check_out = check_out;

	}
	
	public Booking() {
		// TODO Auto-generated constructor stub
	}

	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public Date getCheck_in() {
		return check_in;
	}
	public void setCheck_in(Date check_in) {
		this.check_in = check_in;
	}
	public Date getCheck_out() {
		return check_out;
	}
	public void setCheck_out(Date check_out) {
		this.check_out = check_out;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBook_date() {
		return book_date;
	}

	public void setBook_date(Date book_date) {
		this.book_date = book_date;
	}


	
}
