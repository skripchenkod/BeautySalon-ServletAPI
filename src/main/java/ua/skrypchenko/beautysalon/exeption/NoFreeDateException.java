package ua.skrypchenko.beautysalon.exeption;

public class NoFreeDateException extends RuntimeException {

    public NoFreeDateException() {
    }

    public NoFreeDateException(String message) {
        super(message);
    }

    public NoFreeDateException(String message, Throwable cause) {
        super(message, cause);
    }
}
