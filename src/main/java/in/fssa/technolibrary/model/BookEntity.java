package in.fssa.technolibrary.model;

public abstract class BookEntity implements Comparable<BookEntity> {

	private int id;
	private String title;
	private String author;
	private int publisherId;
	private int categoryId;
	private String publishedDate;
	private int price;
	private boolean isActive = true;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int category_id) {
		this.categoryId = category_id;
	}
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
    @Override
    public int compareTo(BookEntity other) {
		return Integer.compare(this.id, other.getId());
    }

	@Override
	public String toString() {
		return "BookEntity [id=" + id + ", title=" + title + ", author=" + author + ", publisherId=" + publisherId
				+ ", categoryId=" + categoryId + ", publishedDate=" + publishedDate + ", price=" + price + ", isActive="
				+ isActive + "]";
	}

	

}
