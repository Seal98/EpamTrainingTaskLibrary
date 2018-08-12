package by.karpau.library.bookCriterions;


import by.karpau.library.bookExceptions.BookWrongArgumentException;
import by.karpau.library.entity.Book;

public class BookNumberOfPagesCriterion implements Criterion{
	private int bookNumberOfPages;
	
	public Object getCriterion() {
		return bookNumberOfPages;
	}
	
	public void setCriterion(Object bookNumberOfPages) throws BookWrongArgumentException {
		if(bookNumberOfPages == null) {
			throw new BookWrongArgumentException();
		}
		this.bookNumberOfPages = (Integer)bookNumberOfPages;
	}

	
	public boolean compareCriterions(Book book) throws BookWrongArgumentException {
		if(book == null) {
			throw new BookWrongArgumentException();
		}
		if((Integer)this.getCriterion() == book.getNumberOfPages()) {
			return true;
		}
		return false;
	}
	
	public boolean compareForSort(Book book1, Book book2) throws BookWrongArgumentException {
		if(book1 == null || book2 == null) {
			throw new BookWrongArgumentException();
		}
		if(book1.getNumberOfPages() > book2.getNumberOfPages()) {
			return true;
		}
		return false;
	}
}
