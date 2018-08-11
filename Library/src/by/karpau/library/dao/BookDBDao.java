package by.karpau.library.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import bookCriterions.Criterion;
import by.karpau.library.bookExceptions.BookNotExistException;
import by.karpau.library.bookExceptions.BookWrongArgumentException;
import by.karpau.library.entity.Book;
import by.karpau.library.service.BookActions;

public class BookDBDao implements BookDao, Comparator<Date> {

	private PreparedStatement saveStmt;
	private PreparedStatement loadStmt;
	private Connection con;
	
	public BookDBDao() {
	}

	public BookDBDao(String url, String user, String pw) throws SQLException {
		    con = DriverManager.getConnection(url, user, pw);
		    saveStmt = con.prepareStatement("INSERT INTO library(bookname, authorname, genre, releasedate, numberofpages) "
		                                   +"VALUES (?, ?, ?, ?, ?)");
		    loadStmt = con.prepareStatement("SELECT bookname, authorname, genre, releasedate,"
		    		+ " numberofpages FROM library");
		  }

	public List<Book> getListOfBooks() throws IOException, ParseException, SQLException{
		List<Book> copyOfBookList = BookActions.readBooksFromDB(loadStmt);
		return copyOfBookList;
	}

	public void addBook(Book newBook) throws IOException, ParseException, SQLException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		saveStmt.setString(1, newBook.getBookName());
		saveStmt.setString(2, newBook.getAuthorName());
		saveStmt.setString(3, newBook.getBookGenre());
		saveStmt.setString(4, formatter.format(newBook.getDateOfPublication()));
		saveStmt.setString(5, newBook.getNumberOfPages().toString());
	    saveStmt.executeUpdate();
	}

	private int getIndexOfExistingBook(Book bookForCheck) throws IOException, ParseException, SQLException {
		int index = 0;
		List<Book> listOfBooks = BookActions.readBooksFromDB(loadStmt);
		for (Book book : listOfBooks) {
			if (bookForCheck.equals(book)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public void removeBook(Book existBook) throws BookNotExistException, IOException, ParseException, SQLException {
		int index;
		List<Book> listOfBooks = BookActions.readBooksFromDB(loadStmt);
		if ((index = getIndexOfExistingBook(existBook)) != -1) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			PreparedStatement delSt = con.prepareStatement("DELETE FROM library WHERE bookname = ?, authorname = ?,"
					+ " genre = ?, releasedate = ?, numberofpages = ?");
			delSt.setString(1, existBook.getBookName());
			delSt.setString(2, existBook.getAuthorName());
			delSt.setString(3, existBook.getBookGenre());
			delSt.setString(4, formatter.format(existBook.getDateOfPublication()));
			delSt.setString(5, existBook.getNumberOfPages().toString());
			delSt.executeUpdate(); 
		} else {
			throw new BookNotExistException();
		}
	}

	public List<Book> findByTag(Criterion criterion) throws BookWrongArgumentException, IOException, ParseException, SQLException {
		List<Book> listOfBooks = BookActions.readBooksFromDB(loadStmt);
		int[] arrayForRemovingBooks = new int[listOfBooks.size()];
		for (int i = 0; i < listOfBooks.size(); i++) {
			if (!criterion.compareCriterions(listOfBooks.get(i))) {
				arrayForRemovingBooks[i] = 1;
			}
		}
		for (int i = arrayForRemovingBooks.length - 1; i >= 0; i--) {
			if (arrayForRemovingBooks[i] == 1) {
				listOfBooks.remove(i);
			}
		}
		BookActions.writeBooksIntoFile("booksFoundByTag.txt", listOfBooks);
		return listOfBooks;
	}

	/**
	 * Sorts by the criterion
	 * 
	 * @param criterion:
	 *            1 - sort by bookName 2 - sort by authorName 3 - sort by bookGenre
	 *            4 - sort by dateOfPublication 5 - sort by numberOfPages
	 * @throws BookWrongArgumentException
	 * @throws ParseException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public List<Book> sortBooksByTag(Criterion criterion)
			throws BookWrongArgumentException, IOException, ParseException, SQLException {
		List<Book> listOfBooks = BookActions.readBooksFromDB(loadStmt);
		for (int i = 0; i < listOfBooks.size() - 1; i++) {
			for (int j = i + 1; j < listOfBooks.size(); j++) {
				if (criterion.compareForSort(listOfBooks.get(i), listOfBooks.get(j))) {
					Book bookContainer = listOfBooks.get(i);
					listOfBooks.set(i, listOfBooks.get(j));
					listOfBooks.set(j, bookContainer);
				}
			}
		}
		BookActions.writeBooksIntoFile("sortedBooks.txt", listOfBooks);
		return listOfBooks;
	}

	@Override
	public int compare(Date date1, Date date2) {
		Calendar dateCal1 = Calendar.getInstance();
		Calendar dateCal2 = Calendar.getInstance();
		dateCal1.setTime(date1);
		dateCal2.setTime(date2);
		if (dateCal1.get(Calendar.DAY_OF_MONTH) == dateCal2.get(Calendar.DAY_OF_MONTH)
				&& dateCal1.get(Calendar.MONTH) == dateCal2.get(Calendar.MONTH)
				&& dateCal1.get(Calendar.YEAR) == dateCal2.get(Calendar.YEAR)) {
			return 1;
		}
		return 0;
	}
}
