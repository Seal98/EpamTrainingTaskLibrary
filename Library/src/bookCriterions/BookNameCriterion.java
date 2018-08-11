package bookCriterions;

import by.karpau.library.bookExceptions.BookWrongArgumentException;
import by.karpau.library.entity.Book;

public class BookNameCriterion implements Criterion{
	private String bookName;
	
	public Object getCriterion() {
		return bookName;
	}
	
	public void setCriterion(Object bookName) throws BookWrongArgumentException {
		if(bookName == null) {
			throw new BookWrongArgumentException();
		}
		this.bookName = (String)bookName;
	}

	public boolean compareCriterions(Book book) throws BookWrongArgumentException {
		if(book == null) {
			throw new BookWrongArgumentException();
		}
		if(((String)this.getCriterion()).compareTo((String)book.getBookName()) == 0) {
			return true;
		}
		return false;
	}
	
	public boolean compareForSort(Book book1, Book book2) throws BookWrongArgumentException {
		if(book1 == null || book2 == null) {
			throw new BookWrongArgumentException();
		}
		if((book1.getBookName()).compareTo(book2.getBookName()) > 0) {
			return true;
		}
		return false;
	}
}
