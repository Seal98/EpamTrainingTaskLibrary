package by.karpau.library.entity;

import java.util.Date;

public class Criterion {

	private String bookName;
	private String authorName;
	private String bookGenre;
	private Date dateOfPublication;
	private int numberOfPages;
	
	public Criterion() {
		numberOfPages = -1;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	
	public String getBookGenre() {
		return bookGenre;
	}
	
	public Date getDateOfPublication() {
		if(dateOfPublication == null) {
			return null;
		}
		else {
			return new Date(dateOfPublication.getTime());
		}
	}
	
	public Integer getNumberOfPages() {
		return numberOfPages;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}
	
	public void setDateOfPublication(Date dateOfPublication) {
		this.dateOfPublication = new Date(dateOfPublication.getTime());
	}
	
	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
}
