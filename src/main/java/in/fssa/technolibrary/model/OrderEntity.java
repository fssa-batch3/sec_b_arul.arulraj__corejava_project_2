package in.fssa.technolibrary.model;

public abstract class OrderEntity {
	
	private int id;
	private int book_id;
	private int user_id;
	private String address;
	private String order_date;
	private String status;
	private String last_update_on;
	
	public String getLast_update_on() {
		return last_update_on;
	}
	public void setLast_update_on(String last_update_on) {
		this.last_update_on = last_update_on;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", book_id=" + book_id + ", user_id=" + user_id + ", address=" + address
				+ ", order_date=" + order_date + ", order_status=" + status + ", last_update_on=" + last_update_on + "]";
	}
	
}
