package ua.skrypchenko.beautysalon.dao;

import ua.skrypchenko.beautysalon.dto.UserDto;
import ua.skrypchenko.beautysalon.entity.Rating;
import ua.skrypchenko.beautysalon.entity.User;

import java.util.List;

public interface UserDao {
    List<Rating> findAllMasters();

    List<Rating> findAllSortedMastersByName();

    List<Rating> findAllSortedMastersByRating();

    void saveUser(User user);

    User getUser(UserDto userDto);

    boolean isExistUser(String userName);
}
