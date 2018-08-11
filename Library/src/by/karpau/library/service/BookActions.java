package by.karpau.library.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import by.karpau.library.entity.Book;

public class BookActions {
	public static List<Book> readBooksFromFile(String wayToFile) throws IOException, ParseException {
		BufferedReader br = new BufferedReader(new FileReader(wayToFile));

		List<Book> listOfBooks = new ArrayList<>();
		String strFromFile;
		StringTokenizer st;
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		while ((strFromFile = br.readLine()) != null) {
			st = new StringTokenizer(strFromFile);
			String bookName = st.nextToken();
			String authorName = st.nextToken();
			String bookGenre = st.nextToken();
			Date dateOfPublication = formatter.parse(st.nextToken());
			int numberOfPages = Integer.parseInt(st.nextToken());
			listOfBooks.add(new Book(bookName, authorName, bookGenre, dateOfPublication, numberOfPages));
		}
		br.close();
		return listOfBooks;
	}

	public static List<Book> readBooksFromDB(PreparedStatement loadStmt) throws IOException, ParseException, SQLException {
		List<Book> listOfBooks = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
	    ResultSet result = loadStmt.executeQuery();
		while (result.next()) {
			String bookName = result.getString("bookname");
			String authorName = result.getString("authorname");
			String bookGenre = result.getString("genre");
			Date dateOfPublication = formatter.parse(result.getString("releasedate"));
			int numberOfPages = Integer.parseInt(result.getString("numberofpages"));
			listOfBooks.add(new Book(bookName, authorName, bookGenre, dateOfPublication, numberOfPages));
		}
		return listOfBooks;
	}
	
	public static void writeBooksIntoFile(String wayToFile, List<Book> listOfBooks) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(wayToFile));
		for (Book book : listOfBooks) {
			bw.write(book.toString());
			bw.newLine();
		}
		bw.close();
	}
}
