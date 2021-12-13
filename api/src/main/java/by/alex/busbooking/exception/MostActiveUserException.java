package by.alex.busbooking.exception;

public class MostActiveUserException extends RuntimeException {
    public MostActiveUserException(int busId) {
        super("Nobody was transported by car with id " + busId);
    }
}
