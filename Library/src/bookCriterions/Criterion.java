package bookCriterions;

import java.util.Date;

import by.karpau.library.bookExceptions.BookWrongArgumentException;
import by.karpau.library.entity.Book;

public interface Criterion {
	
	Object getCriterion();
	void setCriterion(Object obj) throws BookWrongArgumentException;
	boolean compareCriterions(Book book) throws BookWrongArgumentException;
	boolean compareForSort(Book book1, Book book2) throws BookWrongArgumentException;
}
