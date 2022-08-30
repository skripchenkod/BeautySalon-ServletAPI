package ua.skrypchenko.beautysalon.dao.impl;

import ua.skrypchenko.beautysalon.config.PostgresConfig;
import ua.skrypchenko.beautysalon.dao.MasterScheduleDao;
import ua.skrypchenko.beautysalon.dto.MastersScheduleDto;
import ua.skrypchenko.beautysalon.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterScheduleDaoImpl implements MasterScheduleDao {

    private final String SQL_GET_SCHEDULE_FOR_CLIENT = "SELECT users.username, master_schedule.work_day, master_schedule.start_work_day, master_schedule.end_work_day  from procedures_masters, users, procedures, master_schedule WHERE procedures_masters.master_id = users.user_id AND procedures_masters.procedure_id = procedures.procedure_id AND master_schedule.master_id = users.user_id AND procedures.name = ?";
    private final String SQL_GET_SCHEDULE_FOR_MASTER = "SELECT work_day FROM master_schedule, users WHERE master_id = users.user_id AND username = ?";
    private final String SQL_GET_SLOTS = "SELECT start_hour from reservations WHERE data = '2022-08-18' AND beauty_master_user_id = ?";
    private final String SQL_GET_SCHEDULE_Masters = "SELECT start_work_day, end_work_day from master_schedule, users WHERE work_day = ? AND master_id = users.user_id AND username = ?";
    private final String SQL_GET_BUSY_SLOTS_WITH_DURATION = "SELECT start_hour, duration_hours from reservations, procedures, users WHERE procedures.procedure_id = reservations.procedure_id AND reservations.beauty_master_user_id = users.user_id AND username = ? AND data = ?";
    private final String SQL_GET_BUSY_SLOTS = "SELECT start_hour from reservations, users WHERE  reservations.beauty_master_user_id = users.user_id AND username = ? AND data = ?";

    PostgresConfig postgresConfig = new PostgresConfig();

    private Connection connection;

    @Override
    public List<MastersScheduleDto> getScheduleForClient(String procedureName) {
        List<MastersScheduleDto> masterSchedules = new ArrayList<>();

        try {
            this.connection = postgresConfig.getСonnection();

            PreparedStatement ps = connection.prepareStatement(SQL_GET_SCHEDULE_FOR_CLIENT);
            ps.setString(1, procedureName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                masterSchedules.add(new MastersScheduleDto(rs.getDate("work_day"), rs.getTime("start_work_day"), rs.getTime("end_work_day"), new User(rs.getString("username"))));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return masterSchedules;
    }

    @Override
    public List<Time> getScheduleForBusyTimeSlots(int master) {
        List<Time> slots = new ArrayList<>();
        try {
            this.connection = postgresConfig.getСonnection();
            PreparedStatement ps = connection.prepareStatement(SQL_GET_SLOTS);
            ps.setInt(1, master);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                slots.add(rs.getTime("start_hour"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slots;
    }

    @Override
    public List<String> getScheduleForFreeTimeSlots(String masterName, Date date){
       List<String> schedule = new ArrayList<>();
       try {
           this.connection = postgresConfig.getСonnection();
           PreparedStatement ps = connection.prepareStatement(SQL_GET_SCHEDULE_Masters);
           ps.setDate(1,date);
           ps.setString(2,masterName);
           ResultSet rs = ps.executeQuery();

           while (rs.next()) {
               schedule.add(rs.getString("start_work_day"));
               schedule.add(rs.getString("end_work_day"));
           }

       } catch (SQLException e) {
           e.printStackTrace();
       }
       return schedule;
    }

    @Override
    public List<String> getBusyTimeSlotsWithDuration(String masterName, Date date){
        List<String>  busySlots = new ArrayList<>();
        try {
            this.connection = postgresConfig.getСonnection();
            PreparedStatement ps = connection.prepareStatement(SQL_GET_BUSY_SLOTS_WITH_DURATION);
            ps.setString(1, masterName);
            ps.setDate(2, date);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                busySlots.add(rs.getString("start_hour"));
                busySlots.add(rs.getString("duration_hours"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return busySlots;
    }

    @Override
    public List<String> getBusyTimeSlots(String masterName, Date date){
        List<String>  busySlots = new ArrayList<>();
        try {
            this.connection = postgresConfig.getСonnection();
            PreparedStatement ps = connection.prepareStatement(SQL_GET_BUSY_SLOTS);
            ps.setString(1, masterName);
            ps.setDate(2, date);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                busySlots.add(rs.getString("start_hour"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return busySlots;
    }

    @Override
    public List<MastersScheduleDto> getScheduleForMaster(String masterName) {
        List<MastersScheduleDto> masterSchedules = new ArrayList<>();

        try {
            this.connection = postgresConfig.getСonnection();

            PreparedStatement ps = connection.prepareStatement(SQL_GET_SCHEDULE_FOR_MASTER);
            ps.setString(1, masterName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                masterSchedules.add(new MastersScheduleDto(rs.getDate("work_day")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return masterSchedules;
    }
}
