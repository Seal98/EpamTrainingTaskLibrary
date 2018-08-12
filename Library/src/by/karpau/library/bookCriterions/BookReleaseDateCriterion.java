package by.karpau.library.bookCriterions;

import java.util.Date;

import by.karpau.library.bookExceptions.BookWrongArgumentException;
import by.karpau.library.entity.Book;

public class BookReleaseDateCriterion implements Criterion{
	private Date bookReleaseDate;
	
	public Object getCriterion() {
		return bookReleaseDate;
	}
	
	public void setCriterion(Object bookReleaseDate) throws BookWrongArgumentException {
		if(bookReleaseDate == null) {
			throw new BookWrongArgumentException();
		}
		this.bookReleaseDate = new Date(((Date)bookReleaseDate).getTime());
	}

	public boolean compareCriterions(Book book) throws BookWrongArgumentException {
		if(book == null) {
			throw new BookWrongArgumentException();
		}
		if(((Date)this.getCriterion()).getTime() == book.getDateOfPublication().getTime()) {
			return true;
		}
		return false;
	}
	
	public boolean compareForSort(Book book1, Book book2) throws BookWrongArgumentException {
		if(book1 == null || book2 == null) {
			throw new BookWrongArgumentException();
		}
		if(book1.getDateOfPublication().getTime() > book2.getDateOfPublication().getTime()) {
			return true;
		}
		return false;
	}
}
