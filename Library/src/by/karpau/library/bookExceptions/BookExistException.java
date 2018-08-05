package by.karpau.library.bookExceptions;

public class BookExistException extends Exception{
	
    public BookExistException() {
    	
    }
    
    public BookExistException(String message) {
        super(message);
    }
}
