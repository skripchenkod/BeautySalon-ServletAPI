package ua.skrypchenko.beautysalon.exeption;

public class DBConnectionException extends RuntimeException {
    public DBConnectionException(Throwable cause) {
        super(cause);
    }

    public DBConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}