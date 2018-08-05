package by.karpau.library.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import by.karpau.library.entity.Book;

public class BookActions {
	public static List<Book> readBooksFromFile() throws IOException, ParseException {
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

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

	public static void writeBooksIntoFile(List<Book> listOfBooks) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		for (Book book : listOfBooks) {
			bw.write(book.toString());
			bw.newLine();
		}
		bw.close();
	}
}
