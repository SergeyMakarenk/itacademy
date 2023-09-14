package by.itacademy.reader;

public class ReaderException extends Exception {
    public ReaderException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ReaderException(final String message){
        super(message);
    }
}
