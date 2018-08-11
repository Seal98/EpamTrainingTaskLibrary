package by.karpau.library.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import bookCriterions.Criterion;
import by.karpau.library.bookExceptions.BookExistException;
import by.karpau.library.bookExceptions.BookNotExistException;
import by.karpau.library.bookExceptions.BookWrongArgumentException;
import by.karpau.library.entity.Book;

public interface BookDao {
	void addBook(Book newBook) throws BookExistException, IOException, ParseException, SQLException;
	void removeBook(Book existBook) throws BookNotExistException, IOException, ParseException, SQLException;
	List<Book> findByTag(Criterion tag) throws BookWrongArgumentException, IOException, ParseException, SQLException;
	List<Book> sortBooksByTag(Criterion criterion) throws BookWrongArgumentException, IOException, ParseException, SQLException;
}
