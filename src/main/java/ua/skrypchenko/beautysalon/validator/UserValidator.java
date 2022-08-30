package ua.skrypchenko.beautysalon.validator;

import org.apache.log4j.Logger;
import ua.skrypchenko.beautysalon.dto.UserDto;
import ua.skrypchenko.beautysalon.exeption.InvalidUserParameterException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator implements Validator<UserDto> {
    private static final Logger LOGGER = Logger.getLogger(UserValidator.class);

    private static final String NAME_REGEX = "[a-zA-Zа-яА-Яієї']{2,25}";
    private static final String EMAIL_REGEX = "^\\w+\\.?\\w{1,}@\\w+\\.[a-z]{2,4}$";
    private static final String PASSWORD_REGEX = "[A-Za-z0-9]{8,}";

    @Override
    public void validate(UserDto userDto) {
        validateNotNull(userDto, "User is null", LOGGER);

        validate(userDto.getUserName(), NAME_REGEX, "Name is not correct");
        validate(userDto.getEmail(), EMAIL_REGEX, "Email is not correct");
        validate(userDto.getPassword(), PASSWORD_REGEX, "Password is not correct");

    }

    private void validate(String parameter, String regex, String message) {
        Matcher matcher = Pattern.compile(regex).matcher(parameter);

        if (!matcher.find()) {
            LOGGER.warn(message + " " + parameter);
            throw new InvalidUserParameterException(message + " " + parameter);
        }
    }
}
