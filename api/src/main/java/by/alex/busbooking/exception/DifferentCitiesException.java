package by.alex.busbooking.exception;

public class DifferentCitiesException extends RuntimeException {
    public DifferentCitiesException() {
        super("Different cities during add bus stop for route");
    }
}
