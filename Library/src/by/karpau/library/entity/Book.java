package by.karpau.library.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
	private String bookName;
	private String authorName;
	private String bookGenre;
	private Date dateOfPublication;
	private int numberOfPages;
	
	public Book() {
		
	}
	
	public Book(String bookName, String authorName, String bookGenre, Date dateOfPublication,
			int numberOfPages) {
		this.numberOfPages = numberOfPages;
		this.bookName = bookName;
		this.authorName = authorName;
		this.bookGenre = bookGenre;
		this.dateOfPublication = new Date(dateOfPublication.getTime());
	}

	public Book getBookCopy() {
		Book bookCopy = new Book();
		bookCopy.bookName = this.bookName;
		bookCopy.authorName = this.authorName;
		bookCopy.bookGenre = this.bookGenre;
		bookCopy.dateOfPublication = new Date(this.dateOfPublication.getTime());
		bookCopy.numberOfPages = this.numberOfPages;
		return bookCopy;
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
		return new Date(dateOfPublication.getTime());
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
	
	public void setBook(Book book) {
		this.numberOfPages = book.numberOfPages;
		this.bookName = book.bookName;
		this.authorName = book.authorName;
		this.bookGenre = book.bookGenre;
		this.dateOfPublication = new Date(book.dateOfPublication.getTime());
	}
	
	@Override
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		return bookName + " " + authorName + " " + bookGenre + " " + 
				formatter.format(dateOfPublication) + " " + numberOfPages;
	}
	
	public boolean equals(Book book) {
		if(book == null) {
			return false;
		}
		if (this == book) {
			return true;
		}
		if (book.getClass().getName() != this.getClass().getName()) {
			return false;
		}
		if(bookName != book.bookName || authorName != book.authorName || bookGenre != book.bookGenre
				|| dateOfPublication.getTime() != book.dateOfPublication.getTime()
				|| numberOfPages != book.numberOfPages) {
			return false;
		}
		return true;
	}
}
