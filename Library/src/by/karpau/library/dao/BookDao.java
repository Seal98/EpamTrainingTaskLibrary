package by.karpau.library.dao;

import java.util.List;

import by.karpau.library.bookExceptions.BookExistException;
import by.karpau.library.bookExceptions.BookNotExistException;
import by.karpau.library.entity.Book;
import by.karpau.library.entity.Criterion;

public interface BookDao {
	void addBook(Book newBook) throws BookExistException;
	void removeBook(Book existBook) throws BookNotExistException;
	List<Book> findByTag(Criterion tag);
	List<Book> sortBooksByTag(int tag);
}
