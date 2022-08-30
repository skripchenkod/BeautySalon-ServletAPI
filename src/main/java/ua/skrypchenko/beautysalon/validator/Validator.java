package ua.skrypchenko.beautysalon.validator;



import java.security.InvalidParameterException;
import java.util.Objects;
import org.apache.log4j.Logger;

public interface Validator<E> {

    void validate(E entity);

    default <T> void validateNotNull(T parameter, String message, Logger logger) {
        if (Objects.isNull(parameter)) {
            logger.warn(message);
            throw new InvalidParameterException(message);
        }
    }

    default void validateNotEmpty(String parameter, String message, Logger logger) {
        if (parameter.isEmpty()) {
            logger.warn(message);
            throw new InvalidParameterException(message);
        }
    }
}
