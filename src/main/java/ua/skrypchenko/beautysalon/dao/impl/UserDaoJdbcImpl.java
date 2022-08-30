package ua.skrypchenko.beautysalon.dao.impl;

import ua.skrypchenko.beautysalon.config.PostgresConfig;
import ua.skrypchenko.beautysalon.dao.UserDao;
import ua.skrypchenko.beautysalon.dto.UserDto;
import ua.skrypchenko.beautysalon.entity.Rating;
import ua.skrypchenko.beautysalon.entity.Role;
import ua.skrypchenko.beautysalon.entity.User;

import java.sql.*;
import java.util.*;

public class UserDaoJdbcImpl implements UserDao {

    private final String SQL_FIND_ALL_MASTERS = "SELECT rating_id, username, rating_mark FROM users left join ratings  on user_id = ratings.master_id WHERE  role= 'MASTER' ";
    private final String SQL_FIND_ALL_SORTED_MASTERS_BY_NAME = "SELECT rating_id, username, rating_mark FROM users left join ratings  on user_id = ratings.master_id WHERE  role= 'MASTER' ORDER BY username ASC ";
    private final String SQL_FIND_ALL_SORTED_MASTERS_BY_RATING_MARK = "SELECT rating_id, username, rating_mark FROM users left join ratings  on user_id = ratings.master_id WHERE  role= 'MASTER' ORDER BY rating_mark DESC ";
    private final String SQL_SAVE_USER = "INSERT INTO users (password, role, username, e_mail) VALUES (?, ?, ?, ?);";
    private final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE e_mail = ?";
    private final String SQL_GET_EMAIL = "SELECT e_mail FROM users WHERE username = ?";
    private final String SQL_FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE e_mail = ? and password = ?";
    private final String SQL_GET_MASTER_WITH_CLIENT = "SELECT  users.username as mastername, u.username as clientname FROM users left join reservations r on users.user_id = r.beauty_master_user_id join users u on u.user_id = r.client_user_id";

    PostgresConfig postgresConfig = new PostgresConfig();

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
            this.connection = postgresConfig.getСonnection();

            PreparedStatement ps = connection.prepareStatement(SQL_SAVE_USER);
            ps.setString(1, user.getPassword());
            ps.setString(2, Role.CLIENT.toString());
            ps.setString(3, user.getUserName());
            ps.setString(4, user.getEmail());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(UserDto userDto) {
        User user = new User();
        try {
            this.connection = postgresConfig.getСonnection();

            PreparedStatement ps = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL_AND_PASSWORD);
            ps.setString(1, userDto.getEmail());
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
            this.connection = postgresConfig.getСonnection();

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
    public HashMap<User, HashSet<User>> getMasterWithClient(){
        HashMap<User, HashSet<User>> map = new HashMap<>();

        try {
            this.connection = postgresConfig.getСonnection();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_GET_MASTER_WITH_CLIENT);

            while (rs.next()) {

                String masterName = rs.getString("mastername");
                String clientName = rs.getString("clientname");


                User master = new User(masterName);
                User client = new User(clientName);

                if (map.containsKey(master) ) {
                    map.get(master).add(client);
                } else {
                    HashSet<User> clients = new HashSet<>();
                    clients.add(client);
                    map.put(master, clients);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }


    @Override
    public boolean isExistUser(String eMail) {
        try {
            this.connection = postgresConfig.getСonnection();


            String email = null;

            PreparedStatement ps = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL);
            ps.setString(1, eMail);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                email = rs.getString("e_mail");
            }

            if (email == null) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private List<Rating> findMasters(String sql) {
        List<Rating> masters = new ArrayList<>();

        try {
            this.connection = postgresConfig.getСonnection();
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
