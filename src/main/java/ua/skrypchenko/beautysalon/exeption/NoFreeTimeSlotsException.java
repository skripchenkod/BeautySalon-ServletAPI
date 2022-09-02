package ua.skrypchenko.beautysalon.exeption;

public class NoFreeTimeSlotsException extends RuntimeException {

    public NoFreeTimeSlotsException() {
    }

    public NoFreeTimeSlotsException(String message) {
        super(message);
    }

    public NoFreeTimeSlotsException(String message, Throwable cause) {
        super(message, cause);
    }
}
