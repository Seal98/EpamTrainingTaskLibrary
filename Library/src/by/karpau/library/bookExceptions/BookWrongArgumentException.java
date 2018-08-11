package by.karpau.library.bookExceptions;

public class BookWrongArgumentException extends Exception{
	
    public BookWrongArgumentException() {
    	
    }
    
    public BookWrongArgumentException(String message) {
        super(message);
    }
}
