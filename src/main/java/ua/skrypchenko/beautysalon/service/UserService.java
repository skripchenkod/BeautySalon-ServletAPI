package ua.skrypchenko.beautysalon.service;

import org.apache.log4j.Logger;
import ua.skrypchenko.beautysalon.dao.UserDao;
import ua.skrypchenko.beautysalon.dao.impl.UserDaoJdbcImpl;
import ua.skrypchenko.beautysalon.dto.UserDto;
import ua.skrypchenko.beautysalon.entity.Rating;
import ua.skrypchenko.beautysalon.entity.User;
import ua.skrypchenko.beautysalon.exeption.AlreadyRegisteredUserException;
import ua.skrypchenko.beautysalon.exeption.UserNotFoundException;
import ua.skrypchenko.beautysalon.validator.UserValidator;
import ua.skrypchenko.beautysalon.validator.Validator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class UserService {

    UserDao userDao = new UserDaoJdbcImpl();
    private final Validator<UserDto> validator = new UserValidator();
    PasswordEncoderService passwordEncoderService = new PasswordEncoderService();
    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    public String chekUser(UserDto userDto) {

        User user = userDao.getUser(userDto);
        if (user.getUsername() != null && user.getPassword() != null) {
            return user.getRole().toString();
        }
        else {
            throw new UserNotFoundException();
        }
    }

    public String getUserName(UserDto userDto){
        User user = userDao.getUser(userDto);
        return user.getUsername();
    }

    public List<Rating> getMasters() {
        return userDao.findAllMasters();
    }

    public String getEmail(String emailName){
        return userDao.getEmailUser(emailName);
    }

    public List<Rating> getSortMastersByName() {
        return userDao.findAllSortedMastersByName();
    }

    public List<Rating> getSortMastersByRating() {
        return userDao.findAllSortedMastersByRating();
    }

    public HashMap<User, HashSet<User>> getMasterWithClient(){
        return userDao.getMasterWithClient();
    }



    public void saveUser(UserDto userDto) {
        validator.validate(userDto);

        if(userDao.isExistUser(userDto.getEmail())){
            throw new AlreadyRegisteredUserException("User with such email already exist " + userDto.getEmail());
        }

        UserDto userWithEncodedPassword = new UserDto(userDto.getUserName(), passwordEncoderService.encode(userDto.getPassword()), userDto.getEmail());

        userDao.saveUser(userWithEncodedPassword);

        LOGGER.info("User registered " + userDto.getEmail());
    }
}
