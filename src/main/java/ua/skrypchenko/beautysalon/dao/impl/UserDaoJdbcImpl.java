package ua.skrypchenko.beautysalon.dao.impl;

import ua.skrypchenko.beautysalon.config.PostgresConfig;
import ua.skrypchenko.beautysalon.dao.UserDao;
import ua.skrypchenko.beautysalon.dto.UserDto;
import ua.skrypchenko.beautysalon.entity.Rating;
import ua.skrypchenko.beautysalon.entity.Role;
import ua.skrypchenko.beautysalon.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    private final String SQL_FIND_ALL_MASTERS = "SELECT rating_id, username, rating_mark FROM users left join ratings  on user_id = ratings.master_id WHERE  role= 'MASTER' ";
    private final String SQL_FIND_ALL_SORTED_MASTERS_BY_NAME = "SELECT rating_id, username, rating_mark FROM users left join ratings  on user_id = ratings.master_id WHERE  role= 'MASTER' ORDER BY username ASC ";
    private final String SQL_FIND_ALL_SORTED_MASTERS_BY_RATING_MARK = "SELECT rating_id, username, rating_mark FROM users left join ratings  on user_id = ratings.master_id WHERE  role= 'MASTER' ORDER BY rating_mark DESC ";
    private final String SQL_SAVE_USER = "INSERT INTO users (password, role, username, e_mail) VALUES (?, ?, ?, ?);";
    private final String SQL_FIND_USER_BY_NAME = "SELECT * FROM users WHERE username = ?";
    private final String SQL_GET_EMAIL = "SELECT e_mail FROM users WHERE username = ?";
    private final String SQL_FIND_USER_BY_NAME_AND_PASSWORD = "SELECT * FROM users WHERE username = ? and password = ?";
    private final String SQL_EDIT_STATUS = "UPDATE balance SET status = ? WHERE id = (SELECT users.balance_id FROM users WHERE username = ?)";

    private final DataSource dataSource = PostgresConfig.getInstance();
    private Connection connection;

    public UserDaoJdbcImpl() {
    }


    @Override
    public List<Rating> findAllSortedMastersByName() {
        return findMasters(SQL_FIND_ALL_SORTED_MASTERS_BY_NAME);
    }

    @Override
    public List<Rating> findAllMasters() {
        return findMasters(SQL_FIND_ALL_MASTERS);
    }

    @Override
    public List<Rating> findAllSortedMastersByRating() {
        return findMasters(SQL_FIND_ALL_SORTED_MASTERS_BY_RATING_MARK);
    }

    @Override
    public void saveUser(UserDto user) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE_USER);
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getUserName());
            ps.setString(3, Role.CLIENT.toString());
            ps.setString(4, user.getEmail());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(UserDto userDto) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User user = new User();

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_FIND_USER_BY_NAME_AND_PASSWORD);
            ps.setString(1, userDto.getUserName());
            ps.setString(2, userDto.getPassword());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.valueOf(rs.getString("role")));
                user.setEmail(rs.getString("e_mail"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public String getEmailUser(String userName) {

        String email = null;
        try {
            this.connection = dataSource.getConnection();

        PreparedStatement ps = connection.prepareStatement(SQL_GET_EMAIL);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                email = rs.getString("e_mail");
            }
        }catch (SQLException s){
            s.printStackTrace();
        }
        return email;
    }

    @Override
    public boolean isExistUser(String userName) {
        try {
            this.connection = dataSource.getConnection();


            String name = null;

            PreparedStatement ps = connection.prepareStatement(SQL_FIND_USER_BY_NAME);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                name = rs.getString("username");
            }

            if (name == null) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private List<Rating> findMasters(String sql) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Rating> masters = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                masters.add(new Rating(rs.getInt("rating_id"), new User(rs.getString("username")), rs.getDouble("rating_mark")));
            }

        } catch (SQLException s) {
            s.printStackTrace();
        }
        return masters;
    }
}
