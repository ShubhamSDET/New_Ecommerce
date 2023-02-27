package Pojo;

public class Post_AddBook {
	
	private String name;
	private String isbn; 
	private Integer aisle; 
	private String author; 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Integer getAisle() {
		return aisle;
	}
	public void setAisle(Integer aisle) {
		this.aisle = aisle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}