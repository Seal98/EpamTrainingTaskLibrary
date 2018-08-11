package by.karpau.library.presentation.view;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import bookCriterions.BookNameCriterion;
import bookCriterions.BookNumberOfPagesCriterion;
import bookCriterions.Criterion;
import by.karpau.library.bookExceptions.BookExistException;
import by.karpau.library.bookExceptions.BookNotExistException;
import by.karpau.library.bookExceptions.BookWrongArgumentException;
import by.karpau.library.dao.BookFileDao;
import by.karpau.library.dao.BookListDao;
import by.karpau.library.entity.Book;
import by.karpau.library.service.BookActions;

public class Main {

	public static void main(String[] args) {
		try {
			BookListDao bld = new BookListDao(BookActions.readBooksFromFile("input.txt"));
			Book testBook = new Book("Book1", "Author1", "Genre1", new Date(), 1);
			bld.addBook(testBook);
			bld.removeBook(testBook);
			testBook = new Book("Book55", "Author1", "Genre55", new Date(), 1);
			bld.addBook(testBook);
			
			List<Book> listOfBooks = bld.getListOfBooks();
			Criterion cr = new BookNameCriterion();
			cr.setCriterion("Book55");
			List<Book> listOfCrBooks = bld.findByTag(cr);
			for(Book el : listOfCrBooks) {
				System.out.println(el);
			}
			System.out.println("\n\n");
			listOfBooks = bld.sortBooksByTag(new BookNumberOfPagesCriterion());
			for(Book el : listOfBooks) {
				System.out.println(el);
			}
			BookActions.writeBooksIntoFile("output.txt", listOfBooks);
			
			BookFileDao bfd = new BookFileDao("input.txt");
			bfd.sortBooksByTag(new BookNumberOfPagesCriterion());
		}
		catch(IOException e) {
			System.out.println(e);
		}
		catch(ParseException e) {
			System.out.println(e);
		}
		catch(BookExistException e) {
			System.out.println("Book already exist");
		}
		catch(BookNotExistException e) {
			System.out.println("Book doesn't exist");
		}
		catch(BookWrongArgumentException e) {
			System.out.println("Criterion is wrong");
		}
	}

}
