package ua.skrypchenko.beautysalon.exeption;

public class InvalidDateParameterException extends RuntimeException {

    public InvalidDateParameterException() {
    }

    public InvalidDateParameterException(String message) {
        super(message);
    }

    public InvalidDateParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
