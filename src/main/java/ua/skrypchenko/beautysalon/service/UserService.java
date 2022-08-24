package ua.skrypchenko.beautysalon.service;

import ua.skrypchenko.beautysalon.dao.UserDao;
import ua.skrypchenko.beautysalon.dao.impl.UserDaoJdbcImpl;
import ua.skrypchenko.beautysalon.dto.UserDto;
import ua.skrypchenko.beautysalon.entity.Rating;
import ua.skrypchenko.beautysalon.entity.User;

import java.util.List;

public class UserService {

    UserDao userDao = new UserDaoJdbcImpl();

    public String chekUser(UserDto userDto) {
        String role;
        String error = "error";
        User user = userDao.getUser(userDto);
        if (user.getUsername() != null && user.getPassword() != null) {
            role = user.getRole().toString();
            return role;
        }
        return error;
    }

    public List<Rating> getMasters() {
        return userDao.findAllMasters();
    }

    public List<Rating> getSortMastersByName() {
        return userDao.findAllSortedMastersByName();
    }

    public List<Rating> getSortMastersByRating() {
        return userDao.findAllSortedMastersByRating();
    }
}