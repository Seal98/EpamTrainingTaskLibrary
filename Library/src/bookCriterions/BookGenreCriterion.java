package bookCriterions;

import by.karpau.library.bookExceptions.BookWrongArgumentException;
import by.karpau.library.entity.Book;

public class BookGenreCriterion implements Criterion{
	private String bookGenre;
	
	public Object getCriterion() {
		return bookGenre;
	}
	
	public void setCriterion(Object bookGenre) throws BookWrongArgumentException {
		if(bookGenre == null) {
			throw new BookWrongArgumentException();
		}
		this.bookGenre = (String)bookGenre;
	}

	public boolean compareCriterions(Book book) throws BookWrongArgumentException {
		if(book == null) {
			throw new BookWrongArgumentException();
		}
		if(((String)this.getCriterion()).compareTo(book.getBookGenre()) == 0) {
			return true;
		}
		return false;
	}
	
	public boolean compareForSort(Book book1, Book book2) throws BookWrongArgumentException {
		if(book1 == null || book2 == null) {
			throw new BookWrongArgumentException();
		}
		if((book1.getBookGenre()).compareTo(book2.getBookGenre()) > 0) {
			return true;
		}
		return false;
	}
}
