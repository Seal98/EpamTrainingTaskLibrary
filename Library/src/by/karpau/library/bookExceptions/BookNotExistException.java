package by.karpau.library.bookExceptions;

public class BookNotExistException extends Exception{
	
    public BookNotExistException() {
    	
    }
    
    public BookNotExistException(String message) {
        super(message);
    }
}
