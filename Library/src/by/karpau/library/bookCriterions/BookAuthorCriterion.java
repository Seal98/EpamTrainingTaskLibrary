package by.karpau.library.bookCriterions;

import by.karpau.library.bookExceptions.BookWrongArgumentException;
import by.karpau.library.entity.Book;

public class BookAuthorCriterion implements Criterion{
	private String authorName;
	
	public Object getCriterion() {
		return authorName;
	}
	
	public void setCriterion(Object authorName) throws BookWrongArgumentException {
		if(authorName == null) {
			throw new BookWrongArgumentException();
		}
		this.authorName = (String)authorName;
	}
	
	public boolean compareCriterions(Book book) throws BookWrongArgumentException {
		if(book == null) {
			throw new BookWrongArgumentException();
		}
		if(((String)this.getCriterion()).compareTo(book.getAuthorName()) == 0) {
			return true;
		}
		return false;
	}
	
	public boolean compareForSort(Book book1, Book book2) throws BookWrongArgumentException {
		if(book1 == null || book2 == null) {
			throw new BookWrongArgumentException();
		}
		if((book1.getAuthorName()).compareTo(book2.getAuthorName()) > 0) {
			return true;
		}
		return false;
	}

}
