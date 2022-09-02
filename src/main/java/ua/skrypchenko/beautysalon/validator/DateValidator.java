package ua.skrypchenko.beautysalon.validator;

import ua.skrypchenko.beautysalon.exeption.InvalidDateParameterException;

import org.apache.log4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator {

    private static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
    private static final String TIME_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    private static final Logger LOGGER = Logger.getLogger(DateValidator.class);

    public void validator(String workDay, String startTime, String endTime) {

        validate(workDay, DATE_REGEX, "Date is not correct");
        validate(startTime, TIME_REGEX, "Start is not correct");
        validate(endTime, TIME_REGEX, "End time is not correct");
    }

    private void validate(String parameter, String regex, String message) {
        Matcher matcher = Pattern.compile(regex).matcher(parameter);

        if (!matcher.find()) {
            LOGGER.warn(message + " " + parameter);
            throw new InvalidDateParameterException(message + " " + parameter);
        }
    }
}
