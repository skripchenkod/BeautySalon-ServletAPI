package ua.skrypchenko.beautysalon.exeption;

public class PasswordEncodeException extends RuntimeException {
    public PasswordEncodeException(String message) {
        super(message);
    }
}
