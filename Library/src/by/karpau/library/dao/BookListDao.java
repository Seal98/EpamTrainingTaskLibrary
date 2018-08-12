package by.karpau.library.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import by.karpau.library.bookCriterions.Criterion;
import by.karpau.library.bookExceptions.BookExistException;
import by.karpau.library.bookExceptions.BookNotExistException;
import by.karpau.library.bookExceptions.BookWrongArgumentException;
import by.karpau.library.entity.Book;

public class BookListDao implements BookDao, Comparator<Date> {

	private List<Book> listOfBooks;

	public BookListDao() {
		listOfBooks = new ArrayList<Book>();
	}

	public BookListDao(List<Book> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}

	public List<Book> getListOfBooks() {
		List<Book> copyOfBookList = new ArrayList<>();
		for (Book book : listOfBooks) {
			copyOfBookList.add(book.getBookCopy());
		}
		return copyOfBookList;
	}

	public void addBook(Book newBook) throws BookExistException {
		if (getIndexOfExistingBook(newBook) == -1) {
			listOfBooks.add(newBook);
		} else {
			throw new BookExistException();
		}
	}

	private int getIndexOfExistingBook(Book bookForCheck) {
		int index = 0;
		for (Book book : listOfBooks) {
			if (bookForCheck.equals(book)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public void removeBook(Book existBook) throws BookNotExistException {
		int index;
		if ((index = getIndexOfExistingBook(existBook)) != -1) {
			listOfBooks.remove(index);
		} else {
			throw new BookNotExistException();
		}
	}

	public List<Book> findByTag(Criterion criterion) throws BookWrongArgumentException {
		List<Book> listOfBooks = getListOfBooks();
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
		return listOfBooks;
	}

	/**
	 * Sorts by the criterion
	 * 
	 * @param criterion:
	 *            1 - sort by bookName 2 - sort by authorName 3 - sort by bookGenre
	 *            4 - sort by dateOfPublication 5 - sort by numberOfPages
	 * @throws BookWrongArgumentException 
	 */
	public List<Book> sortBooksByTag(Criterion criterion) throws BookWrongArgumentException {
		List<Book> listOfBooks = getListOfBooks();
		for (int i = 0; i < listOfBooks.size() - 1; i++) {
			for (int j = i + 1; j < listOfBooks.size(); j++) {
					if (criterion.compareForSort(listOfBooks.get(i), listOfBooks.get(j))) {
						Book bookContainer = listOfBooks.get(i);
						listOfBooks.set(i, listOfBooks.get(j));
						listOfBooks.set(j, bookContainer);
					}
			}
		}

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
