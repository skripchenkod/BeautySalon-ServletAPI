package ua.skrypchenko.beautysalon.service;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.skrypchenko.beautysalon.dao.UserDao;
import ua.skrypchenko.beautysalon.dto.UserDto;
import ua.skrypchenko.beautysalon.entity.Rating;
import ua.skrypchenko.beautysalon.entity.Role;
import ua.skrypchenko.beautysalon.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private static final Rating RATING = new Rating(1, new User("Dima"), 5.);
    private static final User USER = new User("Dima", "12345678", Role.valueOf("ADMIN"), "dima@gmail.com");
    private PasswordEncoderService passwordEncoderService = new PasswordEncoderService();

    @Rule
    public ExpectedException exception;
    @InjectMocks
    UserService userService;
    @Mock
    UserDao userDao;


    @Test
    public void getAllMasters() {
        when(userDao.findAllMasters()).thenReturn(Collections.singletonList(RATING));

        List<Rating> actual = userService.getMasters();

        verify(userDao, times(1)).findAllMasters();
        Assert.assertThat(actual, is(not(Collections.emptyList())));
    }

    @Test
    public void getEmail(){
        String username = "Dima";
        String email = "dima@gmail.com";
        when(userDao.getEmailUser(username)).thenReturn(email);

        String actual = userService.getEmail(username);

        verify(userDao, times(1)).getEmailUser(username);

        Assert.assertThat(actual, is(email));
    }

    @Test
    public void getSortMastersByName() {
        ArrayList<Rating> arrayList = new ArrayList<>(Arrays.asList(new Rating(new User("ira")), new Rating(new User("sonya"))));
        when(userDao.findAllSortedMastersByName()).thenReturn(arrayList);

        List<Rating> actual = userService.getSortMastersByName();

        verify(userDao, times(1)).findAllSortedMastersByName();

        Assert.assertThat(actual, is(arrayList));
    }

    @Test
    public void saveUser() {

        UserDto userDto = new UserDto("Dima", "12345678", "Dima@gmail.com");

        UserDto userWithEncodingPassword = new UserDto("Dima", passwordEncoderService.encode(userDto.getPassword()), "Dima@gmail.com");
        userService.saveUser(userDto);
        verify(userDao, times(1)).saveUser(userWithEncodingPassword);
        verify(userDao, times(1)).isExistUser(userDto.getUserName());

    }

    @Test
    public void getUser() {
        UserDto userDto = new UserDto("Dima", passwordEncoderService.encode("12345678"));
        UserDto userDto1 = new UserDto("Dima", "12345678");
        User fromDb = new User("Dima", "12345678", Role.CLIENT);

        when(userDao.getUser(userDto)).thenReturn(fromDb);

       String actual = userService.getUserRole(userDto1);

        Assert.assertThat(actual, is("CLIENT"));


    }
}
