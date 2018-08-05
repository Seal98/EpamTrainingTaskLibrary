package by.karpau.library.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import by.karpau.library.bookExceptions.BookExistException;
import by.karpau.library.bookExceptions.BookNotExistException;
import by.karpau.library.entity.Book;
import by.karpau.library.entity.Criterion;

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

	public List<Book> findByTag(Criterion criterion) {
		List<Book> listOfBooks = getListOfBooks();
		int[] arrayForRemovingBooks = new int[listOfBooks.size()];
		for (int i = 0; i < listOfBooks.size(); i++) {
			if (criterion.getBookName() != null
					&& criterion.getBookName().compareTo(listOfBooks.get(i).getBookName()) != 0) {
				arrayForRemovingBooks[i] = 1;
			}
			if (criterion.getAuthorName() != null
					&& criterion.getAuthorName().compareTo(listOfBooks.get(i).getAuthorName()) != 0) {
				arrayForRemovingBooks[i] = 1;
			}
			if (criterion.getBookGenre() != null
					&& criterion.getBookGenre().compareTo(listOfBooks.get(i).getBookGenre()) != 0) {
				arrayForRemovingBooks[i] = 1;
			}
			if (criterion.getDateOfPublication() != null && criterion.getDateOfPublication().getTime() != listOfBooks
					.get(i).getDateOfPublication().getTime()) {
				arrayForRemovingBooks[i] = 1;
			}
			if (criterion.getNumberOfPages() != -1
					&& criterion.getNumberOfPages() != listOfBooks.get(i).getNumberOfPages()) {
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
	 */
	public List<Book> sortBooksByTag(int criterion) {
		criterion %= 6;
		List<Book> listOfBooks = getListOfBooks();
		for (int i = 0; i < listOfBooks.size() - 1; i++) {
			for (int j = i + 1; j < listOfBooks.size(); j++) {
				switch (criterion) {
				case 1:
					if (listOfBooks.get(i).getBookName().compareTo(listOfBooks.get(j).getBookName()) == 1) {
						Book bookContainer = listOfBooks.get(i);
						listOfBooks.set(i, listOfBooks.get(j));
						listOfBooks.set(j, bookContainer);
					}
					;
					break;
				case 2:
					if (listOfBooks.get(i).getAuthorName().compareTo(listOfBooks.get(j).getAuthorName()) == 1) {
						Book bookContainer = listOfBooks.get(i);
						listOfBooks.set(i, listOfBooks.get(j));
						listOfBooks.set(j, bookContainer);
					}
					;
					break;
				case 3:
					if (listOfBooks.get(i).getBookGenre().compareTo(listOfBooks.get(j).getBookGenre()) == 1) {
						Book bookContainer = listOfBooks.get(i);
						listOfBooks.set(i, listOfBooks.get(j));
						listOfBooks.set(j, bookContainer);
					}
					;
					break;
				case 4:
					if (listOfBooks.get(i).getDateOfPublication().getTime() > listOfBooks.get(j).getDateOfPublication()
							.getTime()) {
						Book bookContainer = listOfBooks.get(i);
						listOfBooks.set(i, listOfBooks.get(j));
						listOfBooks.set(j, bookContainer);
					}
					;
					break;
				case 5:
					if (listOfBooks.get(i).getNumberOfPages() > listOfBooks.get(j).getNumberOfPages()) {
						Book bookContainer = listOfBooks.get(i);
						listOfBooks.set(i, listOfBooks.get(j));
						listOfBooks.set(j, bookContainer);
					}
					;
					break;
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
