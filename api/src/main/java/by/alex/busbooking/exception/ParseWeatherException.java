package by.alex.busbooking.exception;

public class ParseWeatherException extends RuntimeException {
    public ParseWeatherException() {
        super("Error during parse weather");
    }
}
