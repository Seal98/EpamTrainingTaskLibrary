package by.karpau.library.dao;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import by.karpau.library.bookCriterions.Criterion;
import by.karpau.library.bookExceptions.BookNotExistException;
import by.karpau.library.bookExceptions.BookWrongArgumentException;
import by.karpau.library.entity.Book;
import by.karpau.library.service.BookActions;

public class BookFileDao implements BookDao, Comparator<Date> {

	private String wayToFile;

	public BookFileDao() {}
	
	public BookFileDao(String way) {
		wayToFile = way;
	}

	public List<Book> getListOfBooks() throws IOException, ParseException {
		List<Book> copyOfBookList = BookActions.readBooksFromFile(wayToFile);
		return copyOfBookList;
	}

	public void addBook(Book newBook) throws IOException, ParseException{
		List<Book> listOfBooks = BookActions.readBooksFromFile(wayToFile);
		listOfBooks.add(newBook);
		BookActions.writeBooksIntoFile(wayToFile, listOfBooks);
	}

	private int getIndexOfExistingBook(Book bookForCheck) throws IOException, ParseException {
		int index = 0;
		List<Book> listOfBooks = BookActions.readBooksFromFile(wayToFile);
		for (Book book : listOfBooks) {
			if (bookForCheck.equals(book)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public void removeBook(Book existBook) throws BookNotExistException, IOException, ParseException {
		int index;
		List<Book> listOfBooks = BookActions.readBooksFromFile(wayToFile);
		if ((index = getIndexOfExistingBook(existBook)) != -1) {
			listOfBooks.remove(index);
			BookActions.writeBooksIntoFile(wayToFile, listOfBooks);
		} else {
			throw new BookNotExistException();
		}
	}
	
	
	public List<Book> findByTag(Criterion criterion) throws BookWrongArgumentException, IOException, ParseException {
		List<Book> listOfBooks = BookActions.readBooksFromFile(wayToFile);
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
	 */
	public List<Book> sortBooksByTag(Criterion criterion) throws BookWrongArgumentException, IOException, ParseException {
		List<Book> listOfBooks = BookActions.readBooksFromFile(wayToFile);
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
