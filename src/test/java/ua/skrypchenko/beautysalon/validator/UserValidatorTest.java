package ua.skrypchenko.beautysalon.validator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ua.skrypchenko.beautysalon.dto.UserDto;
import ua.skrypchenko.beautysalon.exeption.InvalidUserParameterException;

import java.security.InvalidParameterException;

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {
    private final Validator<UserDto> userValidator = new UserValidator();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void validateShouldThrowInvalidParameterExceptionForNullUser() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("User is null");

        userValidator.validate(null);
    }

    @Test
    public void validateShouldThrowInvalidUserParameterExceptionForInvalidName() {
        exception.expect(InvalidUserParameterException.class);
        exception.expectMessage("Name is not correct");

        userValidator.validate(new UserDto("1"));
    }

    @Test
    public void validateShouldThrowInvalidUserParameterExceptionForInvalidEmail() {
        exception.expect(InvalidUserParameterException.class);
        exception.expectMessage("Email is not correct");

        userValidator.validate(new UserDto("Dima", "12345678", "@email.com"));
    }

    @Test
    public void validateShouldThrowInvalidUserParameterExceptionForInvalidPassword() {
        exception.expect(InvalidUserParameterException.class);
        exception.expectMessage("Password is not correct");

        userValidator.validate((new UserDto("Dima", "1235678", "dima@email.com")));
    }

    @Test
    public void validateShouldNotThrowException() {
        userValidator.validate(new UserDto("Dima", "1235678c", "dima@email.com"));
    }

}
