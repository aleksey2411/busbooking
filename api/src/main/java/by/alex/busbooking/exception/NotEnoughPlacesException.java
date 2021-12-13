package by.alex.busbooking.exception;

public class NotEnoughPlacesException extends RuntimeException {
    public NotEnoughPlacesException() {
        super("Incorrect number of seats");
    }
}
