package ua.skrypchenko.beautysalon.exeption;

public class AlreadyRegisteredUserException extends RuntimeException {

    public AlreadyRegisteredUserException() {
    }

    public AlreadyRegisteredUserException(String message) {
        super(message);
    }

    public AlreadyRegisteredUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
