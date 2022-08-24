package ua.skrypchenko.beautysalon.dao.impl;

import ua.skrypchenko.beautysalon.config.PostgresConfig;
import ua.skrypchenko.beautysalon.dao.ReservationDao;
import ua.skrypchenko.beautysalon.entity.Procedure;
import ua.skrypchenko.beautysalon.entity.Reservation;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservationImpl implements ReservationDao {
    private String SQL_GET_ALL_RESERVATION = "SELECT start_hour, end_hour from reservations where beauty_master_user_id = 3";
    private String SQL_GET_DURATION = "SELECT duration_hours FROM procedures WHERE name = ?";
    private String SQL_INSERT_RESERVATION = "INSERT INTO reservations (end_hour, procedure_id, start_hour, beauty_master_user_id, client_user_id, data) VALUES (?,(SELECT procedure_id from procedures WHERE name = ?), ?, (SELECT user_id from users WHERE username = ?),(SELECT user_id FROM users WHERE username = ?), ?)";
    private final String SQL_DELETE_RESERVATION = "DELETE from reservations WHERE start_hour = ? AND data = ? AND beauty_master_user_id = (SELECT user_id username from users WHERE username = ?)";

    private final DataSource dataSource = PostgresConfig.getInstance();
    private Connection connection;


    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations= new ArrayList<>();
        try {
            this.connection = dataSource.getConnection();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_GET_ALL_RESERVATION);

            while (rs.next()){
                reservations.add(new Reservation(rs.getTime("start_hour"), rs.getTime("end_hour")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return reservations;
    }

    private int getDuration(Reservation reservation){
        int duration = 0;
        try {
            this.connection = dataSource.getConnection();

            PreparedStatement ps = connection.prepareStatement(SQL_GET_DURATION);
            ps.setString(1, reservation.getProcedure().getName());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                duration = rs.getInt("duration_hours");
            }
        }
        catch (SQLException s){
            s.printStackTrace();
        }
        return duration;
    }


    public void setReservation(Reservation reservation) {
        try {
            this.connection = dataSource.getConnection();

            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_RESERVATION);
            ps.setTime(1, getEndTime(reservation, getDuration(reservation)));
            ps.setString(2, reservation.getProcedure().getName());
            ps.setTime(3, Time.valueOf(reservation.getStart().toString()));
            ps.setString(4, reservation.getBeautyMaster().getUsername());
            ps.setString(5, reservation.getClient().getUsername());
            ps.setDate(6, Date.valueOf(reservation.getData().toString()));

            ResultSet rs = ps.executeQuery();



        } catch (SQLException s) {
            s.printStackTrace();

        }
    }
    @Override
   public void deleteProcedure(Reservation reservation){
        try {
            this.connection = dataSource.getConnection();

            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_RESERVATION);

            ps.setTime(1, Time.valueOf(reservation.getStart().toString()));
            ps.setDate(2, Date.valueOf(reservation.getData().toString()));
            ps.setString(3, reservation.getBeautyMaster().getUsername());
            ps.executeQuery();
        }
        catch (SQLException s){
            s.printStackTrace();
        }
    }

    private Time getEndTime(Reservation reservation, Integer duration){
        Time myTime = Time.valueOf(reservation.getStart().toString());
        LocalTime localtime = myTime.toLocalTime().plusHours(duration);
        String output = localtime.toString() + ":00";

       return  Time.valueOf(output);
    }
}
