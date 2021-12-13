package by.alex.busbooking.exception;

public class DifferentRoutesException extends RuntimeException {
    public DifferentRoutesException() {
        super("Different routes during buy ticket");
    }
}
