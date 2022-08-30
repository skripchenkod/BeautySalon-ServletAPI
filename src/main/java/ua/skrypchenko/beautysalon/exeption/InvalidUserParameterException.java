package ua.skrypchenko.beautysalon.exeption;

public class InvalidUserParameterException extends RuntimeException {

    public InvalidUserParameterException() {
    }

    public InvalidUserParameterException(String message) {
        super(message);
    }

    public InvalidUserParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
